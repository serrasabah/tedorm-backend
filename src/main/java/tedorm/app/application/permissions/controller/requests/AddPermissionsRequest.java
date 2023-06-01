package tedorm.app.application.permissions.controller.requests;

import tedorm.app.application.address.entity.Address;
import tedorm.app.application.permissions.entity.Permissions;

import java.time.LocalDateTime;

public record AddPermissionsRequest(
        //@NotBlank
        String name,
        String phoneNumber,

        Long addressId,
        String address,

        LocalDateTime permissionDateStart,
        LocalDateTime permissionDateEnd,
        String message,
        Long studentId
) {
    public Permissions toDomainEntity() {
        return new Permissions( new Address( name,  phoneNumber, address),permissionDateStart, permissionDateEnd,message,studentId, addressId);
    }
}
