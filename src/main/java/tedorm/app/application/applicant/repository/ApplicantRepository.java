package tedorm.app.application.applicant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.applicant.entity.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

}