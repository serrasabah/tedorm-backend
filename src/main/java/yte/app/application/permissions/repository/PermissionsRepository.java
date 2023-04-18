package yte.app.application.permissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.app.application.permissions.entity.Permissions;

public interface PermissionsRepository extends JpaRepository<Permissions, Long> {

}