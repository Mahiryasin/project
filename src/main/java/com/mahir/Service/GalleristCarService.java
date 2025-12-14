package com.mahir.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.mahir.DTO.GalleristCarRequest;
import com.mahir.Entity.GalleristCar;
import com.mahir.Service.imp.IGalleristCarService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GalleristCarService implements IGalleristCarService {

    private final JdbcTemplate jdbcTemplate;

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
    public GalleristCar saveGalleristCar(GalleristCarRequest galleristCarRequest) {

        String findGalleristIdSql = "SELECT id FROM Gallerist WHERE user_id = ?";
        Integer galleristId = null;

        try {
            galleristId = jdbcTemplate.queryForObject(findGalleristIdSql, Integer.class,
                    galleristCarRequest.getUserId());
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Bu kullanıcı bir galerici değil! (Gallerist kaydı bulunamadı)");
        }

        if (galleristId == null) {
            throw new RuntimeException("Galerici ID'si alınamadı.");
        }

        String checkCarSql = "SELECT COUNT(*) FROM Car WHERE id = ?";
        Integer carCount = jdbcTemplate.queryForObject(checkCarSql, Integer.class, galleristCarRequest.getCarId());

        if (carCount == null || carCount == 0) {
            throw new RuntimeException("Car not found (Böyle bir araba yok)");
        }

        String sql = "INSERT INTO Gallerist_Car (create_date, gallerist_id, car_id) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        final int finalGalleristId = galleristId;
        final LocalDateTime now = LocalDateTime.now();

        try {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(java.sql.Connection con) throws java.sql.SQLException {
                    PreparedStatement ps = con.prepareStatement(sql, new String[] { "id" });
                    ps.setTimestamp(1, Timestamp.valueOf(now));
                    ps.setInt(2, finalGalleristId);
                    ps.setInt(3, galleristCarRequest.getCarId());
                    return ps;
                }
            }, keyHolder);
        } catch (DuplicateKeyException e) {
            throw new RuntimeException("Bu araç zaten listenizde ekli.");
        }

        GalleristCar newGalleristCar = new GalleristCar();
        newGalleristCar.setId(keyHolder.getKey().intValue());
        newGalleristCar.setCreateDate(now);
        newGalleristCar.setGalleristId(finalGalleristId);
        newGalleristCar.setCarId(galleristCarRequest.getCarId());

        return newGalleristCar;
    }

    @Override
    public List<GalleristCar> getAllGalleristCars() {
        String sql = "SELECT id, create_date, gallerist_id, car_id FROM Gallerist_Car";
        return jdbcTemplate.query(sql, galleristCarRowMapper);
    }

    @Override
    public List<com.mahir.DTO.GalleristCarDetailsDTO> getSalableCarsByUserId(int userId) {

        String sql = "SELECT " +
                "g.first_name, g.last_name, " +
                "c.id, c.plaka, c.brand, c.model, c.production_year, " +
                "c.price, c.damage_price, c.currency_code, c.status_code, c.create_date, " +
                "(SELECT TOP 1 image_url FROM CarImage ci WHERE ci.car_id = c.id) AS image_url " +

                "FROM Gallerist_Car gc " +
                "INNER JOIN Gallerist g ON gc.gallerist_id = g.id " +
                "INNER JOIN Car c ON gc.car_id = c.id " +
                "WHERE g.user_id = ? " +
                "AND c.status_code = 'SALABLE'";

        return jdbcTemplate.query(sql, new Object[] { userId }, (rs, rowNum) -> {
            return mapToDto(rs);
        });
    }

    @Override
    public int getSalableCarCountByUserId(int userId) {
        String sql = "SELECT COUNT(*) " +
                "FROM Gallerist_Car gc " +
                "INNER JOIN Gallerist g ON gc.gallerist_id = g.id " +
                "INNER JOIN Car c ON gc.car_id = c.id " +
                "WHERE g.user_id = ? " +
                "AND c.status_code = 'SALABLE'";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        return count != null ? count : 0;
    }

    @Override
    public List<com.mahir.DTO.GalleristCarDetailsDTO> getNewestCarsByUserId(int userId) {

        String sql = "SELECT " +
                "g.first_name, g.last_name, " +
                "c.id, c.plaka, c.brand, c.model, c.production_year, " +
                "c.price, c.damage_price, c.currency_code, c.status_code, c.create_date, " +
                "(SELECT TOP 1 image_url FROM CarImage ci WHERE ci.car_id = c.id) AS image_url " +

                "FROM Gallerist_Car gc " +
                "INNER JOIN Gallerist g ON gc.gallerist_id = g.id " +
                "INNER JOIN Car c ON gc.car_id = c.id " +
                "WHERE g.user_id = ? " +
                "AND c.status_code = 'SALABLE' " +
                "ORDER BY c.create_date DESC";

        return jdbcTemplate.query(sql, new Object[] { userId }, (rs, rowNum) -> {
            return mapToDto(rs);
        });
    }

    @Override
    public List<com.mahir.DTO.GalleristCarDetailsDTO> getOldestCarsByUserId(int userId) {

        String sql = "SELECT " +
                "g.first_name, g.last_name, " +
                "c.id, c.plaka, c.brand, c.model, c.production_year, " +
                "c.price, c.damage_price, c.currency_code, c.status_code, c.create_date, " +
                "(SELECT TOP 1 image_url FROM CarImage ci WHERE ci.car_id = c.id) AS image_url " +

                "FROM Gallerist_Car gc " +
                "INNER JOIN Gallerist g ON gc.gallerist_id = g.id " +
                "INNER JOIN Car c ON gc.car_id = c.id " +
                "WHERE g.user_id = ? " +
                "AND c.status_code = 'SALABLE' " +
                "ORDER BY c.create_date ASC";

        return jdbcTemplate.query(sql, new Object[] { userId }, (rs, rowNum) -> {
            return mapToDto(rs);
        });
    }

    private com.mahir.DTO.GalleristCarDetailsDTO mapToDto(ResultSet rs) throws SQLException {
        com.mahir.DTO.GalleristCarDetailsDTO dto = new com.mahir.DTO.GalleristCarDetailsDTO();

        dto.setGalleristFirstName(rs.getString("first_name"));
        dto.setGalleristLastName(rs.getString("last_name"));
        dto.setCarId(rs.getInt("id"));
        dto.setPlaka(rs.getString("plaka"));
        dto.setBrand(rs.getString("brand"));
        dto.setModel(rs.getString("model"));
        dto.setProductionYear(rs.getInt("production_year"));
        dto.setPrice(rs.getBigDecimal("price"));
        dto.setDamagePrice(rs.getBigDecimal("damage_price"));
        dto.setCurrencyCode(rs.getString("currency_code"));
        dto.setStatusCode(rs.getString("status_code"));
        dto.setImageUrl(rs.getString("image_url"));

        if (rs.getTimestamp("create_date") != null) {
            dto.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
        }

        return dto;
    }

    @Override
    @Transactional
    public void deleteCarById(int carId) {

        String deleteRelationSql = "DELETE FROM Gallerist_Car WHERE car_id = ?";
        jdbcTemplate.update(deleteRelationSql, carId);

        String deleteImagesSql = "DELETE FROM CarImage WHERE car_id = ?";
        jdbcTemplate.update(deleteImagesSql, carId);

        String deleteCarSql = "DELETE FROM Car WHERE id = ?";
        jdbcTemplate.update(deleteCarSql, carId);
    }
}