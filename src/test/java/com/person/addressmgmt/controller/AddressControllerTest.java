package com.person.addressmgmt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.addressmgmt.model.Address;
import com.person.addressmgmt.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.person.addressmgmt.service.AddressServiceTest.getAddressList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AddressControllerTest {

    @InjectMocks
    private AddressController addressController;

    @Mock
    private AddressService addressService;

    private MockMvc mockMvc;

    private final String url = "http://localhost:9012";

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
    }

    @Test
    void getAllAddresses() throws Exception {
        when(addressService.getAllAddresses()).thenReturn(getAddressList());
        mockMvc.perform(
                MockMvcRequestBuilders.get(url + "/v1/address")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void create() throws Exception {
        when(addressService.create(any(Address.class))).thenReturn(getAddressList().get(0));
        mockMvc.perform(
                MockMvcRequestBuilders.post(url + "/v1/address")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new Address()))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}