package yte.app.application.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.app.application.student.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByName(String name);


}