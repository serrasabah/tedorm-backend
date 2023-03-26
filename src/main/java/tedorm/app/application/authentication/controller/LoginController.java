package tedorm.app.application.authentication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tedorm.app.application.authentication.controller.request.LoginRequest;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.authentication.service.LoginService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @PostMapping("/login")
    public MessageResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);

    }
}