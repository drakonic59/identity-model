package fr.hattane.ilias.identitymodel.config.security;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import fr.hattane.ilias.identitymodel.config.security.types.BearerToken;
import fr.hattane.ilias.identitymodel.services.IAuthorizationService;
import fr.hattane.ilias.identitymodel.services.ILoggerService;
import fr.hattane.ilias.identitymodel.services.ITokenService;

@Component
public class BearerAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    
	@Value( "${server.servlet.context-path}" )
	private String contextPath;

	@Value( "${security.headers.authorization}" )
	private String authorizationHeader;
	
	@Autowired
	private ILoggerService logs;
	
	@Autowired
	private ITokenService tokens;
	
	@Autowired
	private IAuthorizationService authorizations;
	
	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
		
		logs.info("SECURITY", "Tentative de connexion Bearer : " + context.getRequest().getRequestURI() + " (" + context.getRequest().getRemoteAddr() + ")");
		
		BearerToken token;
		if ( ( token = tokens.getBearerToken(
								context.getRequest().getHeader(authorizationHeader)
							)) == null ) {
			
			logs.error(context.getRequest().getRemoteAddr(), "-> Erreur : Token invalide ou inexistant '" + context.getRequest().getHeader(authorizationHeader) + "'.");
			return new AuthorizationDecision(false);
			
		}
				
		String paths[] = context.getRequest().getRequestURI()
												.split(contextPath)[1]
													.substring(1)
														.split("/");

		logs.info("SECURITY", "-> Succès : Requête du client '" + token.getIdentity().getClientId() + "'.");
		
		return new AuthorizationDecision(authorizations.isAuthorized(token.getIdentity(), paths));
		
	}
	
}
