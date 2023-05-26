package tedorm.app.application.student.controller.responses;

import tedorm.app.application.student.entity.Student;

import java.time.LocalDate;

public record StudentQueryModel(
        Long id,
        String name,
        String surname,
        String email,
        String tcKimlikNo,
        String studentNumber,
        LocalDate age,
        String roomNumber,
        String university,
        String phoneNumber
) {

    public StudentQueryModel(Student student) {
        this(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getTcKimlikNo(),
                student.getStudentNumber(),
                student.getAge(),
                student.getRoomNumber(),
                student.getUniversity(),
                student.getPhoneNumber()
        );
    }
}
