package tedorm.app.application.rooms.controller.requests;


import tedorm.app.application.rooms.entity.Room;

public record UpdateRoomRequest(
        Integer availableSlots,
        String roomNumber
) {
    public Room toDomainEntity() {
        return new Room(availableSlots, roomNumber);
    }
}
