package com.gotta_watch_them_all.app.infrastructure.dataprovider.repository;

import com.gotta_watch_them_all.app.core.entity.RoleName;
import com.gotta_watch_them_all.app.infrastructure.dataprovider.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleName name);
}
