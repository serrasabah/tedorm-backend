package tedorm.app.application.admin.controller.request;


import tedorm.app.application.admin.entity.Admin;

public record AddAdminRequest(

        String name,
        String surname


) {

    public Admin toDomainEntity() {
        return new Admin(name, surname);

    }


}