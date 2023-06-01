package tedorm.app.application.rooms.controller.responses;

import tedorm.app.application.rooms.entity.Room;

public  record ListRoomResponse(
        Long id,
        Integer roomType,
        Integer availableSlots,
        String roomNumber
) {

public ListRoomResponse(Room room) {
        this(room.getId(),
                room.getRoomType(),room.getAvailableSlots(),room.getRoomNumber()
        );
        }

        }