package com.person.addressmgmt.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AddressException extends RuntimeException {

    public AddressException(String message) { super(message); }
}
