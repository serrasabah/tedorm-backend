package yte.app.application.applicant.controller.responses;

import yte.app.application.applicant.entity.Applicant;

public record ApplicantResponse(
        Long id,
        String name,
        String surname,
        String university,
        int roomType

) {

    public ApplicantResponse(Applicant applicant) {
        this(
                applicant.getId(),
                applicant.getName(),
                applicant.getSurname(),
                applicant.getUniversity(),
                applicant.getRoomType()
        );
    }
}
