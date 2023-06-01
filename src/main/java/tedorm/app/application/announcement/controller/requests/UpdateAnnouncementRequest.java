package tedorm.app.application.announcement.controller.requests;

import tedorm.app.application.announcement.entity.Announcement;

public record UpdateAnnouncementRequest(
        String announcement
) {
    public Announcement toDomainEntity() {
        return new Announcement( announcement);    }
}