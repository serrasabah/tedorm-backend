package yte.app.application.authentication.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import yte.app.application.authentication.repository.UserRepository;
import yte.app.application.authentication.entity.Authority;
import yte.app.application.authentication.entity.User;

import javax.annotation.PostConstruct;
import java.util.List;
@Component
@RequiredArgsConstructor
public class UserTablePopulator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void populateDatabase() {
        if (!userRepository.existsByUsername("user")) {
            User user = new User("user", passwordEncoder.encode("user"), "USER");
            user.getAuthorities().add(new Authority(user.getRole()));
            userRepository.save(user);
        }
        if (!userRepository.existsByUsername("admin")) {
            User user = new User("admin", passwordEncoder.encode("admin"), "ADMIN");
            user.getAuthorities().add(new Authority(user.getRole()));
            userRepository.save(user);
        }
    }

}