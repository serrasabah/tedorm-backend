package yte.app.application.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.app.application.authentication.entity.User;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}