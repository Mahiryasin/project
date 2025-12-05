package com.mahir.Service;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.mahir.Entity.Account;
import com.mahir.Service.imp.IAccountService;

@Service
public class AccountService implements IAccountService {

    private final JdbcTemplate jdbcTemplate;

    public AccountService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

   @Override
public Account saveAccount(Account account) {
    String sql = "INSERT INTO Account (create_date, account_no, iban, amount, currency_code) VALUES (?, ?, ?, ?, ?)";

    LocalDateTime now = LocalDateTime.now();
    account.setCreateDate(now); 

    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        ps.setTimestamp(1, Timestamp.valueOf(now));
        ps.setString(2, account.getAccountNo());
        ps.setString(3, account.getIban());
        ps.setBigDecimal(4, account.getAmount());
        ps.setString(5, account.getCurrencyCode());
        
        return ps;
    }, keyHolder);

    if (keyHolder.getKey() != null) {
        account.setId(keyHolder.getKey().intValue());
    }

    return account;
}
}
