package yte.app.application.absenceInformation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yte.app.application.absenceInformation.controller.requests.AddAbsenceInformationRequest;
import yte.app.application.absenceInformation.controller.requests.UpdateAbsenceInformationRequest;
import yte.app.application.absenceInformation.controller.responses.AbsenceInformationResponse;
import yte.app.application.absenceInformation.entity.AbsenceInformation;
import yte.app.application.absenceInformation.service.AbsenceInformationService;
import yte.app.application.common.response.MessageResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Validated
public class AbsenceInformationController {

    private final AbsenceInformationService absenceInformationService;

    @PostMapping
    public MessageResponse addAbsenceInformation(@Valid @RequestBody AddAbsenceInformationRequest addAbsenceInformationRequest) {
        return absenceInformationService.addAbsenceInformation(addAbsenceInformationRequest.toDomainEntity());
    }

    @GetMapping
    public List<AbsenceInformationResponse> getAllAbsenceInformation() {
        return absenceInformationService.getAllAbsenceInformation()
                .stream()
                .map(AbsenceInformationResponse::new)
                .toList();
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteAbsenceInformation(@PathVariable @NotNull AbsenceInformation absenceInformation) {
        return absenceInformationService.deleteAbsenceInformation(absenceInformation);
    }

    @PutMapping("/{id}")
    public MessageResponse updateAbsenceInformation(@Valid @RequestBody UpdateAbsenceInformationRequest request) {
        return absenceInformationService.updateAbsenceInformation(request.toDomainEntity());
    }
}