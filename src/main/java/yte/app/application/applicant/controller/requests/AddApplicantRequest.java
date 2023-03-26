package yte.app.application.applicant.controller.requests;

import yte.app.application.applicant.entity.Applicant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddApplicantRequest(
        @NotBlank
        @Size(max = 25)
        String name,
        @NotBlank
        @Size(max = 25)
        String surname,
        String university,
        int roomType

) {

    public Applicant toDomainEntity() {
        return new Applicant(name, surname, university, roomType);
    }
}
