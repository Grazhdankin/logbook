package com.gt.logbook.configuration;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Profile("!test")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.gt.logbook.domain.repository")
public class PersistenceConfiguration {

    @Profile("!test")
    @Configuration
    @EnableJpaAuditing(auditorAwareRef = "auditorProvider")
    public static class JpaAuditingConfiguration {

        @Bean
        public AuditorAware<String> auditorProvider() {

        /*
          if you are using spring security, you can get the currently logged username with following code segment.
          SecurityContextHolder.getContext().getAuthentication().getName()
         */
            return () -> Optional.of("system");
        }
    }
}