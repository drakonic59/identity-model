package fr.hattane.ilias.identitymodel.controllers.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.hattane.ilias.identitymodel.config.files.SecurityConfig;
import fr.hattane.ilias.identitymodel.model.api.responses.success.groups.ApiSuccessResponseGroupsList;
import fr.hattane.ilias.identitymodel.services.ILoggerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/admin/groups")
public class GroupsController {
	
	@Autowired
	private SecurityConfig security;
	
	@Autowired
	private ILoggerService logs;
	
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody()
	@Operation(
			summary  = "Récupérer la liste des groupes",
			description = "Récupérer la liste des groupes."
	)
	@ApiResponses(value = {
	        @ApiResponse(
	        		responseCode = "200", 
	        		description = "Renvoie la liste des groupes enregistrés.",
	        		content = @Content(
	        				schema = @Schema( implementation = ApiSuccessResponseGroupsList.class )
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
				  value = "/", 
				  produces="application/json"
	) 
	public ResponseEntity<String> listGroups( @RequestHeader( value = "x-api-key", required = true ) @Parameter( name = "Clé d'identification", description = "Clé unique attribuée à chaque application.", required = true ) String key) { 
		
		logs.info(key, "Action 'MODEL GROUPS LIST'.");

		ApiSuccessResponseGroupsList response = new ApiSuccessResponseGroupsList();
		response.setGroups(security.getGroups());
				
		return ResponseEntity.status(200).body(response.toString());

	}

}
