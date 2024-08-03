package com.b2b.ecommerce.controller;
import com.b2b.ecommerce.dto.SellerDTO;
import com.b2b.ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/addOrUpdateSeller")
    public ResponseEntity<String> addOrUpdateSeller(@RequestBody SellerDTO sellerDTO) {
        boolean success = sellerService.addOrUpdateSeller(sellerDTO);
        if (success) {
            return ResponseEntity.ok("Seller added/updated successfully!");
        } else {
            return ResponseEntity.status(400).body("Error adding/updating seller!");
        }
    }

    @GetMapping("/getSellerDetails/{sellerId}")
    public ResponseEntity<SellerDTO> getSellerDetails(@PathVariable Long sellerId) {
        SellerDTO sellerDTO = sellerService.getSellerDetails(sellerId);
        return ResponseEntity.ok(sellerDTO);
    }

    @GetMapping("/getAllSellers")
    public ResponseEntity<List<SellerDTO>> getAllSellers() {
        List<SellerDTO> sellers = sellerService.getAllSellers();
        return ResponseEntity.ok(sellers);
    }
}
