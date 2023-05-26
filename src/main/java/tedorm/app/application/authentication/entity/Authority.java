package tedorm.app.application.authentication.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import tedorm.app.application.common.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@NoArgsConstructor
public class Authority extends BaseEntity implements GrantedAuthority {
    private String authority;

    @ManyToMany(mappedBy = "authorities")
    public List<User> users = new ArrayList<>();

    public Authority(String authority) {
        this.authority = authority;
    }
}