package com.mahir.Service;

import com.mahir.DTO.AddressDetailDTO;
import com.mahir.Entity.Address;
import com.mahir.Service.imp.IAddressService;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class AddressService implements IAddressService{

    private final JdbcTemplate jdbcTemplate;

    public AddressService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Address saveAddress(Address address) {
       
        String sql = "INSERT INTO Address (create_date, neighborhood_id, street) VALUES (?, ?, ?)";

        LocalDateTime now = LocalDateTime.now();
        address.setCreateDate(now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
           
            ps.setTimestamp(1, Timestamp.valueOf(now));
            
           
            ps.setInt(2, address.getNeighborhoodId());
            
           
            ps.setString(3, address.getStreet());
            
            return ps;
        }, keyHolder);

        
        if (keyHolder.getKey() != null) {
            address.setId(keyHolder.getKey().intValue());
        }

        return address;
    }

    
    @Override
    public AddressDetailDTO getAddressByUserId(long userId) {
      
        String sql = "SELECT ci.city_name, d.district_name, n.neighborhood_name, a.street " +
                     "FROM Customer cu " +
                     "JOIN Address a ON cu.address_id = a.id " +
                     "JOIN Neighborhood n ON a.neighborhood_id = n.id " +
                     "JOIN District d ON n.district_id = d.id " +
                     "JOIN City ci ON d.city_id = ci.id " +
                     "WHERE cu.user_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId}, (rs, rowNum) -> 
                new AddressDetailDTO(
                    rs.getString("city_name"),
                    rs.getString("district_name"),
                    rs.getString("neighborhood_name"),
                    rs.getString("street") 
                )
            );
        } catch (Exception e) {
            return null;
        }
    }
}