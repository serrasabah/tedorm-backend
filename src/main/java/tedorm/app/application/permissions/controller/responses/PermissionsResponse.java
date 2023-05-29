package tedorm.app.application.permissions.controller.responses;

import tedorm.app.application.permissions.entity.Permissions;

import java.time.LocalDateTime;

public record PermissionsResponse(
        LocalDateTime permissionDateStart,
        LocalDateTime  permissionDateEnd,
        String message
) {

    public PermissionsResponse(Permissions permissions) {
        this(
                permissions.getPermissionDateStart(),
                permissions.getPermissionDateEnd(),
                permissions.getMessage()
        );
    }
}
