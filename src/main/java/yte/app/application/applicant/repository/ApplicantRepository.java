package yte.app.application.applicant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.app.application.applicant.entity.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

}