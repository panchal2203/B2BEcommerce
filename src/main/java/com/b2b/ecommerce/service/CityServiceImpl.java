package com.b2b.ecommerce.service;

import com.b2b.ecommerce.dto.CityDTO;
import com.b2b.ecommerce.entity.City;
import com.b2b.ecommerce.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public String addCity(CityDTO cityDTO) {
        City city = convertToEntity(cityDTO);
        cityRepository.save(city);
        return "City added successfully!";
    }

    @Override
    public String removeCity(long cityId) {
        cityRepository.deleteById(cityId);
        return "City removed successfully!";
    }

    @Override
    public String updateCity(long cityId, CityDTO cityDTO) {
        City city = cityRepository.findById(cityId).orElseThrow(() -> new RuntimeException("City not found"));
        updateEntityFromDTO(cityDTO, city);
        cityRepository.save(city);
        return "City updated successfully!";
    }

    @Override
    public List<CityDTO> getAllCities() {
        return cityRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CityDTO convertToDTO(City city) {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setCode(city.getCode());
        cityDTO.setCityName(city.getCityName());
        cityDTO.setZipCode(city.getZipCode());
        return cityDTO;
    }

    private City convertToEntity(CityDTO cityDTO) {
        City city = new City();
        city.setId(cityDTO.getId());
        city.setCode(cityDTO.getCode());
        city.setCityName(cityDTO.getCityName());
        city.setZipCode(cityDTO.getZipCode());
        return city;
    }

    private void updateEntityFromDTO(CityDTO cityDTO, City city) {
        city.setCode(cityDTO.getCode());
        city.setCityName(cityDTO.getCityName());
        city.setZipCode(cityDTO.getZipCode());
    }
}
