package tedorm.app.application.permissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.permissions.entity.Permissions;

public interface PermissionsRepository extends JpaRepository<Permissions, Long> {

}