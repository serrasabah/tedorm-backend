package tedorm.app.application.student.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import tedorm.app.application.authentication.entity.User;
import tedorm.app.application.common.entity.BaseEntity;
import tedorm.app.application.files.entity.FileData;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Student extends BaseEntity {

    private String name;
    private String surname;
    private String email;
    private String tcKimlikNo;
    private String studentNumber;
    private LocalDate age;

    private String roomNumber;

    private String university;

    private Integer roomType;
    private String phoneNumber;

    private String address;

    public Student(String name,
                   String surname,
                   String email,
                   String tcKimlikNo,
                   String studentNumber,
                   LocalDate age,
                   String roomNumber,
                   String university,
                   String phoneNumber,
                   Integer roomType,
                   String address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.tcKimlikNo = tcKimlikNo;
        this.studentNumber = studentNumber;
        this.age = age;
        this.roomNumber = roomNumber;
        this.university = university;
        this.phoneNumber = phoneNumber;
        this.roomType = roomType;
        this.address = address;
    }

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<FileData> files = new ArrayList<>();

    public void update(Student updatedStudent) {
        this.email = updatedStudent.email;
        this.phoneNumber = updatedStudent.phoneNumber;
        this.university = updatedStudent.university;
        this.roomNumber=updatedStudent.roomNumber;
        this.roomType = updatedStudent.roomType;
    }

    public void addFile(FileData fileData) {
        this.files.add(fileData);
        fileData.setStudent(this);
    }
}