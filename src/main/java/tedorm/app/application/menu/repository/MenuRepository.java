package tedorm.app.application.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.menu.entity.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByDateGreaterThanEqual(LocalDate date);


}