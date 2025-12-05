package com.mahir.Service;

import com.mahir.Entity.Neighborhood;
import com.mahir.Service.imp.INeighborhoodService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;

@Service
public class NeighborhoodService implements INeighborhoodService {

    private final JdbcTemplate jdbcTemplate;

    public NeighborhoodService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Neighborhood save(Neighborhood neighborhood) {
        String sql = "INSERT INTO Neighborhood (neighborhood_name, district_id, create_date) VALUES (?, ?, ?)";
        neighborhood.setCreateDate(LocalDateTime.now());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, neighborhood.getNeighborhoodName());
            ps.setInt(2, neighborhood.getDistrictId());
            ps.setObject(3, neighborhood.getCreateDate());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            neighborhood.setId(keyHolder.getKey().intValue());
        }

        return neighborhood;
    }
}