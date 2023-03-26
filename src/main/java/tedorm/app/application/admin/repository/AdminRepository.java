package tedorm.app.application.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.admin.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}