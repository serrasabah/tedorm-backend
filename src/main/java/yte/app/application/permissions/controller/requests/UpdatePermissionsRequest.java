package yte.app.application.permissions.controller.requests;

import yte.app.application.address.entity.Address;
import yte.app.application.permissions.entity.Permissions;

import java.time.LocalDateTime;

public record UpdatePermissionsRequest(
        String name, String phoneNumber,  String address,
        LocalDateTime permissionDateStart,
        LocalDateTime permissionDateEnd,
        String message,
        Long studentId
) {
    public Permissions toDomainEntity() {
        return new Permissions( new Address( name,  phoneNumber, address),permissionDateStart, permissionDateEnd, message,studentId);
    }
}