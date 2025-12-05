package com.mahir.Controller.imp;

import com.mahir.Entity.District;
import org.springframework.http.ResponseEntity;

public interface IDistrictController {
    
    ResponseEntity<District> save(District district);
}