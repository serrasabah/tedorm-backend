package yte.app.application.permissions.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yte.app.application.address.entity.Address;
import yte.app.application.common.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Permissions extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "ID")
    private Address address;
    private LocalDateTime permissionDateStart;
    private LocalDateTime permissionDateEnd;
    private String message;

    public Permissions(Address address,LocalDateTime permissionDateStart, LocalDateTime permissionDateEnd, String message) {
        this.address = address;
        this.permissionDateStart = permissionDateStart;
        this.permissionDateEnd = permissionDateEnd;
        this.message = message;
    }

    public void update(Permissions updatePermissionDates) {
        this.message = updatePermissionDates.message;
        this.permissionDateStart = updatePermissionDates.permissionDateStart;
        this.permissionDateEnd = updatePermissionDates.permissionDateEnd;
    }
}