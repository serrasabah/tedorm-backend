package yte.app.application.absenceInformation.controller.responses;

import yte.app.application.absenceInformation.entity.AbsenceInformation;

public record AbsenceInformationResponse(
        String address,
        String hostName,
        Long hostNumber
) {

    public AbsenceInformationResponse(AbsenceInformation absenceInformation) {
        this(
                absenceInformation.getAddress(),
                absenceInformation.getHostName(),
                absenceInformation.getHostNumber()
        );
    }
}
