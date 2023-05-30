package tedorm.app.application.announcement.controller.requests;

import tedorm.app.application.announcement.entity.Announcement;

public record UpdateAnnouncementRequest(
        String announcement,
        Long adminId
) {
    public Announcement toDomainEntity() {
        return new Announcement( announcement, adminId);    }
}