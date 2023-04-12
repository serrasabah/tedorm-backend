package yte.app.application.permissions.controller.requests;

import yte.app.application.permissions.entity.Permissions;

import java.time.LocalDateTime;

public record AddPermissionsRequest(
        LocalDateTime permissionDates,
        String message
) {
    public Permissions toDomainEntity() {
        return new Permissions(permissionDates, message);
    }
}
