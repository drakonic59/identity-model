package fr.hattane.ilias.identitymodel.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class PingController {

	@GetMapping(
				  value = "/ping", 
				  produces="plain/text"
	) 
	public String ping() { 
	    return "pong!";
	}

}
