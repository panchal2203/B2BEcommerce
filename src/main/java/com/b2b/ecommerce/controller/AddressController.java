package com.b2b.ecommerce.controller;

// AddressController.java
import com.b2b.ecommerce.dto.AddressDTO;
import com.b2b.ecommerce.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // Add a new address
    @PostMapping
    public ResponseEntity<String> addAddress(@RequestBody AddressDTO addressDTO) {
        String response = addressService.addAddress(addressDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Update an existing address
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAddress(@PathVariable long id, @RequestBody AddressDTO addressDTO) {
        String response = addressService.updateAddress(id, addressDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Delete an address
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable long id) {
        String response = addressService.deleteAddress(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Get all addresses for an account
    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<AddressDTO>> getAllAddressesByAccountId(@PathVariable long accountId) {
        List<AddressDTO> addresses = addressService.getAllAddressesByAccountId(accountId);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    // Get address by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable long id) {
        AddressDTO address = addressService.getAddressById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }
}
