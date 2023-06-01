package tedorm.app.application.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.authentication.entity.User;
import tedorm.app.application.student.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findById(Long id);
    Optional<Student> findByEmail(String email);
    boolean existsByEmail(String name);

    List<Student> findByUserId(Long id);

    List<Student> findByRoomNumber(String roomNumber);

}