package yte.app.application.absenceInformation.controller.requests;

import yte.app.application.absenceInformation.entity.AbsenceInformation;

public record UpdateAbsenceInformationRequest(
        String address,
        String hostName,
        Long hostNumber
) {
    public AbsenceInformation toDomainEntity() {
        return new AbsenceInformation(address, hostName, hostNumber);
    }
}