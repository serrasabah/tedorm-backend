package tedorm.app.application.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tedorm.app.application.admin.controller.request.AddAdminRequest;
import tedorm.app.application.admin.controller.request.UpdateAdminRequest;
import tedorm.app.application.admin.controller.response.AdminResponse;
import tedorm.app.application.admin.service.AdminService;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.student.controller.requests.AddStudentRequest;
import tedorm.app.application.student.controller.requests.ChangePasswordRequest;
import tedorm.app.application.student.controller.requests.UpdateStudentRequest;
import tedorm.app.application.student.controller.responses.StudentQueryModel;
import tedorm.app.application.student.service.StudentService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Validated
public class AdminController {


    private final AdminService adminService;

    private final StudentService studentService;

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

    @PostMapping("/changePassword/{id}")
    public MessageResponse changePassword(@Valid @RequestBody ChangePasswordRequest request, @PathVariable Long id) {
        return studentService.changePassword(id, request);
    }
}