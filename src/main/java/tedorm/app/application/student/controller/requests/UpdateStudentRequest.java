package tedorm.app.application.student.controller.requests;

import tedorm.app.application.student.entity.Student;

public record UpdateStudentRequest(
        String name,
        String surname,
        String email
) {

    public Student toDomainEntity() {
        return new Student(name, surname, email, null, null);
    }
}