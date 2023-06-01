package tedorm.app.application.announcement.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tedorm.app.application.admin.entity.Admin;
import tedorm.app.application.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Announcement extends BaseEntity {

        private String announcement;

    public Announcement(String announcement) {
        this.announcement = announcement;
    }

    public void update(Announcement updateAnnouncement) {
        this.announcement = updateAnnouncement.announcement;
    }
}