package tedorm.app.application.rooms.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tedorm.app.application.common.entity.BaseEntity;
import tedorm.app.application.menu.entity.Menu;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Room extends BaseEntity {

    private Integer roomType;
    private Integer availableSlots;
    private String roomNumber;

    public Room(String roomNumber, Integer roomType, Integer availableSlots) {
        this.availableSlots = availableSlots;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }

    public Room(Integer availableSlots, String roomNumber) {
        this.availableSlots = availableSlots;
        this.roomNumber = roomNumber;
    }

    public void update(Room updatedMenu) {
        this.availableSlots = updatedMenu.availableSlots;
        this.roomNumber = updatedMenu.roomNumber;
    }

}
