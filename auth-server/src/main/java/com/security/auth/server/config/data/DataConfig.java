package com.security.auth.server.config.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.security.auth.server.repo")
@EntityScan(basePackages = {"com.security.auth.server.domain"})
public class DataConfig {

}
