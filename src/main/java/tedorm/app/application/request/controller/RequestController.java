package tedorm.app.application.request.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.request.controller.requests.AddRequestRequest;
import tedorm.app.application.request.controller.requests.UpdateRequestRequest;
import tedorm.app.application.request.controller.responses.RequestResponse;
import tedorm.app.application.request.service.RequestService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
@Validated
public class RequestController {

    private final RequestService requestService;

    @GetMapping("/{id}")
    public RequestResponse getRequest(@NotNull @PathVariable Long id) {
        return new RequestResponse(requestService.getRequest(id));
    }
    @GetMapping
    public List<RequestResponse> getAllRequest() {
        return requestService.getAllRequest()
                .stream()
                .map(RequestResponse::new)
                .toList();
    }
    @PostMapping
    public MessageResponse addRequest(@Valid @RequestBody AddRequestRequest addRequestRequest) {
        return requestService.addRequest(addRequestRequest.toDomainEntity());
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteRequest(@PathVariable @NotNull  Long id) {
        return requestService.deleteRequest(id);
    }

    @PutMapping("/{id}")
    public MessageResponse updateRequest(@Valid @RequestBody UpdateRequestRequest request, @PathVariable Long id) {
        return requestService.updateRequest(id, request.toDomainEntity());
    }
}