package tedorm.app.application.announcement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tedorm.app.application.announcement.entity.Announcement;
import tedorm.app.application.announcement.repository.AnnouncementRepository;
import tedorm.app.application.common.response.MessageResponse;
import tedorm.app.application.common.response.ResponseType;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public Announcement getAnnouncement(Long id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Announcement not found"));
    }
    public List<Announcement> getAllAnnouncement() {
        return announcementRepository.findAll();
    }

    public MessageResponse addAnnouncement(Announcement announcement) {
        announcementRepository.save(announcement);
        return new MessageResponse(ResponseType.SUCCESS, "Announcement information has been added successfully");
    }

    public MessageResponse deleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);
        return new MessageResponse(ResponseType.SUCCESS, "Permission information has been deleted successfully");
    }

    public MessageResponse updateAnnouncement(Long id, Announcement updateAnnouncement) {
        Announcement permission = announcementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Announcement not found"));

        permission.update(updateAnnouncement);

        announcementRepository.save(permission);

        return new MessageResponse(ResponseType.SUCCESS, "Announcement information has been updated successfully");
    }

}