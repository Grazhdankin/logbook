package com.gt.logbook.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.gt.logbook.domain.entity.User;

@Configuration
@EnableTransactionManagement
@EnableR2dbcRepositories(basePackages = "com.gt.logbook.domain.repository", repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class PersistenceConfiguration {

    @Configuration
    @EnableR2dbcAuditing(auditorAwareRef = "auditorProvider")
    public static class R2dbcAuditingConfiguration {

        @Bean
        ReactiveAuditorAware<String> auditorAware() {
            return () -> ReactiveSecurityContextHolder.getContext()
                    .map(SecurityContext::getAuthentication)
                    .filter(Authentication::isAuthenticated)
                    .map(Authentication::getPrincipal)
                    .map(object -> ((User) object).getUsername());
        }
    }
}