package tedorm.app.application.rooms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;
import tedorm.app.application.menu.controller.response.ListMenuResponse;
import tedorm.app.application.menu.entity.Menu;
import tedorm.app.application.rooms.entity.Room;
import tedorm.app.application.rooms.repository.RoomRepository;
import tedorm.app.application.student.entity.Student;
import tedorm.app.application.student.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;

    public MessageResponse addRooms(Room room) {

        roomRepository.save(room);
        return new MessageResponse(ResponseType.SUCCESS, "SUCCESS");
    }

    public List<Room> getAllRooms() {
        List<Room> listRooms = roomRepository.findAll();
        return listRooms;
    }

    public MessageResponse deleteRoomById(Long id) {
        Room roomOptional = roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));;;
        List<Student> student = studentRepository.findByRoomNumber(roomOptional.getRoomNumber());
            if (!student.isEmpty()) {
                return new MessageResponse(ResponseType.WARNING, "There are students in the room. First update the student");
            }
            roomRepository.deleteById(id);
            return new MessageResponse(ResponseType.SUCCESS, "Room has been deleted successfully");
    }

    public MessageResponse updateRooms(Long id, Room updatedRoom) {
        Room roomOptional = roomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));;;
            List<Student> student = studentRepository.findByRoomNumber(roomOptional.getRoomNumber());
            if (!student.isEmpty()) {
                for(Student s: student){
                    s.setRoomNumber(updatedRoom.getRoomNumber());
                }
            }
            Room room = roomRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Room not found"));

            room.update(updatedRoom);

            roomRepository.save(room);

            return new MessageResponse(ResponseType.SUCCESS, "Room has been updated successfully");
        }

}