package tedorm.app.application.student.controller.requests;

import tedorm.app.application.student.entity.Student;

public record UpdateStudentForAdmin(

        String phoneNumber
) {

    public Student toDomainEntity() {
        return new Student(null, null, null, null, null, null, null, null, phoneNumber, null, null);
    }
}