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

import com.mahir.Entity.Gallerist;
import com.mahir.Service.imp.IGalleristService;

@Service
public class GalleristService implements IGalleristService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GalleristService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Gallerist> galleristRowMapper = new RowMapper<>() {
        @Override
        public Gallerist mapRow(ResultSet rs, int rowNum) throws SQLException {
            Gallerist g = new Gallerist();
            g.setId(rs.getInt("id"));
            
            g.setUserId(rs.getInt("user_id")); 
            
            g.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
            g.setFirstName(rs.getString("first_name"));
            g.setLastName(rs.getString("last_name"));
            g.setAddressId(rs.getInt("address_id"));
            return g;
        }
    };

    @Override
    public Gallerist saveGallerist(Gallerist gallerist) {
        gallerist.setCreateDate(LocalDateTime.now());

        String checkUserSql = "SELECT COUNT(*) FROM [User] WHERE id = ?";
        
        Integer userCount = jdbcTemplate.queryForObject(checkUserSql, Integer.class, gallerist.getUserId()); 
        
        if (userCount == null || userCount == 0) {
            throw new RuntimeException("User not found (Böyle bir kullanıcı yok)");
        }

        String checkAddressSql = "SELECT COUNT(*) FROM Address WHERE id = ?";
        Integer addressCount = jdbcTemplate.queryForObject(checkAddressSql, Integer.class, gallerist.getAddressId());
        
        if (addressCount == null || addressCount == 0) {
            throw new RuntimeException("Address not found (Böyle bir adres yok)");
        }

      
        String sql = "INSERT INTO Gallerist (user_id, create_date, first_name, last_name, address_id) "
                   + "VALUES (?, ?, ?, ?, ?)";
                   
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws java.sql.SQLException {
                PreparedStatement ps = con.prepareStatement(sql, new String[] { "id" });
                
                ps.setInt(1, gallerist.getUserId()); 
                ps.setTimestamp(2, Timestamp.valueOf(gallerist.getCreateDate()));
                ps.setString(3, gallerist.getFirstName());
                ps.setString(4, gallerist.getLastName());
                ps.setInt(5, gallerist.getAddressId());
                
                return ps;
            }
        }, keyHolder);

        gallerist.setId(keyHolder.getKey().intValue());
        return gallerist;
    }

    @Override
    public List<Gallerist> getAllGallerists() {
        String sql = "SELECT id, user_id, create_date, first_name, last_name, address_id FROM Gallerist";
        return jdbcTemplate.query(sql, galleristRowMapper);
    }
}