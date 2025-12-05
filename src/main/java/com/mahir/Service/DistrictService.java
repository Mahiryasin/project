package com.mahir.Service;

import com.mahir.Entity.District;
import com.mahir.Service.imp.IDistrictService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;

@Service
public class DistrictService implements IDistrictService {

    private final JdbcTemplate jdbcTemplate;

    public DistrictService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public District save(District district) {
        String sql = "INSERT INTO District (district_name, city_id, create_date) VALUES (?, ?, ?)";
        district.setCreateDate(LocalDateTime.now());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, district.getDistrictName());
            ps.setInt(2, district.getCityId());
            ps.setObject(3, district.getCreateDate());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            district.setId(keyHolder.getKey().intValue());
        }

        return district;
    }
}