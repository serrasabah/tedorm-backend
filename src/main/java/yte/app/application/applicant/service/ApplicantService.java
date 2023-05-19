package yte.app.application.applicant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.app.application.applicant.entity.Applicant;
import yte.app.application.applicant.repository.ApplicantRepository;
import yte.app.application.common.response.MessageResponse;
import yte.app.application.common.response.ResponseType;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    public MessageResponse addApplicant(Applicant applicant) {
        applicantRepository.save(applicant);
        return new MessageResponse(ResponseType.SUCCESS, "Address has been added successfully");
    }

    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    public Applicant getById(Long id) {
        return applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));
    }

}