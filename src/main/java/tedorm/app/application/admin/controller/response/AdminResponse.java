package tedorm.app.application.admin.controller.response;

import tedorm.app.application.admin.entity.Admin;

public record AdminResponse(
        Long id,
        String name,
        String surname,
        String email
) {
    public AdminResponse(Admin admin) {
        this(
                admin.getId(),
                admin.getName(),
                admin.getSurname(),
                admin.getEmail()
        );
    }
}