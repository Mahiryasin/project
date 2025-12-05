
CREATE TABLE CurrencyType (
    id INT IDENTITY(1,1) PRIMARY KEY,
    create_date DATETIME DEFAULT GETDATE(),
    currency_code VARCHAR(3) UNIQUE NOT NULL CHECK (currency_code IN ('TRY','USD','EUR')),
    description VARCHAR(255)
);

CREATE TABLE CarStatusType (
    id INT IDENTITY(1,1) PRIMARY KEY,
    create_date DATETIME DEFAULT GETDATE(),
    status_code VARCHAR(50) UNIQUE NOT NULL CHECK (status_code IN ('SALABLE','SALED')),
    description VARCHAR(255)
);

CREATE TABLE City (
    id INT IDENTITY(1,1) PRIMARY KEY,
    create_date DATETIME DEFAULT GETDATE(),
    city_name VARCHAR(255) NOT NULL
);


CREATE TABLE District (
    id INT IDENTITY(1,1) PRIMARY KEY,
    create_date DATETIME DEFAULT GETDATE(),
    district_name VARCHAR(255) NOT NULL,
    city_id INT NOT NULL,
    CONSTRAINT FK_District_City FOREIGN KEY (city_id) REFERENCES City(id)
);

CREATE TABLE Neighborhood (
    id INT IDENTITY(1,1) PRIMARY KEY,
    create_date DATETIME DEFAULT GETDATE(),
    neighborhood_name VARCHAR(255) NOT NULL,
    district_id INT NOT NULL,
    CONSTRAINT FK_Neighborhood_District FOREIGN KEY (district_id) REFERENCES District(id)
);

CREATE TABLE Address (
    id INT IDENTITY(1,1) PRIMARY KEY,
    create_date DATETIME DEFAULT GETDATE(),
    neighborhood_id INT NOT NULL,
    street VARCHAR(255) NOT NULL,
    CONSTRAINT FK_Address_Neighborhood FOREIGN KEY (neighborhood_id) REFERENCES Neighborhood(id)
);


CREATE TABLE [User] (
    id INT IDENTITY(1,1) PRIMARY KEY,
    create_date DATETIME DEFAULT GETDATE(),
    username VARCHAR(255) UNIQUE NOT NULL,
    [password] VARCHAR(255) NOT NULL
);

CREATE TABLE Account (
    id INT IDENTITY(1,1) PRIMARY KEY,
    create_date DATETIME DEFAULT GETDATE(),
    account_no VARCHAR(255) UNIQUE NOT NULL,
    iban VARCHAR(255) UNIQUE NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    currency_code VARCHAR(3) NOT NULL,
    CONSTRAINT FK_Account_Currency FOREIGN KEY (currency_code) REFERENCES CurrencyType(currency_code)
);


CREATE TABLE Customer (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT UNIQUE NOT NULL,
    create_date DATETIME DEFAULT GETDATE(),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    tckn VARCHAR(11) UNIQUE NOT NULL,
    birth_of_date DATE NOT NULL,
    address_id INT NOT NULL,
    account_id INT NOT NULL,
    CONSTRAINT FK_Customer_User FOREIGN KEY (user_id) REFERENCES [User](id),
    CONSTRAINT FK_Customer_Address FOREIGN KEY (address_id) REFERENCES Address(id),
    CONSTRAINT FK_Customer_Account FOREIGN KEY (account_id) REFERENCES Account(id)
);

CREATE TABLE Gallerist (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT UNIQUE NOT NULL,
    create_date DATETIME DEFAULT GETDATE(),
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    address_id INT NOT NULL,
    CONSTRAINT FK_Gallerist_User FOREIGN KEY (user_id) REFERENCES [User](id),
    CONSTRAINT FK_Gallerist_Address FOREIGN KEY (address_id) REFERENCES Address(id)
);


CREATE TABLE Car (
    id INT IDENTITY(1,1) PRIMARY KEY,
    create_date DATETIME DEFAULT GETDATE(),
    plaka VARCHAR(255) UNIQUE NOT NULL,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    production_year INT NOT NULL,
    price DECIMAL(19,2) NOT NULL,
    damage_price DECIMAL(19,2) NOT NULL,
    currency_code VARCHAR(3) NOT NULL,
    status_code VARCHAR(50) NOT NULL,
    CONSTRAINT FK_Car_Currency FOREIGN KEY (currency_code) REFERENCES CurrencyType(currency_code),
    CONSTRAINT FK_Car_Status FOREIGN KEY (status_code) REFERENCES CarStatusType(status_code)
);

CREATE TABLE Gallerist_Car (
    id INT IDENTITY(1,1) PRIMARY KEY,
    create_date DATETIME DEFAULT GETDATE(),
    gallerist_id INT NOT NULL,
    car_id INT NOT NULL,
    CONSTRAINT FK_GalleristCar_Gallerist FOREIGN KEY (gallerist_id) REFERENCES Gallerist(id),
    CONSTRAINT FK_GalleristCar_Car FOREIGN KEY (car_id) REFERENCES Car(id),
    CONSTRAINT UQ_GalleristCar UNIQUE (gallerist_id, car_id)
);

CREATE TABLE Saled_Car (
    id INT IDENTITY(1,1) PRIMARY KEY,
    create_date DATETIME DEFAULT GETDATE(),
    gallerist_id INT,
    car_id INT NOT NULL,
    customer_id INT NOT NULL,
    CONSTRAINT FK_SaledCar_Gallerist FOREIGN KEY (gallerist_id) REFERENCES Gallerist(id),
    CONSTRAINT FK_SaledCar_Car FOREIGN KEY (car_id) REFERENCES Car(id),
    CONSTRAINT FK_SaledCar_Customer FOREIGN KEY (customer_id) REFERENCES Customer(id),
    CONSTRAINT UQ_SaledCar UNIQUE (gallerist_id, car_id, customer_id)
);

CREATE TABLE CarImage (
    id INT IDENTITY(1,1) PRIMARY KEY,
    car_id INT NOT NULL,
    image_url VARCHAR(500) NOT NULL,
    create_date DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_CarImage_Car FOREIGN KEY (car_id) REFERENCES Car(id)
);