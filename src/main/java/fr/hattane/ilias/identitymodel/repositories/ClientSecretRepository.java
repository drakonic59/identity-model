package fr.hattane.ilias.identitymodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.hattane.ilias.identitymodel.model.entities.ClientSecret;

@Repository
public interface ClientSecretRepository extends JpaRepository<ClientSecret, String> {
	
}
