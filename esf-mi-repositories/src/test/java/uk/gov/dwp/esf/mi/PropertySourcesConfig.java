package uk.gov.dwp.esf.mi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class PropertySourcesConfig {
	
	private static final Resource[] LOCAL_PROPERTIES = new ClassPathResource[]{
            new ClassPathResource("application-local.properties"),
    };
    private static final Resource[] DEV_PROPERTIES = new ClassPathResource[]{
            new ClassPathResource("application-dev.properties"),
    };

    
    @Profile("dev")
    public static class DevConfig {
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            PropertySourcesPlaceholderConfigurer propertySources = new PropertySourcesPlaceholderConfigurer();
            propertySources.setLocations(DEV_PROPERTIES);
            return propertySources;
        }
    }

	@Profile("default")
    public static class LocalConfig {
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
            PropertySourcesPlaceholderConfigurer propertySources = new PropertySourcesPlaceholderConfigurer();
            propertySources.setLocations(LOCAL_PROPERTIES);
            return propertySources;
        }
    }
    
}
