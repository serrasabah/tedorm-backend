package yte.app.application.authentication.controller.responses;
import yte.app.application.authentication.entity.User;
public record UserQueryModel(
        Long id,
        String username,
        String role
) {
    public UserQueryModel(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }
}

