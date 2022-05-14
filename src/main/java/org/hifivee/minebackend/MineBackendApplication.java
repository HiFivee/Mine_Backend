package org.hifivee.minebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MineBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MineBackendApplication.class, args);
    }

}
