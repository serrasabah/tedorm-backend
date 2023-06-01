package tedorm.app.application.student.controller.requests;

import tedorm.app.application.student.entity.Student;

public record UpdateStudentRequest(
        String email,
        String phoneNumber,
         String university,
        String roomNumber
) {

    public Student toDomainEntity() {
        return new Student(null, null, email, null, null, null, roomNumber, university, phoneNumber, null, null);
    }
}