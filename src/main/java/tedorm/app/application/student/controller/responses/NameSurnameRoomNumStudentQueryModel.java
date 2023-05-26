package tedorm.app.application.student.controller.responses;

import tedorm.app.application.student.entity.Student;

  public record NameSurnameRoomNumStudentQueryModel(
        Long id,
        String name,
        String surname,
        String email,
        String phoneNumber,
        String roomNumber
) {

    public NameSurnameRoomNumStudentQueryModel(Student student) {
        this(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getPhoneNumber(),
                student.getRoomNumber()
        );
    }
}
