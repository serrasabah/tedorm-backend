package yte.app.application.student.controller.requests;

import yte.app.application.student.entity.Student;

public record UpdateStudentRequest(
        String name,
        String surname,
        String email
) {

    public Student toDomainEntity() {
        return new Student(name, surname, email, null, null);
    }
}