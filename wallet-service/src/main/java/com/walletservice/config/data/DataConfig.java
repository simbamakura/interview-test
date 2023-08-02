package com.walletservice.config.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.walletservice.data.repo")
@EntityScan(basePackages = {"com.walletservice.data.entity"})
public class DataConfig {
}
