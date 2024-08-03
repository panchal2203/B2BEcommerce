package com.b2b.ecommerce.service;

import com.b2b.ecommerce.dto.ProductDTO;
import com.b2b.ecommerce.dto.SellerDTO;
import com.b2b.ecommerce.entity.Account;
import com.b2b.ecommerce.entity.City;
import com.b2b.ecommerce.entity.Product;
import com.b2b.ecommerce.entity.Seller;
import com.b2b.ecommerce.repository.AccountRepository;
import com.b2b.ecommerce.repository.CityRepository;
import com.b2b.ecommerce.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CityRepository cityRepository;

    public boolean addOrUpdateSeller(SellerDTO sellerDTO) {
        Optional<Account> accountOpt = accountRepository.findById(sellerDTO.getAccountId());
        Optional<City> cityOpt = cityRepository.findById(sellerDTO.getCityId());

        if (accountOpt.isPresent() && cityOpt.isPresent()) {
            Account account = accountOpt.get();
            City city = cityOpt.get();

            Seller seller = account.getSeller(); // Get the existing seller associated with the account

            if (seller == null) {
                seller = new Seller(); // Create a new seller if none exists
                seller.setAccount(account);
            } else if (!seller.getId().equals(sellerDTO.getId())) {
                throw new IllegalStateException("Seller ID mismatch for the account");
            }

            seller.setCompanyName(sellerDTO.getCompanyName());
            seller.setGstNumber(sellerDTO.getGstNumber());
            seller.setSummary(sellerDTO.getSummary());
            seller.setAddress(sellerDTO.getAddress());
            seller.setMobileNumber(sellerDTO.getMobileNumber());
            seller.setEmailId(sellerDTO.getEmailId());
            seller.setName(sellerDTO.getName());
            seller.setCity(city);

            sellerRepository.save(seller);
            return true;
        }
        return false;
    }

    public SellerDTO getSellerDetails(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(() -> new RuntimeException("Seller not found"));

        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setId(seller.getId());
        sellerDTO.setCompanyName(seller.getCompanyName());
        sellerDTO.setGstNumber(seller.getGstNumber());
        sellerDTO.setSummary(seller.getSummary());
        sellerDTO.setAddress(seller.getAddress());
        sellerDTO.setMobileNumber(seller.getMobileNumber());
        sellerDTO.setEmailId(seller.getEmailId());
        sellerDTO.setName(seller.getName());
        sellerDTO.setCityId(seller.getCity().getId());
        sellerDTO.setCityName(seller.getCity().getCityName());
        sellerDTO.setAccountId(seller.getAccount().getId());
        sellerDTO.setAccountName(seller.getAccount().getFirstName() + " " + seller.getAccount().getLastName());
        // Assuming a method exists to map Product to ProductDTO
        sellerDTO.setProducts(seller.getProducts().stream().map(this::mapToProductDTO).collect(Collectors.toList()));

        return sellerDTO;
    }

    private ProductDTO mapToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        // Add other product fields here
        return productDTO;
    }

    public List<SellerDTO> getAllSellers() {
        return sellerRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private SellerDTO convertToDTO(Seller seller) {
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setId(seller.getId());
        sellerDTO.setCompanyName(seller.getCompanyName());
        sellerDTO.setGstNumber(seller.getGstNumber());
        sellerDTO.setSummary(seller.getSummary());
        sellerDTO.setAddress(seller.getAddress());
        sellerDTO.setMobileNumber(seller.getMobileNumber());
        sellerDTO.setEmailId(seller.getEmailId());
        sellerDTO.setName(seller.getName());
        sellerDTO.setCityId(seller.getCity().getId());
        sellerDTO.setCityName(seller.getCity().getCityName());
        sellerDTO.setAccountId(seller.getAccount().getId());
        sellerDTO.setAccountName(seller.getAccount().getFirstName() + " " + seller.getAccount().getLastName());
        // Assuming a method exists to map Product to ProductDTO
        sellerDTO.setProducts(seller.getProducts().stream().map(this::mapToProductDTO).collect(Collectors.toList()));

        return sellerDTO;
    }
}

