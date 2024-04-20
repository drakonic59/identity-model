package fr.hattane.ilias.identitymodel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.hattane.ilias.identitymodel.services.ILoggerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/health")
public class HealthController {

	public static final String PONG = "pong!";
	
	@Autowired
	private ILoggerService logs;
	
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody()
	@Operation(
			summary  = "Vérifier Etat API",
			description = "Vérifier si le service est disponible."
	)
	@ApiResponses(value = {
	        @ApiResponse(
	        		responseCode = "200", 
	        		description = "Renvoie la chaine \"" + PONG + "!\" si le service est disponible.",
	        		content = @Content(
	        				schema = @Schema( example = PONG )
    				)
	        ),
	        @ApiResponse(
	        		responseCode = "401", 
	        		description = "Non autorisé.",
	        		content = @Content(
	        				schema = @Schema( example = "" )
    				)
	        ) 
	})
	@GetMapping(
				  value = "/ping", 
				  produces="plain/text"
	) 
	public String ping( @RequestHeader( value = "x-api-key", required = true ) @Parameter( name = "Clé d'identification", description = "Clé unique attribuée à chaque application.", required = true ) String key) { 
		logs.info(key, "Action 'PING'.");
		return PONG;
	}

}
