package tedorm.app.application.applicant.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tedorm.app.application.applicant.controller.requests.AddApplicantRequest;
import tedorm.app.application.applicant.controller.responses.ApplicantResponse;
import tedorm.app.application.applicant.service.ApplicantService;
import tedorm.app.application.common.response.MessageResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/applicant")
@RequiredArgsConstructor
@Validated
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping
    public MessageResponse addApplicant(@Valid @RequestBody AddApplicantRequest addApplicantRequest) {
        return applicantService.addApplicant(addApplicantRequest.toDomainEntity());
    }

    @GetMapping
    public List<ApplicantResponse> getAllApplicants() {
        return applicantService.getAllApplicants()
                .stream()
                .map(ApplicantResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ApplicantResponse getById(@NotNull @PathVariable Long id) {
        return new ApplicantResponse(applicantService.getById(id));
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteApplicantById(@PathVariable @NotNull Long id) {
        return applicantService.deleteApplicantById(id);
    }
}