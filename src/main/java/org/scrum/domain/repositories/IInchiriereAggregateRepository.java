package org.scrum.domain.repositories;

/*
 * Use Spring Data JpaRepository
 */
//import org.springframework.data.repository.Repository;
import org.scrum.domain.rent.Inchiriere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*
 * URL-base: http://localhost:8080/scrum/data/api/rents
 * ALPS: http://localhost:8080/scrum/data/api/profile/rents/
 */
@RepositoryRestResource(collectionResourceRel = "rents", path = "rents")
//@Repository
public interface IInchiriereAggregateRepository extends JpaRepository<Inchiriere, Integer>
{

}