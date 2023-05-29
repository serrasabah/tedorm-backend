package tedorm.app.application.permissions.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.permissions.controller.requests.AddPermissionsRequest;
import tedorm.app.application.permissions.controller.requests.UpdatePermissionsRequest;
import tedorm.app.application.permissions.controller.responses.PermissionsResponse;
import tedorm.app.application.permissions.service.PermissionsService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@Validated
public class PermissionsController {

    private final PermissionsService permissionsService;

    @GetMapping("/{id}")
    public PermissionsResponse getPermissions(@NotNull @PathVariable Long id) {
        return new PermissionsResponse(permissionsService.getPermissions(id));
    }
    @GetMapping
    public List<PermissionsResponse> getAllPermissions() {
        return permissionsService.getAllPermissions()
                .stream()
                .map(PermissionsResponse::new)
                .toList();
    }
    @PostMapping
    public MessageResponse addPermissions(@Valid @RequestBody AddPermissionsRequest addPermissionsRequest) {
        return permissionsService.addPermissions(addPermissionsRequest.toDomainEntity());
    }

    @DeleteMapping("/{id}")
    public MessageResponse deletePermissions(@PathVariable @NotNull  Long id) {
        return permissionsService.deletePermissions(id);
    }

    @PutMapping("/{id}")
    public MessageResponse updatePermissions(@Valid @RequestBody UpdatePermissionsRequest request,@PathVariable Long id) {
        return permissionsService.updatePermissions(id, request.toDomainEntity());
    }
}