package com.works.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class ConfigAuditing implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Optional<String> optional = Optional.of("ali@mail.com");
        return optional;
    }

}
