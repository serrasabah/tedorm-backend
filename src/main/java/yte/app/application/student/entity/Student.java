package yte.app.application.student.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import yte.app.application.common.entity.BaseEntity;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
public class Student extends BaseEntity {

    private String name;
    private String surname;
    private String email;
    private String tcKimlikNo;
    private String studentNumber;

    public Student(String name,
                   String surname,
                   String email,
                   String tcKimlikNo,
                   String studentNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.tcKimlikNo = tcKimlikNo;
        this.studentNumber = studentNumber;
    }

    public void update(Student updatedStudent) {
        this.name = updatedStudent.name;
        this.surname = updatedStudent.surname;
        this.email = updatedStudent.email;
    }
}