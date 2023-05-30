package tedorm.app.application.announcement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tedorm.app.application.announcement.controller.requests.AddAnnouncementRequest;
import tedorm.app.application.announcement.controller.requests.UpdateAnnouncementRequest;
import tedorm.app.application.announcement.controller.responses.AnnouncementResponse;
import tedorm.app.application.announcement.service.AnnouncementService;
import tedorm.app.application.common.response.MessageResponse;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/announcement")
@RequiredArgsConstructor
@Validated
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping("/{id}")
    public AnnouncementResponse getAnnouncement(@NotNull @PathVariable Long id) {
        return new AnnouncementResponse(announcementService.getAnnouncement(id));
    }
    @GetMapping
    public List<AnnouncementResponse> getAnnouncement() {
        return announcementService.getAllAnnouncement()
                .stream()
                .map(AnnouncementResponse::new)
                .toList();
    }
    @PostMapping
    public MessageResponse addAnnouncement(@Valid @RequestBody AddAnnouncementRequest addAnnouncementRequest) {
        return announcementService.addAnnouncement(addAnnouncementRequest.toDomainEntity());
    }

    @DeleteMapping("/{id}")
    public MessageResponse deletePermissions(@PathVariable @NotNull  Long id) {
        return announcementService.deleteAnnouncement(id);
    }

    @PutMapping("/{id}")
    public MessageResponse updateAnnouncement(@Valid @RequestBody UpdateAnnouncementRequest request, @PathVariable Long id) {
        return announcementService.updateAnnouncement(id, request.toDomainEntity());
    }
}