package yte.app.application.absenceInformation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import yte.app.application.absenceInformation.entity.AbsenceInformation;
import yte.app.application.absenceInformation.repository.AbsenceInformationRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbsenceInformationService {

    private final AbsenceInformationRepository absenceInformationRepository;

    public MessageResponse addAbsenceInformation(AbsenceInformation absenceInformation) {
        absenceInformationRepository.save(absenceInformation);
        return new MessageResponse(ResponseType.SUCCESS, "Absence information has been added successfully");
    }

    public List<AbsenceInformation> getAllAbsenceInformation() {
        return absenceInformationRepository.findAll();
    }

    public MessageResponse deleteAbsenceInformation(Long id) {
        absenceInformationRepository.deleteById(id);

        return new MessageResponse(ResponseType.SUCCESS, "Absence information has been deleted successfully");
    }

    public MessageResponse updateAbsenceInformation(Long id, AbsenceInformation absenceInformation) {
        AbsenceInformation information = absenceInformationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Information not found"));

        information.update(absenceInformation);

        absenceInformationRepository.save(information);

        return new MessageResponse(ResponseType.SUCCESS, "Absence information has been updated successfully");
    }
}