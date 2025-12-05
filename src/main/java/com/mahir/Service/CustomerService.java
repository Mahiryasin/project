package com.mahir.Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.mahir.Entity.Customer;
import com.mahir.Service.imp.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Customer saveCustomer(Customer customer) {
        customer.setCreateDate(LocalDateTime.now());

      
        String checkUserSql = "SELECT COUNT(*) FROM [User] WHERE id = ?";
        Integer userCount = jdbcTemplate.queryForObject(checkUserSql, Integer.class, customer.getUserId());
        
        if (userCount == null || userCount == 0) {
            throw new RuntimeException("User not found (Böyle bir kullanıcı yok)");
        }

        String checkAddressSql = "SELECT COUNT(*) FROM Address WHERE id = ?";
        Integer addressCount = jdbcTemplate.queryForObject(checkAddressSql, Integer.class, customer.getAddressId());
        if (addressCount == null || addressCount == 0) {
            throw new RuntimeException("Address not found");
        }

        String checkAccountSql = "SELECT COUNT(*) FROM Account WHERE id = ?";
        Integer accountCount = jdbcTemplate.queryForObject(checkAccountSql, Integer.class, customer.getAccountId());
        if (accountCount == null || accountCount == 0) {
            throw new RuntimeException("Account not found");
        }

        String sql = "INSERT INTO Customer (user_id, create_date, first_name, last_name, tckn, birth_of_date, address_id, account_id) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                   
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(java.sql.Connection con) throws java.sql.SQLException {
                PreparedStatement ps = con.prepareStatement(sql, new String[] { "id" });
                
                ps.setInt(1, customer.getUserId()); 
                ps.setTimestamp(2, Timestamp.valueOf(customer.getCreateDate()));
                ps.setString(3, customer.getFirstName());
                ps.setString(4, customer.getLastName());
                ps.setString(5, customer.getTckn());
                ps.setDate(6, Date.valueOf(customer.getBirthOfDate()));
                ps.setInt(7, customer.getAddressId());
                ps.setInt(8, customer.getAccountId());
                
                return ps;
            }
        }, keyHolder);

        customer.setId(keyHolder.getKey().intValue());
        return customer;
    }
}