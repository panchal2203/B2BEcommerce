package com.b2b.ecommerce.controller;

import com.b2b.ecommerce.dto.CityDTO;
import com.b2b.ecommerce.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/add")
    public ResponseEntity<String> addCity(@RequestBody CityDTO cityDTO) {
        String status = cityService.addCity(cityDTO);
        return ResponseEntity.ok(status);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeCity(@PathVariable Long id) {
        String status = cityService.removeCity(id);
        return ResponseEntity.ok(status);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCity(@PathVariable Long id, @RequestBody CityDTO cityDTO) {
        String status = cityService.updateCity(id, cityDTO);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CityDTO>> getAllCities() {
        List<CityDTO> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);
    }
}
