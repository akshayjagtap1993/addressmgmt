package com.person.addressmgmt.service;

import com.person.addressmgmt.exception.AddressException;
import com.person.addressmgmt.model.Address;
import com.person.addressmgmt.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static com.person.addressmgmt.util.AppConstants.INVALID_ADDRESS_OBJECT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;

    @Test
    void getAllAddresses() {
        when(addressRepository.findAll()).thenReturn(getAddressList());
        List<Address> fetchedAddressList = addressService.getAllAddresses();
        verify(addressRepository, times(1)).findAll();
        assertEquals(3, fetchedAddressList.size());
    }

    @Test
    void createSuccess() {
        Address inputAddress = getAddressList().get(0);
        when(addressRepository.save(any(Address.class))).thenReturn(inputAddress);
        Address createdAddress = addressService.create(inputAddress);
        assertNotNull(createdAddress);
        verify(addressRepository, times(1)).save(any(Address.class));
        assertEquals(inputAddress.getAddressId(), createdAddress.getAddressId());
    }

    @Test
    void create_WithException() {
        Exception exception = assertThrows(AddressException.class, () -> addressService.create(new Address()));
        assertEquals(INVALID_ADDRESS_OBJECT, exception.getMessage());
    }

    public static List<Address> getAddressList() {
        List<Address> addressList = new ArrayList<>();
        addressList.add(new Address(1, "Pune", "123456"));
        addressList.add(new Address(2, "Mumbai", "567567"));
        addressList.add(new Address(3, "Bangalore", "789879"));
        return addressList;
    }
}