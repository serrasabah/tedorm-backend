package yte.app.application.authentication.controller.request;

import yte.app.application.authentication.entity.User;
public record UpdateUserRequest(
        String username,
        String password,
        String role
) {
        public User toDomainEntity() {
            return new User(username, password, role);
        }
}
