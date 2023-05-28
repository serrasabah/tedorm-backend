package yte.app.application.address.controller.responses;

import yte.app.application.address.entity.Address;

public record AddressResponse(
        Long id,
        String name,
        String phoneNumber,
        String address
) {

    public AddressResponse(Address address) {
        this(
                address.getId(),
                address.getName(),
                address.getPhoneNumber(),
                address.getAddress()
        );
    }
}
