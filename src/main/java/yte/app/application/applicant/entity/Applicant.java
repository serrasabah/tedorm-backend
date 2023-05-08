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
    private String phoneNumber;
    private  String email;
    private String address;
    private String university;
    private int roomType;

    public Applicant(String name, String surname, String phoneNumber, String email, String address, String university, int roomType) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.university = university;
        this.roomType = roomType;
    }

}