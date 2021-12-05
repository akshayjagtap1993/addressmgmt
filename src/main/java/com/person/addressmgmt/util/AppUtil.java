package com.person.addressmgmt.util;

import com.person.addressmgmt.model.Address;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

public class AppUtil {
    public static Function<Exception, ResponseEntity<Address>> buildInternalServerError
             = e -> ResponseEntity.internalServerError().header("errMsg", e.getMessage()).build();
}
