package yte.app.application.admin.controller.response;

import yte.app.application.admin.entity.Admin;

public record AdminResponse(
        Long id,
        String name,
        String surname
) {
    public AdminResponse(Admin admin) {
        this(
                admin.getId(),
                admin.getName(),
                admin.getSurname()

        );
    }
}