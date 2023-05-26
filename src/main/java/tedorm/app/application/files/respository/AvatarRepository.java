package tedorm.app.application.files.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.files.entity.AvatarData;
import tedorm.app.application.files.entity.FileData;

import java.util.List;
import java.util.Optional;

public interface AvatarRepository extends JpaRepository<AvatarData,Long> {

        List<AvatarData> findByStudentId(Long id);

        Optional<AvatarData> findByName(String fileName);
        }
