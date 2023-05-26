package tedorm.app.application.applicant.controller.requests;

import tedorm.app.application.applicant.entity.Applicant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddApplicantRequest(
        @NotBlank
        @Size(max = 25)
        String name,
        @NotBlank
        @Size(max = 25)
        String surname,
        @NotBlank
        String phoneNumber,
        @Email
        @NotBlank
        String email,
        String address,
        String university,
        int roomType
) {

    public Applicant toDomainEntity() {
        return new Applicant(name, surname, phoneNumber, email, address, university, roomType);
    }
}
