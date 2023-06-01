package tedorm.app.application.permissions.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tedorm.app.application.address.entity.Address;
import tedorm.app.application.common.entity.BaseEntity;
import tedorm.app.application.student.entity.Student;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "ID")
    private Student student;


    public Permissions(Address address,LocalDateTime permissionDateStart,
                       LocalDateTime permissionDateEnd, String message,
                       Long studentId, Long addressId) {
        this.address = address;
        this.permissionDateStart = permissionDateStart;
        this.permissionDateEnd = permissionDateEnd;
        this.message = message;
        this.address.setId(addressId);
        this.student=new Student();
        this.student.setId(studentId);
    }

    public void update(Permissions updatePermissionDates) {
        this.message = updatePermissionDates.message;
        this.permissionDateStart = updatePermissionDates.permissionDateStart;
        this.permissionDateEnd = updatePermissionDates.permissionDateEnd;
    }
}