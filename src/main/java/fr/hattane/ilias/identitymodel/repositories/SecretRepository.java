package fr.hattane.ilias.identitymodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.hattane.ilias.identitymodel.model.entities.Secret;

@Repository
public interface SecretRepository extends JpaRepository<Secret, Long> {
		
}
