package com.mahir.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mahir.DTO.PhotoRequest;

@Service
public class CarImageService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveCarImageRecord(PhotoRequest photoRequest) {
        
       
        String dbImageUrl =photoRequest.getName();
        
        String sql = "INSERT INTO CarImage (car_id, image_url) VALUES (?, ?)";
        
        jdbcTemplate.update(sql, photoRequest.getCar_id(), dbImageUrl);
        
        System.out.println("Veritabanına kaydedildi: " + dbImageUrl);
    }
}