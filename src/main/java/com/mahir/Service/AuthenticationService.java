package com.mahir.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mahir.Entity.User;
import com.mahir.JWT.AuthResponse;
import com.mahir.JWT.JWTService;
import com.mahir.Service.imp.IAuthenticationService;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationProvider authenticationProvider;
    private final JWTService jwtService;

    public AuthenticationService(JdbcTemplate jdbcTemplate,
                                 BCryptPasswordEncoder bCryptPasswordEncoder,
                                 AuthenticationProvider authenticationProvider,
                                 JWTService jwtService) {
        this.jdbcTemplate = jdbcTemplate;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationProvider = authenticationProvider;
        this.jwtService = jwtService;
    }

    @Override
    public User authenticate(User user) {
        String checkSql = "SELECT COUNT(*) FROM [User] WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, user.getUsername());

        if (count != null && count > 0) {
            throw new RuntimeException("Bu kullanıcı adı zaten alınmış: " + user.getUsername());
        }

        user.setCreateDate(LocalDateTime.now());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        String insertSql = "INSERT INTO [User] (username, password, create_date) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int result = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setTimestamp(3, Timestamp.valueOf(user.getCreateDate()));
            return ps;
        }, keyHolder);

        if (result > 0 && keyHolder.getKey() != null) {
            user.setId(keyHolder.getKey().intValue()); 
            return user;
        } else {
            throw new RuntimeException("Kayıt işlemi başarısız oldu.");
        }
    }
@Override
public AuthResponse isAuthenticate(User user) throws Exception {
    try {
        authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
    } catch (Exception e) {
        throw new Exception("Kullanıcı adı veya şifre hatalı!");
    }

    String sql = "SELECT * FROM [User] WHERE username = ?";

    try {
        User user_ = jdbcTemplate.queryForObject(
                sql,
                new BeanPropertyRowMapper<>(User.class),
                user.getUsername()
        );

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtService.GenerateToken(user_));
        authResponse.setId(user_.getId()); 

        return authResponse;

    } catch (Exception e) {
        throw new Exception("Token üretilirken bir hata oluştu: " + e.getMessage());
    }
}

}