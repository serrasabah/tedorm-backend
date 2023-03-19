package yte.app.application.authentication.controller.request;

import yte.app.application.authentication.entity.User;

import javax.validation.constraints.NotBlank;
public record AddUserRequest(
        @NotBlank
        String username,
        @NotBlank
        String password,
        String role
) {
    public User toDomainEntity() {
        return new User(username,  password, role);
    }

}
