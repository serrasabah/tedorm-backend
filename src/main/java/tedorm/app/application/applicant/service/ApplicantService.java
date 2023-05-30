package tedorm.app.application.applicant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tedorm.app.application.applicant.entity.Applicant;
import tedorm.app.application.applicant.repository.ApplicantRepository;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;

    public MessageResponse addApplicant(Applicant applicant) {
        applicantRepository.save(applicant);
        return new MessageResponse(ResponseType.SUCCESS, "Applicant has been added successfully");
    }

    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    public Applicant getById(Long id) {
        return applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Applicant not found"));
    }


    public MessageResponse deleteApplicantById(Long id) {
        applicantRepository.deleteById(id);

        return new MessageResponse(ResponseType.SUCCESS, "Applicant has been deleted successfully");
    }

}