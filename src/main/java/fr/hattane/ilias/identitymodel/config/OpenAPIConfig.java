package fr.hattane.ilias.identitymodel.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

	@Value( "${spring.application.name}" )
	private String apiName;
	@Value( "${server.port}" )
	private String apiPort;
	
	@Value( "${server.servlet.context-path}" )
	private String contextPath;
	
	@Bean
	public OpenAPI myOpenAPI() {
		
		Server devServer = new Server();
		devServer.setUrl("http://localhost:" + apiPort + contextPath);
		devServer.setDescription("Développement");
		
		Server prodServer = new Server();
		prodServer.setUrl("http://env-identity:" + apiPort + contextPath);
		prodServer.setDescription("Production");
		
		Contact contact = new Contact();
		contact.setEmail("ilias.hattane@gmail.com");
		contact.setName("HATTANE Ilias");
		
		License license = new License().name("HATMO MORANE").url("http://hattane-ilias.fr/");
		
		Info info = new Info()
					.title(apiName)
					.version("0.0.1")
					.contact(contact)
					.description("Application JAVA Spring Boot offrant des fonctionnalités liées à la sécurité des utilisateurs, et à la protection d'autres applications dans le cadre de leur utilisation.")
					.license(license);

		return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
	
	}
}