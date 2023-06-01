package tedorm.app.application.applicant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.applicant.entity.Applicant;
import tedorm.app.application.student.entity.Student;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    Applicant findByEmail(String email);

}