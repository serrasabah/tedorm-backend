package tedorm.app.application.student.controller.requests;

import tedorm.app.application.student.entity.Student;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
        @NotBlank
        @Size(min = 7, max = 7)
        String studentNumber
) {

    public Student toDomainEntity() {
        return new Student(name, surname, email, tcKimlikNo, studentNumber);
    }
}
