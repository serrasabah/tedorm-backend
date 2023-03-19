package yte.app.application.authentication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import yte.app.application.authentication.controller.request.LoginRequest;
import yte.app.application.authentication.controller.responses.UserQueryModel;
import yte.app.application.common.response.MessageResponse;
import yte.app.application.authentication.service.LoginService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @PostMapping("/login")
    public MessageResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);

    }
}