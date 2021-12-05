package com.person.addressmgmt.service;

import com.person.addressmgmt.model.Address;
import com.person.addressmgmt.repository.AddressRepository;
import com.person.addressmgmt.exception.AddressException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.person.addressmgmt.util.AppConstants.INVALID_ADDRESS_OBJECT;

@Slf4j
@Service
public class AddressService {

    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address create(Address address) {
        if(!isValidAddressForCreate(address))
            throw new AddressException(INVALID_ADDRESS_OBJECT);
        return addressRepository.save(address);
    }

    public Address update(Address address) {
        if(!isValidAddressForUpdate(address))
            throw new AddressException(INVALID_ADDRESS_OBJECT);
        return addressRepository.save(address);
    }

    private boolean isValidAddressForCreate(Address address) {
        return !Objects.isNull(address)
                && StringUtils.isNotBlank(address.getAddress1())
                && StringUtils.isNotBlank(address.getZip());
    }

    private boolean isValidAddressForUpdate(Address address) {
        return isValidAddressForCreate(address) && address.getAddressId() > 0;
    }

    public void delete(int addressId) {
        try {
            addressRepository.deleteById(addressId);
        } catch(Exception e) {
            throw new AddressException("Address not found for delete");
        }
    }

    public Address getAddressById(int addressId) {
        return addressRepository.findById(addressId).orElse(null);
    }
}
