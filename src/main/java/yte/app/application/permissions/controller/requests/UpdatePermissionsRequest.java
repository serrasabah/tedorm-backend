package yte.app.application.permissions.controller.requests;

import yte.app.application.address.entity.Address;
import yte.app.application.permissions.entity.Permissions;

import java.time.LocalDateTime;

public record UpdatePermissionsRequest(
        LocalDateTime permissionDates,
        Address address,
        String message
) {
    public Permissions toDomainEntity() {
        return new Permissions(address, permissionDates, message);
    }
}