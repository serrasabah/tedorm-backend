package tedorm.app.application.announcement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.announcement.entity.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

}