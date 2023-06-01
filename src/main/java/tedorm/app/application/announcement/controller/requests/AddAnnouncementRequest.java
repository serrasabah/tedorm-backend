package tedorm.app.application.announcement.controller.requests;

import tedorm.app.application.announcement.entity.Announcement;

import javax.validation.constraints.NotBlank;

public record AddAnnouncementRequest(
        @NotBlank
        String announcement
) {
    public Announcement toDomainEntity() {
        return new Announcement( announcement);
    }
}
