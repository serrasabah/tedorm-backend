package tedorm.app.application.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.admin.entity.Admin;
import tedorm.app.application.student.entity.Student;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findById(Long id);

    Optional<Admin> findByEmail(String email);
}