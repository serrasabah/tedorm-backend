package tedorm.app.application.applicant.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import tedorm.app.application.common.entity.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Applicant extends BaseEntity {

    private String name;
    private String surname;
    private String phoneNumber;
    private  String email;

    private LocalDate age;
    private String address;
    private String university;
    private int roomType;

    private String tcKimlikNo;
    private String studentNumber;


    public Applicant(String name, String surname, String phoneNumber, String email, String address, String university, int roomType, LocalDate age,  String tcKimlikNo,
                     String studentNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.university = university;
        this.roomType = roomType;
        this.age = age;
        this.tcKimlikNo = tcKimlikNo;
        this.studentNumber = studentNumber;
    }

}