package tedorm.app.application.authentication.controller.responses;
import tedorm.app.application.authentication.entity.User;

public record UserQueryModel(
        Long id,
        String username
) {
    public UserQueryModel(User user) {
        this(
                user.getId(),
                user.getUsername()
        );
    }
}

