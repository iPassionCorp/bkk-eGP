package th.co.ipassion.bkkegp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/listBkkEgp").allowedOrigins("*")
                	//.allowCredentials(false)
                    .maxAge(3600);
                registry.addMapping("/listAnnounce").allowedOrigins("*").maxAge(3600);;
                registry.addMapping("/listMethodId").allowedOrigins("*").maxAge(3600);;
                registry.addMapping("/testJson").allowedOrigins("*").maxAge(3600);;
            }
        };
    }
    
}