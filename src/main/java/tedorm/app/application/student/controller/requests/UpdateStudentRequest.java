package tedorm.app.application.student.controller.requests;

import tedorm.app.application.student.entity.Student;

public record UpdateStudentRequest(
         Integer roomType,
        String roomNumber
) {

    public Student toDomainEntity() {
        return new Student(null, null, null, null, null, null, roomNumber, null, null, roomType, null);
    }
}