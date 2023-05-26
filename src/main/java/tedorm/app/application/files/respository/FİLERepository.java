package tedorm.app.application.files.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.files.entity.ImageData;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {

    Optional<ImageData> findByName(String fileName);

    boolean existsByName(String name);

}
