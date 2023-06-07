package tedorm.app.application.rooms.controller.requests;

import tedorm.app.application.rooms.entity.Room;
import tedorm.app.application.student.entity.Student;

import javax.validation.constraints.Min;

public record AddRoomsRequest(
        Integer roomType,
        Integer availableSlots,
        String roomNumber
) {
    public Room toDomainEntity() {
        return new Room(roomNumber, roomType, availableSlots);
    }
}
