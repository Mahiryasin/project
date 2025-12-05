package com.mahir.Controller.imp;

import com.mahir.Entity.Neighborhood;
import org.springframework.http.ResponseEntity;

public interface INeighborhoodController {
    
    ResponseEntity<Neighborhood> save(Neighborhood neighborhood);
}