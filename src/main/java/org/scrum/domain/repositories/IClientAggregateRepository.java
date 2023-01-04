package org.scrum.domain.repositories;

import java.util.List;

/*
 * Use Spring Data JpaRepository
 */
//import org.springframework.data.repository.Repository;
import org.scrum.domain.rent.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;


//@Repository
@RepositoryRestResource
public interface IClientAggregateRepository extends JpaRepository<Client, Integer>
{
    List<Client> findAll(); // extends Repository only

    // Queriable Named Operations
    List<Client> findByNume(String nume);
    Client findByIdClient(Integer projectNo);

    // Queriable Annotated Operation
    @RestResource(path="byClientName", rel = "byClientName")
    @Query("SELECT c FROM Client c WHERE c.nume like %:nume%")
    List<Client> findByProjectName(@Param("nume") String nume);
}