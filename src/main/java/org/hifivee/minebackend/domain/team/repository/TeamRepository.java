package org.hifivee.minebackend.domain.team.repository;

import org.hifivee.minebackend.domain.recruit.repository.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Page<Team> findAll(Pageable pageable);

    @Query(
            value = "SELECT * FROM team AS r WHERE r.id <= :topId",
            countQuery = "SELECT COUNT(*) FROM recruit AS r WHERE r.id <= :topId",
            nativeQuery = true
    )

    Page<Team> findAllByTopTeamId(
            @Param("topId") Long topId,
            Pageable pageable);
}
