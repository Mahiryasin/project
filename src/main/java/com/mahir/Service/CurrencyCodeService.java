package com.mahir.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mahir.Entity.CurrencyType;
import com.mahir.Service.imp.ICurrencyCodeService;

@Service
public class CurrencyCodeService implements ICurrencyCodeService {

    private final JdbcTemplate jdbcTemplate;

    public CurrencyCodeService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CurrencyType saveCurrencyType(CurrencyType currencyType) {
        String sql = "INSERT INTO CurrencyType (create_date, currency_code, description) VALUES (?, ?, ?)";

        LocalDateTime now = LocalDateTime.now();
        jdbcTemplate.update(sql,
                Timestamp.valueOf(now),
                currencyType.getCurrencyCode(),
                currencyType.getDescription()
        );

        Integer id = jdbcTemplate.queryForObject("SELECT SCOPE_IDENTITY()", Integer.class);
        currencyType.setId(id);
        currencyType.setCreateDate(now);

        return currencyType;
    }
}
