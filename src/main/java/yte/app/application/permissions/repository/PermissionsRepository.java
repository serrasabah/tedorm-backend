package yte.app.application.permissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.app.application.permissions.entity.Permissions;

import java.time.LocalDateTime;

public interface PermissionsRepository extends JpaRepository<Permissions, Long> {

    boolean existsPermissions(LocalDateTime permissionDates);

}