package tedorm.app.application.applicant.controller.responses;

import tedorm.app.application.applicant.entity.Applicant;

import java.time.LocalDate;

public record ApplicantResponse(
        Long id,
        String name,
        String surname,
        String phoneNumber,
        String email,
        String address,
        String university,
        int roomType,
        LocalDate age,
        String tcKimlikNo,
        String studentNumber

) {

    public ApplicantResponse(Applicant applicant) {
        this(
                applicant.getId(),
                applicant.getName(),
                applicant.getSurname(),
                applicant.getPhoneNumber(),
                applicant.getEmail(),
                applicant.getAddress(),
                applicant.getUniversity(),
                applicant.getRoomType(),
                applicant.getAge(),
                applicant.getTcKimlikNo(),
                applicant.getStudentNumber()
        );
    }
}
