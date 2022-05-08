package org.hifivee.minebackend.domain.devArea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DevAreaRepository extends JpaRepository<DevArea, Long> {
    Optional<DevArea> findByUserName(String name);
    Optional<DevArea> findByUserTechStack(Long id);


}
