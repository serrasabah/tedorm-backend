package tedorm.app.application.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.request.entity.Requests;

public interface RequestRepository extends JpaRepository<Requests, Long> {

}