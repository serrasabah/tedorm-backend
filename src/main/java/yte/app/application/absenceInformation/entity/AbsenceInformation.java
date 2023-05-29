package yte.app.application.absenceInformation.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tedorm.app.application.common.entity.BaseEntity;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AbsenceInformation extends BaseEntity {

    private String address;
    private String hostName;
    private Long hostNumber;

    public AbsenceInformation(String address, String hostName, Long hostNumber) {
        this.address = address;
        this.hostName = hostName;
        this.hostNumber = hostNumber;
    }

    public void update(AbsenceInformation updateAbsenceInformation) {
        this.address = updateAbsenceInformation.address;
        this.hostName = updateAbsenceInformation.hostName;
        this.hostNumber = updateAbsenceInformation.hostNumber;
    }
}