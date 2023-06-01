package tedorm.app.application.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.admin.entity.Admin;
import tedorm.app.application.authentication.entity.User;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByStudentId(Long id);
    Optional<User> findByAdminId(Long id);

    boolean existsByUsername(String username);
    boolean existsByPassword(String password);
}