package fr.hattane.ilias.identitymodel.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.hattane.ilias.identitymodel.config.files.ErrorsConfig;
import fr.hattane.ilias.identitymodel.config.files.errors.Errors;
import fr.hattane.ilias.identitymodel.config.files.errors.SimpleError;
import fr.hattane.ilias.identitymodel.config.security.types.AccessTypes;
import fr.hattane.ilias.identitymodel.config.security.types.BasicToken;
import fr.hattane.ilias.identitymodel.config.security.types.BearerToken;
import fr.hattane.ilias.identitymodel.config.security.types.Token;
import fr.hattane.ilias.identitymodel.model.api.responses.ApiErrorResponse;
import fr.hattane.ilias.identitymodel.model.api.responses.success.ApiSuccessResponseActionAuthorization;
import fr.hattane.ilias.identitymodel.model.api.responses.success.ApiSuccessResponseBasicLogin;
import fr.hattane.ilias.identitymodel.model.entities.Authorization;
import fr.hattane.ilias.identitymodel.model.entities.Client;
import fr.hattane.ilias.identitymodel.services.ILoggerService;
import fr.hattane.ilias.identitymodel.services.ITokenService;
import fr.hattane.ilias.identitymodel.services.models.IClientAuthorizationService;
import fr.hattane.ilias.identitymodel.services.models.IClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/basic")
public class BasicAuthController {

	@Autowired 
	private ErrorsConfig errors;

	@Autowired 
	private ITokenService tokens;
	
	@Autowired
	private IClientAuthorizationService clientAuthorizations;
	
	@Autowired
	private IClientService clients;
	
	@Autowired
	private ILoggerService logs;
	
	@ResponseStatus(HttpStatus.OK)
	@Operation(
			summary  = "Vérification couple Basic.",
			description = "Vérifier la validité d'un couple client_id et client_secret."
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
		logs.info(key, "Action 'BASIC VERIFY'.");
	}
	
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody()
	@Operation(
			summary  = "Connexion par Token Basic.",
			description = "Obtenir un token de sécurité permettant l'accès à d'autres applications."
	)
	@ApiResponses(value = {
	        @ApiResponse(
	        		responseCode = "200", 
	        		description = "Génération et récupération d'un token Bearer.",
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
				  value = "/login",
				  produces = "application/json"
	) 
	public ResponseEntity<String> login(@RequestHeader("Authorization") String authorization, 
										@RequestHeader( value = "x-api-key", required = true ) @Parameter( name = "Clé d'identification", description = "Clé unique attribuée à chaque application.", required = true ) String key) { 
	    
		logs.info(key, "Action 'BASIC LOGIN'. Génération d'un Token.");

		Token token = tokens.generateTokenFromBasic(authorization);
		
		ApiSuccessResponseBasicLogin response = new ApiSuccessResponseBasicLogin();
		response.setAccessType(AccessTypes.BEARER.getStartsWith().trim());
		response.setAccessToken(token.getToken());
		response.setExpirationTime(token.getExpiration());
		
		return ResponseEntity.status(200).body(response.toString());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody()
	@Operation(
			summary  = "Connexion par Token Basic.",
			description = "Obtenir un token de sécurité permettant l'accès à d'autres applications."
	)
	@ApiResponses(value = {
	        @ApiResponse(
	        		responseCode = "200", 
	        		description = "Décision liée à l'autorisation avec droits du demandeur.",
	        		content = @Content(
	        				schema = @Schema( implementation = ApiSuccessResponseActionAuthorization.class )
    				)
	        ),
	        @ApiResponse(
	        		responseCode = "206", 
	        		description = "Demandeur autorisé mais aucune autorisation enregistrée.",
	        		content = @Content(
	        				schema = @Schema( implementation = ApiSuccessResponseActionAuthorization.class )
    				)
	        ),
	        @ApiResponse(
	        		responseCode = "203", 
	        		description = "Demandeur non autorisé.",
	        		content = @Content(
	        				schema = @Schema( implementation = ApiSuccessResponseActionAuthorization.class )
    				)
	        ),
	        @ApiResponse(
	        		responseCode = "401", 
	        		description = "Non autorisé.",
	        		content = @Content(
	        				schema = @Schema( example = "" )
    				)
	        ),
	        @ApiResponse(
	        		responseCode = "403", 
	        		description = "Demandeur inconnu, accès interdit.",
	        		content = @Content(
	        				schema = @Schema( implementation = ApiErrorResponse.class )
    				)
	        ) 
	})
	@GetMapping(
				  value = "/authorize",
				  produces = "application/json"
	) 
	public ResponseEntity<String> authorize(@RequestHeader("Authorization") String authorization,
										@RequestHeader(value = "x-Authorization", required = true ) @Parameter( name = "Token à autoriser", description = "Token utilisé par l'application à autoriser.", required = true ) String xAuthorization, 
										@RequestHeader( value = "x-api-key", required = true ) @Parameter( name = "Clé d'identification", description = "Clé unique attribuée à chaque application.", required = true ) String key, 
										@RequestHeader( value = "xx-api-key", required = true ) @Parameter( name = "Clé d'identification de l'application à autoriser", description = "Clé unique attribuée à chaque application.", required = true ) String xKey) { 
	    
		logs.info(key, "Action 'BASIC AUTHORIZATION'.");
		logs.info(key, "-> Demandeur : '" + xKey + "'.");
		
		BearerToken token = tokens.getBearerToken(xAuthorization);
		if (token != null) {

			logs.info(key, "-> Token valide.");
			
			BasicToken basic = tokens.getBasicToken(authorization);

			Client target = clients.getRepository().findByClientIdEquals(basic.getClientId()).get();
			Client reference = clients.getRepository().findByClientIdEquals(token.getIdentity().getClientId()).get();
			
			Authorization auth = clientAuthorizations.getAuthorizationFor(reference.getId(), target.getId());

			ApiSuccessResponseActionAuthorization response = new ApiSuccessResponseActionAuthorization();
			if (authorization != null) {

				logs.info(key, "-> Autorisé.");
				
				response.setAuthorized(true);
				if (auth.getAuthorizations() != null && !auth.getAuthorizations().isEmpty()) {
					
					response.setAuthorizations(auth.getAuthorizations());
					return ResponseEntity.status(200).body(response.toString());
			
				}
				return ResponseEntity.status(206).body(response.toString());
				
			}
			
			logs.error(key, "-> Non Autorisé.");

			response.setAuthorized(false);
			return ResponseEntity.status(203).body(response.toString());
			
		} else {

			logs.error(key, "-> Token Inconnu.");

			SimpleError error = errors.getTechnicals().get(Errors.NOT_AUTHORIZED.getName());
			return ResponseEntity.status(error.getReturnCode()).body((new ApiErrorResponse(error)).toString());
		}
		
	}

}
