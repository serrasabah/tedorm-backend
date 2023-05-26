package tedorm.app.application.common.response;

public record MessageResponseID(
        ResponseType responseType,
        String message,
        Long ID,
        String isAuthority
) {
}