package yte.app.application.student.controller.responses;

import yte.app.application.student.entity.Student;

public record StudentQueryModel(
        Long id,
        String name,
        String surname,
        String email,
        String tcKimlikNo,
        String studentNumber
) {

    public StudentQueryModel(Student student) {
        this(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getTcKimlikNo(),
                student.getStudentNumber()
        );
    }
}
