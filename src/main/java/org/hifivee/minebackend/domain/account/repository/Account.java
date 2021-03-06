package org.hifivee.minebackend.domain.account.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hifivee.minebackend.domain.team.repository.Team;
import org.hifivee.minebackend.global.entity.BaseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column
    private String nickname;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private String position;

    @Column
    private String link;

    @Column
    private String message;

    @Column
    private String techStack;

    @Column
    private String experience;

    @ManyToOne(targetEntity = Team.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Builder
    public Account(String email, String password, Authority authority,
                   String nickname, String phone, String address,
                   String position, String link, String message, String techStack, String experience, Team team) {
        this.email = email;
        this.password = password;
        this.authority = authority;
        this.nickname = nickname;
        this.phone = phone;
        this.address = address;
        this.position = position;
        this.link = link;
        this.message = message;
        this.techStack = techStack;
        this.experience = experience;
        this.team = team;
    }

    public void update(String nickname, String phone, String address,
                       String position, String link, String message, String techStack, String experience) {
        this.nickname = nickname;
        this.phone = phone;
        this.address = address;
        this.position = position;
        this.link = link;
        this.message = message;
        this.techStack = techStack;
        this.experience = experience;
    }

    public void updatePassword(PasswordEncoder passwordEncoder, String password) {
        this.password = passwordEncoder.encode(password);
    }
}
