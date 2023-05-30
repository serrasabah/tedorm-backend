package tedorm.app.application.authentication.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tedorm.app.application.authentication.repository.UserRepository;
import tedorm.app.application.authentication.entity.Authority;
import tedorm.app.application.authentication.entity.User;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class UserTablePopulator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void populateDatabase() {
        if (!userRepository.existsByUsername("user")) {
            User user = new User("user", passwordEncoder.encode("user") );
            user.getAuthorities().add(new Authority("STUDENT"));
            userRepository.save(user);
        }
        if (!userRepository.existsByUsername("test")) {
            User user = new User("test", passwordEncoder.encode("admin"));
            user.getAuthorities().add(new Authority("ADMIN"));
            userRepository.save(user);
        }
    }

}