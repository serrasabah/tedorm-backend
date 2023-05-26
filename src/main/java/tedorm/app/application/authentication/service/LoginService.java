package tedorm.app.application.authentication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tedorm.app.application.authentication.controller.request.LoginRequest;
import tedorm.app.application.authentication.entity.User;
import tedorm.app.application.authentication.repository.UserRepository;
import tedorm.app.application.common.response.MessageResponseID;
import tedorm.app.application.common.response.ResponseType;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    public  String authority = " ";
    public MessageResponseID login(LoginRequest loginRequest) {
        var preAuthentication = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        try {
            Authentication postAuthentication = authenticationManager.authenticate(preAuthentication);
            SecurityContext newContext = SecurityContextHolder.createEmptyContext();
            newContext.setAuthentication(postAuthentication);
            SecurityContextHolder.setContext(newContext);
            User user = userRepository.findByUsername(loginRequest.username()).
                    orElseThrow(() -> new UsernameNotFoundException("User not found."));
            Long id = user.getId();

            authority = newContext.getAuthentication().getAuthorities().stream().toList().get(0).getAuthority();

            return new MessageResponseID(ResponseType.SUCCESS, "Login is successful", id, authority);
        } catch (AuthenticationException e) {
            return new MessageResponseID(ResponseType.ERROR, "the username or password is incorrect please try again",0L,authority);
        }
    }


}