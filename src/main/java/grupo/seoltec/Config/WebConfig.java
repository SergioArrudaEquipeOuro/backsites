package grupo.seoltec.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Mapeia o CORS para todas as URLs da sua aplicação
                .allowedOrigins("*") // Permite solicitações de origens específicas (substitua pelo seu frontend URL)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite métodos HTTP específicos
                .allowedHeaders("*"); // Permite todos os cabeçalhos
    }
}