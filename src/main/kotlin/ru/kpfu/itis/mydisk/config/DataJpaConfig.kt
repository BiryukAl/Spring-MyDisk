package ru.kpfu.itis.mydisk.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["ru.kpfu.itis.mydisk.data"])
class DataJpaConfig
