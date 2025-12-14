package com.mahir.Service;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.mahir.Entity.Car;
import com.mahir.DTO.CarFilterRequest;
import com.mahir.DTO.CarWithOwnerDTO;
import com.mahir.Service.imp.ICarService;

@Service
public class CarService implements ICarService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Car saveCar(Car car) {
        car.setCreateDate(LocalDateTime.now());

        String sql = "INSERT INTO Car (create_date, plaka, brand, model, production_year, price, damage_price, currency_code, status_code) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws java.sql.SQLException {
                PreparedStatement ps = con.prepareStatement(sql, new String[] { "id" });
                ps.setTimestamp(1, Timestamp.valueOf(car.getCreateDate()));
                ps.setString(2, car.getPlaka());
                ps.setString(3, car.getBrand());
                ps.setString(4, car.getModel());
                ps.setInt(5, car.getProductionYear());
                ps.setBigDecimal(6, car.getPrice());
                ps.setBigDecimal(7, car.getDamagePrice());
                ps.setString(8, car.getCurrencyCode());
                ps.setString(9, car.getStatusCode());
                System.out.println(car.getBrand() + " Brandddddddddddddddddd");
                return ps;
            }
        }, keyHolder);

        car.setId(keyHolder.getKey().intValue());
        return car;
    }

    public List<Car> getAllCars() {
        String sql = "SELECT * FROM Car";
        RowMapper<Car> rowMapper = (rs, rowNum) -> {
            Car car = new Car();
            car.setId(rs.getInt("id"));
            car.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
            car.setPlaka(rs.getString("plaka"));
            car.setBrand(rs.getString("brand"));
            car.setModel(rs.getString("model"));
            car.setProductionYear(rs.getInt("production_year"));
            car.setPrice(rs.getBigDecimal("price"));
            car.setDamagePrice(rs.getBigDecimal("damage_price"));

            car.setCurrencyCode(rs.getString("currency_code"));
            car.setStatusCode(rs.getString("status_code"));
            return car;
        };
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public List<Car> changeCarDTO(List<Car> cars) {
        List<Car> carList = new ArrayList<>();
        for (Car car : cars) {
            Car copy = new Car();
            copy.setId(car.getId());
            copy.setPlaka(car.getPlaka());
            copy.setBrand(car.getBrand());
            copy.setModel(car.getModel());
            copy.setProductionYear(car.getProductionYear());
            copy.setPrice(car.getPrice());
            copy.setDamagePrice(car.getDamagePrice());
            copy.setCurrencyCode(car.getCurrencyCode());
            copy.setStatusCode(car.getStatusCode());
            copy.setCreateDate(car.getCreateDate());
            carList.add(copy);
        }
        return carList;
    }

 @Override
public List<CarWithOwnerDTO> getCarsNotOwnedByUser(int userId) {

    String sql =
            "SELECT c.id, c.create_date, c.plaka, c.brand, c.model, c.production_year, " +
            "c.price, c.damage_price, c.currency_code, c.status_code, " +
            "g.user_id AS owner_user_id, " +
            "(SELECT TOP 1 ci.image_url FROM CarImage ci WHERE ci.car_id = c.id) AS image_url " +
            "FROM Car c " + 
            "INNER JOIN Gallerist_Car gc ON gc.car_id = c.id " +
            "INNER JOIN Gallerist g ON g.id = gc.gallerist_id " +
            "WHERE c.status_code = 'SALABLE' " +
            "AND g.user_id <> ?";

    return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
        Car car = new Car();
        car.setId(rs.getInt("id"));

        java.sql.Timestamp ts = rs.getTimestamp("create_date");
        if (ts != null) car.setCreateDate(ts.toLocalDateTime());

        car.setPlaka(rs.getString("plaka"));
        car.setBrand(rs.getString("brand"));
        car.setModel(rs.getString("model"));
        car.setProductionYear(rs.getInt("production_year"));
        car.setPrice(rs.getBigDecimal("price"));
        car.setDamagePrice(rs.getBigDecimal("damage_price"));
        car.setCurrencyCode(rs.getString("currency_code"));
        car.setStatusCode(rs.getString("status_code"));
        
        CarWithOwnerDTO dto = new CarWithOwnerDTO();
        dto.setCar(car);
        dto.setOwnerUserId(rs.getInt("owner_user_id"));
        
            dto.setFirstImageUr(rs.getString("image_url"));

        return dto;
    });
}
@Override
public List<CarWithOwnerDTO> getFilteredCars(int userId, CarFilterRequest filter) {
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT c.id, c.create_date, c.plaka, c.brand, c.model, c.production_year, ");
    sql.append("c.price, c.damage_price, c.currency_code, c.status_code, ");
    sql.append("g.user_id AS owner_user_id, ");
    sql.append("(SELECT TOP 1 ci.image_url FROM CarImage ci WHERE ci.car_id = c.id) AS image_url ");
    sql.append("FROM Car c ");
    sql.append("INNER JOIN Gallerist_Car gc ON gc.car_id = c.id ");
    sql.append("INNER JOIN Gallerist g ON g.id = gc.gallerist_id ");
    sql.append("INNER JOIN Address a ON g.address_id = a.id ");
    sql.append("INNER JOIN Neighborhood n ON a.neighborhood_id = n.id ");
    sql.append("INNER JOIN District d ON n.district_id = d.id ");
    sql.append("INNER JOIN City cty ON d.city_id = cty.id ");
    
    sql.append("WHERE c.status_code = 'SALABLE' ");
    sql.append("AND g.user_id <> ? ");

    List<Object> params = new ArrayList<>();
    params.add(userId);

    if (filter.getMinPrice() != null && filter.getMaxPrice() != null) {
        sql.append("AND c.price BETWEEN ? AND ? ");
        params.add(filter.getMinPrice());
        params.add(filter.getMaxPrice());
    } else if (filter.getMinPrice() != null) {
        sql.append("AND c.price >= ? ");
        params.add(filter.getMinPrice());
    } else if (filter.getMaxPrice() != null) {
        sql.append("AND c.price <= ? ");
        params.add(filter.getMaxPrice());
    }

    if (filter.getMinYear() != null && filter.getMaxYear() != null) {
        sql.append("AND c.production_year BETWEEN ? AND ? ");
        params.add(filter.getMinYear());
        params.add(filter.getMaxYear());
    } else if (filter.getMinYear() != null) {
        sql.append("AND c.production_year >= ? ");
        params.add(filter.getMinYear());
    } else if (filter.getMaxYear() != null) {
        sql.append("AND c.production_year <= ? ");
        params.add(filter.getMaxYear());
    }

    if (filter.getCityName() != null && !filter.getCityName().isEmpty()) {
        sql.append("AND cty.city_name = ? ");
        params.add(filter.getCityName());
    }

    if (filter.getCurrencyCode() != null && !filter.getCurrencyCode().isEmpty()) {
        sql.append("AND c.currency_code = ? ");
        params.add(filter.getCurrencyCode());
    }

    return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) -> {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        
        java.sql.Timestamp ts = rs.getTimestamp("create_date");
        if (ts != null) car.setCreateDate(ts.toLocalDateTime());

        car.setPlaka(rs.getString("plaka"));
        car.setBrand(rs.getString("brand"));
        car.setModel(rs.getString("model"));
        car.setProductionYear(rs.getInt("production_year"));
        car.setPrice(rs.getBigDecimal("price"));
        car.setDamagePrice(rs.getBigDecimal("damage_price"));
        car.setCurrencyCode(rs.getString("currency_code"));
        car.setStatusCode(rs.getString("status_code"));

        CarWithOwnerDTO dto = new CarWithOwnerDTO();
        dto.setCar(car);
        dto.setOwnerUserId(rs.getInt("owner_user_id"));
        
       
        dto.setFirstImageUr(rs.getString("image_url")); 

        return dto;
    });
}
@Override
public CarWithOwnerDTO getMinPriceCar(int userId, String currencyCode) {
    
    String sql = "SELECT c.id, c.create_date, c.plaka, c.brand, c.model, c.production_year, " +
                 "c.price, c.damage_price, c.currency_code, c.status_code, " +
                 "g.user_id AS owner_user_id, " +
                 "(SELECT TOP 1 ci.image_url FROM CarImage ci WHERE ci.car_id = c.id) AS image_url " +
                 "FROM Car c " +
                 "INNER JOIN Gallerist_Car gc ON gc.car_id = c.id " +
                 "INNER JOIN Gallerist g ON g.id = gc.gallerist_id " +
                 "WHERE c.price = ( " +
                 "    SELECT MIN(c2.price) " +
                 "    FROM Car c2 " +
                 "    INNER JOIN Gallerist_Car gc2 ON gc2.car_id = c2.id " +
                 "    INNER JOIN Gallerist g2 ON g2.id = gc2.gallerist_id " +
                 "    WHERE c2.status_code = 'SALABLE' " +
                 "    AND c2.currency_code = ? " +
                 "    AND g2.user_id <> ? " +
                 ")";

    List<CarWithOwnerDTO> result = jdbcTemplate.query(sql, new Object[]{currencyCode, userId}, (rs, rowNum) -> {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        
        java.sql.Timestamp ts = rs.getTimestamp("create_date");
        if (ts != null) car.setCreateDate(ts.toLocalDateTime());

        car.setPlaka(rs.getString("plaka"));
        car.setBrand(rs.getString("brand"));
        car.setModel(rs.getString("model"));
        car.setProductionYear(rs.getInt("production_year"));
        car.setPrice(rs.getBigDecimal("price"));
        car.setDamagePrice(rs.getBigDecimal("damage_price"));
        car.setCurrencyCode(rs.getString("currency_code"));
        car.setStatusCode(rs.getString("status_code"));

        CarWithOwnerDTO dto = new CarWithOwnerDTO();
        dto.setCar(car);
        dto.setOwnerUserId(rs.getInt("owner_user_id"));
        dto.setFirstImageUr(rs.getString("image_url"));

        return dto;
    });

    if (result.isEmpty()) {
        return null;
    }
    return result.get(0);
}
   @Override
public CarWithOwnerDTO getMaxPrice(int userId, String currencyCode) {
    
   
    
    String sql = "SELECT c.id, c.create_date, c.plaka, c.brand, c.model, c.production_year, " +
                 "c.price, c.damage_price, c.currency_code, c.status_code, " +
                 "g.user_id AS owner_user_id, " +
                 "(SELECT TOP 1 ci.image_url FROM CarImage ci WHERE ci.car_id = c.id) AS image_url " +
                 "FROM Car c " +
                 "INNER JOIN Gallerist_Car gc ON gc.car_id = c.id " +
                 "INNER JOIN Gallerist g ON g.id = gc.gallerist_id " +
                 "WHERE c.price = ( " +
                 "    SELECT MAX(c2.price) " +
                 "    FROM Car c2 " +
                 "    INNER JOIN Gallerist_Car gc2 ON gc2.car_id = c2.id " +
                 "    INNER JOIN Gallerist g2 ON g2.id = gc2.gallerist_id " +
                 "    WHERE c2.status_code = 'SALABLE' " +
                 "    AND c2.currency_code = ? " +
                 "    AND g2.user_id <> ? " +
                 ")";

    Object[] params = new Object[]{currencyCode, userId};

    List<CarWithOwnerDTO> result = jdbcTemplate.query(sql, params, (rs, rowNum) -> {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        
        java.sql.Timestamp ts = rs.getTimestamp("create_date");
        if (ts != null) car.setCreateDate(ts.toLocalDateTime());

        car.setPlaka(rs.getString("plaka"));
        car.setBrand(rs.getString("brand"));
        car.setModel(rs.getString("model"));
        car.setProductionYear(rs.getInt("production_year"));
        car.setPrice(rs.getBigDecimal("price"));
        car.setDamagePrice(rs.getBigDecimal("damage_price"));
        car.setCurrencyCode(rs.getString("currency_code"));
        car.setStatusCode(rs.getString("status_code"));

        CarWithOwnerDTO dto = new CarWithOwnerDTO();
        dto.setCar(car);
        dto.setOwnerUserId(rs.getInt("owner_user_id"));
        dto.setFirstImageUr(rs.getString("image_url"));

        return dto;
    });

    if (result.isEmpty()) {
        return null;
    }
    
    return result.get(0);
}
}
