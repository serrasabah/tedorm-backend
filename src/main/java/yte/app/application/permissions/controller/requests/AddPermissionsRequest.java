package yte.app.application.permissions.controller.requests;

import yte.app.application.address.entity.Address;
import yte.app.application.permissions.entity.Permissions;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record AddPermissionsRequest(
        @NotBlank
        LocalDateTime permissionDates,
        Address address,
        String message
) {
    public Permissions toDomainEntity() {
        return new Permissions(address, permissionDates, message);
    }
}
