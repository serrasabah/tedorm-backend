package tedorm.app.application.admin.controller.request;


import tedorm.app.application.admin.entity.Admin;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;

import javax.validation.constraints.Email;

public record AddAdminRequest(

        String name,
        String surname,
        String email,

        String password


) {

    public Admin toDomainEntity() {
        return new Admin(name, surname, email, password);

    }


}