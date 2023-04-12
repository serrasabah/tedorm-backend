package yte.app.application.permissions.controller.responses;

import yte.app.application.permissions.entity.Permissions;

import java.time.LocalDateTime;

public record PermissionsResponse(
        LocalDateTime permissionDates,
        String message
) {

    public PermissionsResponse(Permissions permissions) {
        this(
                permissions.getPermissionDates(),
                permissions.getMessage()
        );
    }
}
