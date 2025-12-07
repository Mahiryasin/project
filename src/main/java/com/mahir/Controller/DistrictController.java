package com.mahir.Controller;

import com.mahir.Controller.imp.IDistrictController;
import com.mahir.Entity.District;
import com.mahir.Service.imp.IDistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/district")
@CrossOrigin(origins = "http://localhost:5173") 
public class DistrictController implements IDistrictController {

    private final IDistrictService districtService;

    public DistrictController(IDistrictService districtService) {
        this.districtService = districtService;
    }

    @PostMapping("/save")
    @Override
    public ResponseEntity<District> save(@RequestBody District district) {
        District savedDistrict = districtService.save(district);
        return ResponseEntity.ok(savedDistrict);
    }
}