package com.mahir.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.mahir.Entity.User;
import com.mahir.Service.imp.IUserService;

@Service
public class UserService implements IUserService {

    private final JdbcTemplate jdbcTemplate;

    public UserService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM [User] WHERE username = ?";
        
        try {
            User user = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                return u;
            }, username);
            
            return Optional.ofNullable(user);
            
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}