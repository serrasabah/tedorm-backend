package tedorm.app.application.student.controller.requests;

import tedorm.app.application.student.entity.Student;

public record UpdateStudentRequest(
        String name,
        String surname,
        String studentNumber,

        String phoneNumber
) {

    public Student toDomainEntity() {
        return new Student(name, surname, null, null, studentNumber, null, null, null, phoneNumber);
    }
}