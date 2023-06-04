package tedorm.app.application.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tedorm.app.application.menu.entity.Rating;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByYemekId(Long yemekId);

    Optional<Rating> findByStudentIdAndYemekId(Long studentId, Long yemekId);
}
