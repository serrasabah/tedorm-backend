package tedorm.app.application.authentication.controller.request;

import tedorm.app.application.authentication.entity.User;

import javax.validation.constraints.NotBlank;
public record AddUserRequest(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
    public User toDomainEntity() {
        return new User(username,  password);
    }

}
