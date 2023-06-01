package tedorm.app.application.rooms.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.menu.controller.request.UpdateMenuRequest;
import tedorm.app.application.menu.controller.response.ListMenuResponse;
import tedorm.app.application.rooms.controller.requests.AddRoomsRequest;
import tedorm.app.application.rooms.controller.requests.UpdateRoomRequest;
import tedorm.app.application.rooms.controller.responses.ListRoomResponse;
import tedorm.app.application.rooms.service.RoomService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
@Validated
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public MessageResponse addRooms(@Valid @RequestBody AddRoomsRequest addRoomsRequest) {
        return roomService.addRooms(addRoomsRequest.toDomainEntity());
    }

    @GetMapping
    public List<ListRoomResponse> getAllRooms() {
        return roomService.getAllRooms()
                .stream()
                .map(room -> new ListRoomResponse(room))
                .toList();
    }

    @PutMapping("/{id}")
    public MessageResponse updateRooms(@Valid @RequestBody UpdateRoomRequest request, @PathVariable Long id) {
        return roomService.updateRooms(id, request.toDomainEntity());
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteRoomById(@PathVariable @NotNull Long id) {
        return roomService.deleteRoomById(id);
    }

}
