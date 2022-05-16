package org.hifivee.minebackend.domain.apply.repository;

import org.hifivee.minebackend.domain.project.repository.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {
}
