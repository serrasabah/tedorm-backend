package tedorm.app.application.authentication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tedorm.app.application.authentication.controller.request.LoginRequest;
import tedorm.app.application.authentication.service.LoginService;
import tedorm.app.application.common.response.MessageResponseID;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @PostMapping("/login")
    public MessageResponseID login(@Valid @RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }
}