package yte.app.application.applicant.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import yte.app.application.common.entity.BaseEntity;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
public class Applicant extends BaseEntity {

    private String name;
    private String surname;
    private String university;
    private int roomType;

    public Applicant(String name, String surname, String university, int roomType) {
        this.name = name;
        this.surname = surname;
        this.university = university;
        this.roomType = roomType;
    }

}