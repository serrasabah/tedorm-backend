package yte.app.application.admin.controller.request;


import yte.app.application.admin.entity.Admin;

public record AddAdminRequest(

        String name,
        String surname


) {

    public Admin toDomainEntity() {
        return new Admin(name, surname);

    }


}