package com.gt.logbook.configuration;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Profile("!test")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.gt.logbook.domain.repository", repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class PersistenceConfiguration {

    @Profile("!test")
    @Configuration
    @EnableJpaAuditing(auditorAwareRef = "auditorProvider")
    public static class JpaAuditingConfiguration {

        @Bean
        public AuditorAware<String> auditorProvider() {
            return () -> Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
        }
    }
}