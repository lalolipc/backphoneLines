package com.utn.PhoneLines.configuration;

import com.utn.PhoneLines.session.InfrastructureSessionFilter;
import com.utn.PhoneLines.session.BackofficeSessionFilter;
import com.utn.PhoneLines.session.SessionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@org.springframework.context.annotation.Configuration
@PropertySource("application.properties")
public class Configuration {

    @Autowired
    SessionFilter sessionFilter;
    @Autowired
    InfrastructureSessionFilter infrastructureSessionFilter;
    @Autowired
    BackofficeSessionFilter backofficeSessionFilter;

    @Bean
    public FilterRegistrationBean clientFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter);
        registration.addUrlPatterns("/api/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean backofficeFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(backofficeSessionFilter);
        registration.addUrlPatterns("/backoffice/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean antennaFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(infrastructureSessionFilter);
        registration.addUrlPatterns("/infrastructure/*");
        return registration;
    }




}
