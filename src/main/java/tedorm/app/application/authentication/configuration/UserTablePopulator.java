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
import tedorm.app.application.student.repository.StudentRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class UserTablePopulator {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void populateDatabase() {
        if (!userRepository.existsByUsername("serra@sabah")) {
            Student student = new Student();
            student.setId(1L);
            student.setName("serra");
            student.setSurname("sabah");
            student.setEmail("serra@sabah");
            student.setTcKimlikNo("111100000");
            studentRepository.save(student);

            Admin admin = new Admin();
            admin.setId(null);
            adminRepository.save(admin);

            User user = new User();
            user.setUsername(student.getEmail());
            user.setPassword(passwordEncoder.encode("student"));
            user.getAuthorities().add(new Authority("STUDENT"));
            user.setStudent(student);
            user.setAdmin(admin);
            userRepository.save(user);
        }
        if (!userRepository.existsByUsername("admin@admin")) {
            Student student = new Student();
            student.setId(null);
            student.setName(null);
            student.setSurname(null);
            student.setEmail(null);
            student.setTcKimlikNo(null);
            studentRepository.save(student);

            Admin admin = new Admin();
            admin.setName("admin");
            admin.setSurname("admin");
            admin.setId(1L);
            adminRepository.save(admin);

            User user = new User();
            user.setUsername(student.getEmail());
            user.setPassword(passwordEncoder.encode("admin"));
            user.getAuthorities().add(new Authority("ADMİN"));
            user.setStudent(student);
            user.setAdmin(admin);
            userRepository.save(user);
        }
    }
}
