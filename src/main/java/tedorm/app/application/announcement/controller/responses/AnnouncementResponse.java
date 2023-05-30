package tedorm.app.application.announcement.controller.responses;

import tedorm.app.application.announcement.entity.Announcement;

public record AnnouncementResponse(

        String message
) {

    public AnnouncementResponse(Announcement announcement) {
        this(
                announcement.getAnnouncement()
        );
    }
}
