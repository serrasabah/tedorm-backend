package tedorm.app.application.address.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tedorm.app.application.common.entity.BaseEntity;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address extends BaseEntity {

    private String name;
    private String phoneNumber;
    private String address;

    public Address(String name, String phoneNumber,  String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

}