package tedorm.app.application.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.menu.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {



}