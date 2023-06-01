package tedorm.app.application.authentication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tedorm.app.application.authentication.controller.request.ForgotPasswordRequest;
import tedorm.app.application.authentication.service.UserService;
import tedorm.app.application.authentication.controller.request.AddUserRequest;
import tedorm.app.application.authentication.controller.responses.UserQueryModel;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.student.controller.responses.StudentQueryModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    @PostMapping()
    public MessageResponse addUsers(@Valid @RequestBody AddUserRequest adduser) {
        return userService.addUsers(adduser.toDomainEntity());
    }
    @DeleteMapping("/{id}")
    public MessageResponse deleteUserbyId(@PathVariable @NotNull Long id) {
        return userService.deleteUserById(id);
    }

    @GetMapping()
    public List<UserQueryModel> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(user -> new UserQueryModel(user))
                .toList();
    }
    @GetMapping("/{id}")
    public UserQueryModel getById(@NotNull @PathVariable Long id) {
        return new UserQueryModel(userService.getById(id));
    }

    @GetMapping("/student/{id}")
    public StudentQueryModel getStudentByUserId(@NotNull @PathVariable Long id) {
        return new StudentQueryModel(userService.getStudentByUserId(id));
    }

    @PostMapping("/forgotPassword")
    public MessageResponse forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        return userService.forgotPassword(request.getEmail());
    }
}