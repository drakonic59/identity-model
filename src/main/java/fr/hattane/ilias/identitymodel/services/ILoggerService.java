package fr.hattane.ilias.identitymodel.services;

public interface ILoggerService {
	
	public void info(String header, String message);
	public void error(String header, String message);
	
}
