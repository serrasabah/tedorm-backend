package tedorm.app.application.request.controller.responses;

import tedorm.app.application.request.entity.Requests;

public record RequestResponse(
        Long id,
        String message,
        String name
) {

    public RequestResponse(Requests requests) {
        this(
                requests.getId(),
                requests.getMessage(),
                requests.getStudent().getName()+" "+
                        requests.getStudent().getSurname()
        );
    }
}
