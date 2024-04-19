package fr.hattane.ilias.identitymodel.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import fr.hattane.ilias.identitymodel.services.ILoggerService;

@Service
public class loggerService implements ILoggerService {

	private static final Logger LOGGER = LogManager.getLogger( loggerService.class );
	
	@Override
	public void info(String header, String message) {
		if (header != null && message != null)
			LOGGER.info("[" + header + "] " + message);
	}

	@Override
	public void error(String header, String message) {
		if (header != null && message != null)
			LOGGER.error("[" + header + "] " + message);
	}

}
