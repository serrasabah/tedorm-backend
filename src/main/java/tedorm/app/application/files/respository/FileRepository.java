package tedorm.app.application.files.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import tedorm.app.application.files.entity.FileData;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<FileData, Long> {

    Optional<FileData> findByName(String name);

    FileData findByFilename(String filename);

    boolean existsByName(String name);

    List<FileData> findByStudentId(Long id);

}
