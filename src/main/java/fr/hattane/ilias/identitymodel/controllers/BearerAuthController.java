package fr.hattane.ilias.identitymodel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.hattane.ilias.identitymodel.config.security.types.AccessTypes;
import fr.hattane.ilias.identitymodel.config.security.types.BearerToken;
import fr.hattane.ilias.identitymodel.model.api.responses.success.ApiSuccessResponseBasicLogin;
import fr.hattane.ilias.identitymodel.services.ILoggerService;
import fr.hattane.ilias.identitymodel.services.ITokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/bearer")
public class BearerAuthController {

	@Autowired 
	private ITokenService tokens;
	
	@Autowired
	private ILoggerService logs;
	
	@ResponseStatus(HttpStatus.OK)
	@Operation(
			summary  = "Vérification Token Bearer.",
			description = "Vérifier la validité d'un Token Bearer."
	)
	@ApiResponses(value = {
	        @ApiResponse(
	        		responseCode = "200", 
	        		description = "Ne retourne rien.",
	        		content = @Content(
	        				schema = @Schema( example = "" )
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
				  value = "/verify"
	) 
	public void verify( @RequestHeader( value = "x-api-key", required = true ) @Parameter( name = "Clé d'identification", description = "Clé unique attribuée à chaque application.", required = true ) String key) {
		logs.info(key, "Action 'BEARER VERIFY'.");
	}
	
	@ResponseStatus(HttpStatus.OK)
	@Operation(
			summary  = "Vérification Token Bearer.",
			description = "Vérifier la validité d'un Token Bearer."
	)
	@ApiResponses(value = {
	        @ApiResponse(
	        		responseCode = "200", 
	        		description = "Retourne les informations relatives au Token.",
	        		content = @Content(
	        				schema = @Schema( implementation = ApiSuccessResponseBasicLogin.class )
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
				  value = "/informations",
				  produces = "application/json"
	) 
	public ResponseEntity<String> informations(@RequestHeader("Authorization") String authorization, 
												@RequestHeader( value = "x-api-key", required = true ) @Parameter( name = "Clé d'identification", description = "Clé unique attribuée à chaque application.", required = true ) String key) {
		
		logs.info(key, "Action 'BEARER INFORMATIONS'.");
		
		BearerToken token = tokens.getBearerToken(authorization);
		
		ApiSuccessResponseBasicLogin response = new ApiSuccessResponseBasicLogin();
		response.setAccessType(AccessTypes.BEARER.getStartsWith().trim());
		response.setAccessToken(token.getToken());
		response.setExpirationTime(token.getRun().getExpiration());
		
		return ResponseEntity.status(200).body(response.toString());
		
	}

}
