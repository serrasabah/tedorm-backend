package yte.app.application.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.app.application.admin.controller.request.AddAdminRequest;
import yte.app.application.admin.controller.request.UpdateAdminRequest;
import yte.app.application.admin.controller.response.AdminResponse;
import yte.app.application.admin.service.AdminService;
import yte.app.application.common.response.MessageResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Validated
public class AdminController {


    private final AdminService adminService;

    @PostMapping
    public MessageResponse addAdmin(@Valid @RequestBody AddAdminRequest addAdminRequest) {
        return adminService.addAdmin(addAdminRequest.toDomainEntity());

    }

    @PutMapping("{id}")
    public MessageResponse updateAdmin(@Valid @RequestBody UpdateAdminRequest updateAdminRequest, @PathVariable Long id) {
        return adminService.updateAdmin(id, updateAdminRequest.toDomainEntity());
    }

    @DeleteMapping("{id}")
    public MessageResponse deleteAdmin(@PathVariable Long id) {
        return adminService.deleteAdminById(id);
    }


    @GetMapping("{id}")
    public AdminResponse getAdminById(@PathVariable Long id) {
        return new AdminResponse(adminService.getById(id));
    }

    @GetMapping
    public List<AdminResponse> getAllAdmins() {

        return adminService.getAllAdmin()
                .stream()
                .map(admin -> new AdminResponse(admin))
                .toList();
    }

}