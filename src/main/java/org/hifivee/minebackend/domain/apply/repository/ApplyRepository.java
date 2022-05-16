package org.hifivee.minebackend.domain.apply.repository;

import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.domain.recruit.repository.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {
    List<Apply> findByAccount(Account account);
    List<Apply> findByRecruit(Recruit recruit);
}
