package tedorm.app.application.common.response;

public record MessageResponse(
        ResponseType responseType,
        String message
) {
}