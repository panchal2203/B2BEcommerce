package com.b2b.ecommerce.service;

import com.b2b.ecommerce.dto.CityDTO;
import java.util.List;

public interface CityService {
    String addCity(CityDTO cityDTO);
    String removeCity(long cityId);
    String updateCity(long cityId, CityDTO cityDTO);
    List<CityDTO> getAllCities();
}