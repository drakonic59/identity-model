package fr.hattane.ilias.identitymodel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.hattane.ilias.identitymodel.model.entities.Authorization;
import fr.hattane.ilias.identitymodel.model.entities.AuthorizationId;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, AuthorizationId> {
	
    List<Authorization> findByIdReference(String reference);

    List<Authorization> findByIdTarget(String target);
}
