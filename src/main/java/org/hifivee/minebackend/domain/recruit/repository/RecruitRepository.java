package org.hifivee.minebackend.domain.recruit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {

    Boolean existsByProjectId(Long projectId);
    Optional<Recruit> findById(Long id);
    Page<Recruit> findAll(Pageable pageable);

    @Query(
            value = "SELECT * FROM recruit AS r WHERE r.id <= :topId",
            countQuery = "SELECT COUNT(*) FROM recruit AS r WHERE r.id <= :topId",
            nativeQuery = true
    )
    Page<Recruit> findAllByTopRecruitId(
            @Param("topId") Long topId,
            Pageable pageable);
}
