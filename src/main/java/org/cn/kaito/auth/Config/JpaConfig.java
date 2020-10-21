package org.cn.kaito.auth.Config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.kaito.game.dao.entity")
@EnableJpaRepositories(basePackages = "com.kaito.game.dao.repository")
public class JpaConfig {
}
