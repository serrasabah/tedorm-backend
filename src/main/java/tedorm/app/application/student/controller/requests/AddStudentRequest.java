package tedorm.app.application.student.controller.requests;

import tedorm.app.application.student.entity.Student;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public record AddStudentRequest(
        @NotBlank
        @Size(max = 25)
        String name,
        @NotBlank
        @Size(max = 25)
        String surname,
        @Email
        String email,
        String tcKimlikNo,

        String studentNumber,


        LocalDateTime age,


        String roomNumber,

        String university
) {

    public Student toDomainEntity() {
        return new Student(name, surname, email, tcKimlikNo, studentNumber, age, roomNumber, university);
    }
}
