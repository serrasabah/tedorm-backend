package yte.app.application.permissions.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.app.application.common.response.MessageResponse;
import yte.app.application.permissions.controller.requests.AddPermissionsRequest;
import yte.app.application.permissions.controller.requests.UpdatePermissionsRequest;
import yte.app.application.permissions.controller.responses.PermissionsResponse;
import yte.app.application.permissions.entity.Permissions;
import yte.app.application.permissions.service.PermissionsService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Validated
public class PermissionsController {

    private final PermissionsService permissionsService;

    @GetMapping("/{id}")
    public PermissionsResponse viewPermission(@NotNull @PathVariable Long id) {
        return new PermissionsResponse(permissionsService.viewPermission(id));
    }

    @PostMapping
    public MessageResponse getPermissions(@Valid @RequestBody AddPermissionsRequest addPermissionsRequest) {
        return permissionsService.getPermissions(addPermissionsRequest.toDomainEntity());
    }

    @DeleteMapping("/{id}")
    public MessageResponse deletePermissions(@PathVariable @NotNull Permissions permissions) {
        return permissionsService.deletePermissions(permissions);
    }

    @PutMapping("/{id}")
    public MessageResponse updatePermissions(@Valid @RequestBody UpdatePermissionsRequest request) {
        return permissionsService.updatePermissions(request.toDomainEntity());
    }
}