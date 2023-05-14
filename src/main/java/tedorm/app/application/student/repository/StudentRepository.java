package tedorm.app.application.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.student.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByName(String name);

    List<Student> findByUserId(Long id);
}