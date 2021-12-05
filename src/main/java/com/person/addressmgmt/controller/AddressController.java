package com.person.addressmgmt.controller;

import com.person.addressmgmt.util.AppUtil;
import com.person.addressmgmt.exception.AddressException;
import com.person.addressmgmt.model.Address;
import com.person.addressmgmt.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.person.addressmgmt.util.AppConstants.INVALID_ADDRESS_OBJECT;

@Slf4j
@RestController
@RequestMapping("/v1")
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(summary = "Get all addresses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "204")
    })
    @GetMapping("/address")
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addressList = addressService.getAllAddresses();
        try {
            log.debug("Fetched {} addresses.", addressList.size());
            if(addressList.isEmpty())
                return ResponseEntity.noContent().build();
            return ResponseEntity.ok(addressList);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().header("errMsg", e.getMessage()).build();
        }
    }

    @Operation(summary = "Create new address")
    @PostMapping("/address")
    public ResponseEntity<Address> create(
            @Parameter(description = "address to create") @RequestBody(required = true) Address address
    ) {
        Address createdAddress = null;
        try {
            createdAddress = addressService.create(address);
            log.debug("Created address : {}", createdAddress);
            return ResponseEntity.ok(createdAddress);
        } catch(AddressException e) {
            e.printStackTrace();
            if(e.getMessage().contains(INVALID_ADDRESS_OBJECT))
                return ResponseEntity.badRequest().build();
            return ResponseEntity.internalServerError().build();
        } catch(Exception e) {
            e.printStackTrace();
            return AppUtil.buildInternalServerError.apply(e);
        }
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable(name = "id", required = true) int addressId) {
        Address fetchedAddress = addressService.getAddressById(addressId);
        try {
            log.debug("Fetched address: {}", fetchedAddress);
            if(Objects.isNull(fetchedAddress))
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(fetchedAddress);
        } catch(Exception e) {
            e.printStackTrace();
            return AppUtil.buildInternalServerError.apply(e);
        }
    }

    @PatchMapping("/address")
    public ResponseEntity<Address> update(@RequestBody(required = true) Address address) {
        Address updatedAddress = null;
        try {
            updatedAddress = addressService.update(address);
            log.debug("Updated address : {}", updatedAddress);
            return ResponseEntity.ok(updatedAddress);
        } catch(AddressException e) {
            e.printStackTrace();
            if(e.getMessage().contains(INVALID_ADDRESS_OBJECT))
                return ResponseEntity.badRequest().build();
            return ResponseEntity.internalServerError().build();
        } catch(Exception e) {
            e.printStackTrace();
            return AppUtil.buildInternalServerError.apply(e);
        }
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id", required = true) int addressId) {
        try {
            addressService.delete(addressId);
            log.debug("Deleted address : {}", addressId);
            return ResponseEntity.ok("Deleted address : " + addressId);
        } catch(AddressException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().header("errMsg", e.getMessage()).build();
        }
    }
}
