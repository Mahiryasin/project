package com.mahir.Service;

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
import com.mahir.Service.imp.ICarService;


@Service
public class CarService implements  ICarService {

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
                System.out.println(car.getBrand()+" Brandddddddddddddddddd");
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

}
