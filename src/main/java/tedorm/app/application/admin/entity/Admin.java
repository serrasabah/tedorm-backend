package tedorm.app.application.admin.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tedorm.app.application.common.entity.BaseEntity;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin extends BaseEntity {

    private  String name;
    private  String surname;

    public Admin(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void update(Admin updateAdmin) {
        this.name = name;
        this.surname = surname;
    }
}