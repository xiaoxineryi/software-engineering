package org.cn.kaito.auth.Config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.cn.kaito.auth.Dao.Entity")
@EnableJpaRepositories(basePackages = "org.cn.kaito.auth.Dao.Repository")
public class JpaConfig {
}
