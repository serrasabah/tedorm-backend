package tedorm.app.application.admin.controller.request;

import tedorm.app.application.admin.entity.Admin;

public record UpdateAdminRequest(
        String email
) {
    public Admin toDomainEntity() {
        return new Admin(email);
    }

}