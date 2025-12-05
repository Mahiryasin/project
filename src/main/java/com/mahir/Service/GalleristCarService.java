package com.mahir.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.mahir.Entity.GalleristCar;
import com.mahir.Service.imp.IGalleristCarService;

@Service
public class GalleristCarService implements IGalleristCarService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired 
    public GalleristCarService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<GalleristCar> galleristCarRowMapper = new RowMapper<>() {
        @Override
        public GalleristCar mapRow(ResultSet rs, int rowNum) throws SQLException {
            GalleristCar g = new GalleristCar();
            g.setId(rs.getInt("id"));
            g.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
            g.setGalleristId(rs.getInt("gallerist_id"));
            g.setCarId(rs.getInt("car_id"));
            return g;
        }
    };

    @Override
    public GalleristCar saveGalleristCar(GalleristCar galleristCar) {
        galleristCar.setCreateDate(LocalDateTime.now());

        String checkGalleristSql = "SELECT COUNT(*) FROM Gallerist WHERE id = ?";
        Integer galleristCount = jdbcTemplate.queryForObject(checkGalleristSql, Integer.class, galleristCar.getGalleristId());
        
        if (galleristCount == null || galleristCount == 0) {
            throw new RuntimeException("Gallerist not found (Böyle bir galerici yok)");
        }

        String checkCarSql = "SELECT COUNT(*) FROM Car WHERE id = ?";
        Integer carCount = jdbcTemplate.queryForObject(checkCarSql, Integer.class, galleristCar.getCarId());
        
        if (carCount == null || carCount == 0) {
            throw new RuntimeException("Car not found (Böyle bir araba yok)");
        }

     
        

        String sql = "INSERT INTO Gallerist_Car (create_date, gallerist_id, car_id) VALUES (?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws java.sql.SQLException {
                PreparedStatement ps = con.prepareStatement(sql, new String[] { "id" });
                
                ps.setTimestamp(1, Timestamp.valueOf(galleristCar.getCreateDate()));
                ps.setInt(2, galleristCar.getGalleristId());
                ps.setInt(3, galleristCar.getCarId());
                
                return ps;
            }
        }, keyHolder);

        galleristCar.setId(keyHolder.getKey().intValue());
        
        return galleristCar;
    }

    @Override
    public List<GalleristCar> getAllGalleristCars() {
        String sql = "SELECT id, create_date, gallerist_id, car_id FROM Gallerist_Car";
        return jdbcTemplate.query(sql, galleristCarRowMapper);
    }
}