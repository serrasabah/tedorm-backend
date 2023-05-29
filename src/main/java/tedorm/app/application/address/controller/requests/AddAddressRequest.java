package tedorm.app.application.address.controller.requests;

import tedorm.app.application.address.entity.Address;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddAddressRequest(
        @NotBlank
        @Size(max = 25)
        String name,
        String phoneNumber,
        @NotBlank
        String address

) {

    public Address toDomainEntity() {
        return new Address(name, phoneNumber, address);
    }
}
