package com.mahir.Controller;

import com.mahir.Controller.imp.INeighborhoodController;
import com.mahir.Entity.Neighborhood;
import com.mahir.Service.imp.INeighborhoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/neighborhood")
@CrossOrigin(origins = "http://localhost:5174") 
public class NeighborhoodController implements INeighborhoodController {

    private final INeighborhoodService neighborhoodService;

    public NeighborhoodController(INeighborhoodService neighborhoodService) {
        this.neighborhoodService = neighborhoodService;
    }

    @PostMapping("/save")
    @Override
    public ResponseEntity<Neighborhood> save(@RequestBody Neighborhood neighborhood) {
        Neighborhood savedNeighborhood = neighborhoodService.save(neighborhood);
        return ResponseEntity.ok(savedNeighborhood);
    }
}