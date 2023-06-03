package tedorm.app.application.authentication.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tedorm.app.application.admin.entity.Admin;
import tedorm.app.application.admin.repository.AdminRepository;
import tedorm.app.application.authentication.repository.UserRepository;
import tedorm.app.application.authentication.entity.Authority;
import tedorm.app.application.authentication.entity.User;
import tedorm.app.application.student.entity.Student;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class UserTablePopulator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AdminRepository adminRepository;

    @PostConstruct
    public void populateDatabase() {
        if (!userRepository.existsByUsername("user")) {
            User user = new User("user", passwordEncoder.encode("user") );
            user.getAuthorities().add(new Authority("STUDENT"));
            userRepository.save(user);
        }
        if (!userRepository.existsByUsername("admin")) {
            Admin admin = new Admin();
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setName("admin");
            admin.setSurname("admin");
            admin.setEmail("admin");
            User user = new User("admin", passwordEncoder.encode("admin"));
            user.getAuthorities().add(new Authority("ADMIN"));
            user.setAdmin(admin);
            adminRepository.save(admin);
            userRepository.save(user);
        }
    }

}