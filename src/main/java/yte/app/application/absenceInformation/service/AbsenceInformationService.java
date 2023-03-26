package yte.app.application.absenceInformation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.app.application.absenceInformation.entity.AbsenceInformation;
import yte.app.application.absenceInformation.repository.AbsenceInformationRepository;
import yte.app.application.common.response.MessageResponse;
import yte.app.application.common.response.ResponseType;

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

    public MessageResponse deleteAbsenceInformation(AbsenceInformation absenceInformation) {
        absenceInformationRepository.delete(absenceInformation);

        return new MessageResponse(ResponseType.SUCCESS, "Absence information has been deleted successfully");
    }

    public MessageResponse updateAbsenceInformation(AbsenceInformation absenceInformation) {

        absenceInformation.update(absenceInformation);

        absenceInformationRepository.save(absenceInformation);

        return new MessageResponse(ResponseType.SUCCESS, "Absence information has been updated successfully");
    }
}