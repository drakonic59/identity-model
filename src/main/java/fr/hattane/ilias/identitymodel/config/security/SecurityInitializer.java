package fr.hattane.ilias.identitymodel.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityInitializer() {
		super(ApplicationSecurityConfig.class);
	}

}
