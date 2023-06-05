package tedorm.app.application.request.controller.requests;

import tedorm.app.application.request.entity.Requests;

public record UpdateRequestRequest(
        String message,
        Long studentId
) {
    public Requests toDomainEntity() {
        return new Requests(  message,studentId);
    }
}