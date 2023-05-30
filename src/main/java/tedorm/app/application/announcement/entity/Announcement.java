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

        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "admin_id", referencedColumnName = "ID")
        private Admin admin;

    public Announcement(String announcement,
                        Long adminId) {
        this.announcement = announcement;

        this.admin=new Admin();
        this.admin.setId(adminId);
    }

    public void update(Announcement updateAnnouncement) {
        this.announcement = updateAnnouncement.announcement;
    }
}