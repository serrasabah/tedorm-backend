package yte.app.application.permissions.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.app.application.common.entity.BaseEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Permissions extends BaseEntity {

    private LocalDateTime permissionDates;
    private String message;

    public Permissions(LocalDateTime permissionDates, String message) {
        this.permissionDates = permissionDates;
        this.message = message;
    }

    public void update(Permissions updatePermissionDates) {
        this.message = updatePermissionDates.message;
        this.permissionDates = updatePermissionDates.permissionDates;
    }
}