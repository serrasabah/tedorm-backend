package tedorm.app.application.applicant.controller.requests;

import tedorm.app.application.applicant.entity.Applicant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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
        @NotBlank
        String university,

        int roomType,

        LocalDate age,
        String tcKimlikNo,

        String studentNumber
) {

    public Applicant toDomainEntity() {
        return new Applicant(name, surname, phoneNumber, email, address, university, roomType, age, tcKimlikNo, studentNumber);
    }
}
