package ru.netology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import ru.netology.profile.SystemProfile;
import ru.netology.profile.DevProfile;
import ru.netology.profile.ProductionProfile;

@Configuration
public class WebConfig {

	@Bean
	@ConditionalOnProperty(prefix = "netology.profile", name = "dev")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
	@ConditionalOnProperty(prefix = "netology.profile", name = "prod")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}
