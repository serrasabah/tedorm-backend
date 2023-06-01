package tedorm.app.application.rooms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tedorm.app.application.rooms.entity.Room;
import tedorm.app.application.student.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findById(Long id);

    Room findByRoomType(Integer roomType);

    Room findByRoomNumber(String roomNumber);
    List<Room> findByRoomTypeAndAvailableSlotsGreaterThan(Integer roomType, Integer availableSlots);

}

