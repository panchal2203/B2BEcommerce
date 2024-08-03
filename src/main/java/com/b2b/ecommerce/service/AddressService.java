package com.b2b.ecommerce.service;

import com.b2b.ecommerce.dto.AddressDTO;
import java.util.List;

public interface AddressService {
    String addAddress(AddressDTO addressDTO);
    String updateAddress(long id, AddressDTO addressDTO);
    String deleteAddress(long id);
    List<AddressDTO> getAllAddressesByAccountId(long accountId);
    AddressDTO getAddressById(long id);
}



