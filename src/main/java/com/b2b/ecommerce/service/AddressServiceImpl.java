package com.b2b.ecommerce.service;

// AddressServiceImpl.java
import com.b2b.ecommerce.dto.AddressDTO;
import com.b2b.ecommerce.entity.Account;
import com.b2b.ecommerce.entity.Address;
import com.b2b.ecommerce.repository.AccountRepository;
import com.b2b.ecommerce.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public String addAddress(AddressDTO addressDTO) {
        Account account = accountRepository.findById(addressDTO.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Address address = new Address();
        address.setAddress1(addressDTO.getAddress1());
        address.setAddress2(addressDTO.getAddress2());
        address.setAddress3(addressDTO.getAddress3());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());
        address.setAccount(account);

        addressRepository.save(address);
        return "Address added successfully!";
    }

    @Override
    public String updateAddress(long id, AddressDTO addressDTO) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        address.setAddress1(addressDTO.getAddress1());
        address.setAddress2(addressDTO.getAddress2());
        address.setAddress3(addressDTO.getAddress3());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());

        addressRepository.save(address);
        return "Address updated successfully!";
    }

    @Override
    public String deleteAddress(long id) {
        addressRepository.deleteById(id);
        return "Address deleted successfully!";
    }

    @Override
    public List<AddressDTO> getAllAddressesByAccountId(long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        List<Address> addresses = account.getAddresses();
        List<AddressDTO> addressDTOs = new ArrayList<>();
        for (Address address : addresses) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setId(address.getId());
            addressDTO.setAddress1(address.getAddress1());
            addressDTO.setAddress2(address.getAddress2());
            addressDTO.setAddress3(address.getAddress3());
            addressDTO.setCity(address.getCity());
            addressDTO.setState(address.getState());
            addressDTO.setZipCode(address.getZipCode());
            addressDTO.setAccountId(accountId);
            addressDTOs.add(addressDTO);
        }
        return addressDTOs;
    }

    @Override
    public AddressDTO getAddressById(long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setAddress1(address.getAddress1());
        addressDTO.setAddress2(address.getAddress2());
        addressDTO.setAddress3(address.getAddress3());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setZipCode(address.getZipCode());
        addressDTO.setAccountId(address.getAccount().getId());

        return addressDTO;
    }
}
