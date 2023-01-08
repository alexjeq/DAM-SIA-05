package org.scrum.domain.repositories;

/*
 * Use Spring Data JpaRepository
 */
//import org.springframework.data.repository.Repository;
import org.scrum.domain.rent.Autovehicul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/*
 * URL-base: http://localhost:8080/scrum/data/api/cars
 * ALPS: http://localhost:8080/scrum/data/api/profile/cars/
 */
@RepositoryRestResource(collectionResourceRel = "cars", path = "cars")
@CrossOrigin(origins = "*")
//@Repository
public interface IAutovehiculAggregateRepository extends JpaRepository<Autovehicul, Integer>
{

}