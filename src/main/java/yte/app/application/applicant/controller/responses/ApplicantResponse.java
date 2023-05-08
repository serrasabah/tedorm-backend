package yte.app.application.applicant.controller.responses;

import yte.app.application.applicant.entity.Applicant;

public record ApplicantResponse(
        Long id,
        String name,
        String surname,
        String phoneNumber,
        String email,
        String address,
        String university,
        int roomType

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
                applicant.getRoomType()
        );
    }
}
