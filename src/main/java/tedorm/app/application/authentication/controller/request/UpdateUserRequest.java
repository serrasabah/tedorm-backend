package tedorm.app.application.authentication.controller.request;

import tedorm.app.application.authentication.entity.User;
public record UpdateUserRequest(
        String username,
        String password
) {
        public User toDomainEntity() {
            return new User(username, password);
        }
}
