package fr.hattane.ilias.identitymodel.model.entities;

import java.util.List;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Authorization {
	
	@EmbeddedId
	private AuthorizationId id;
	
	private List<String> authorizations;

	public AuthorizationId getId() {
		return id;
	}

	public void setId(AuthorizationId id) {
		this.id = id;
	}

	public List<String> getAuthorizations() {
		return authorizations;
	}

	public void setAuthorizations(List<String> authorizations) {
		this.authorizations = authorizations;
	}
	
}
