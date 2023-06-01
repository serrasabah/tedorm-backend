package tedorm.app.application.admin.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tedorm.app.application.authentication.entity.User;
import tedorm.app.application.common.entity.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin extends BaseEntity {

    private  String name;
    private  String surname;
    private String email;
    private String password;
    
    @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    public Admin(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public Admin(String email) {
        this.email = email;
    }

    public void update(Admin updateAdmin) {
        this.email = updateAdmin.email;
    }
}