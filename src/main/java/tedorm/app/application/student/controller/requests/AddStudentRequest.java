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
        String email,
        @NotBlank
        String tcKimlikNo,

        @NotBlank
        String studentNumber,

        LocalDate age,
        @NotBlank
        String roomNumber,
        @NotBlank
        String university,

        @NotBlank
        String phoneNumber
) {

    public Student toDomainEntity() {
        return new Student(name, surname, email, tcKimlikNo, studentNumber, age, roomNumber, university, phoneNumber);
    }
}
