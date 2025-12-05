package com.mahir.Service;

import com.mahir.Entity.City;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;

import com.mahir.Service.imp.ICityService;

@Service
public class CityService implements  ICityService {

    private final JdbcTemplate jdbcTemplate;

    public CityService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public City saveCity(City city) {
        String sql = "INSERT INTO City (city_name, create_date) VALUES (?, ?)";
        city.setCreateDate(LocalDateTime.now());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, city.getCityName());
            ps.setObject(2, city.getCreateDate());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            city.setId(keyHolder.getKey().intValue());
        }

        return city;
    }

   
   
}