package tedorm.app.application.student.controller.requests;

import tedorm.app.application.student.entity.Student;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AddStudentRequest(
        @NotBlank
        @Size(max = 25)
        String name,
        @NotBlank
        @Size(max = 25)
        String surname,
        @NotBlank
        String email,

        String tcKimlikNo,

        String studentNumber,

        LocalDate age,
        @NotBlank
        String roomNumber,
        @NotBlank
        String university,
        @NotBlank
        String phoneNumber,
        Integer roomType,
        String address
) {

    public Student toDomainEntity() {
        return new Student(name, surname, email, tcKimlikNo, studentNumber, age, roomNumber, university, phoneNumber, roomType, address);
    }
}
