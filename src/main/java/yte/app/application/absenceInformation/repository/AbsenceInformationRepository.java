package yte.app.application.absenceInformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.app.application.absenceInformation.entity.AbsenceInformation;

public interface AbsenceInformationRepository extends JpaRepository<AbsenceInformation, Long> {

}