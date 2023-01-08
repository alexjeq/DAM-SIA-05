package org.scrum.domain.repositories;

/*
 * Use Spring Data JpaRepository
 */
//import org.springframework.data.repository.Repository;

import org.scrum.domain.rent.Client;
import org.scrum.domain.rent.Inchiriere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;

/*
 * URL-base: http://localhost:8080/scrum/data/api/rents
 * ALPS: http://localhost:8080/scrum/data/api/profile/rents/
 */
//@RepositoryRestResource(collectionResourceRel = "rents", path = "rents")
@RepositoryRestResource(collectionResourceRel = "rents", path = "rents")
@CrossOrigin(origins = "*")
public interface IInchiriereAggregateRepository extends JpaRepository<Inchiriere, Integer> {
    List<Inchiriere> findAll(); // extends Repository only

    // Queriable Named Operations
    List<Inchiriere> findByDataInchiriere(Date dataInchiriere);

    Inchiriere findByIdInchiriere(Integer idInchiriere);

    Client findByClient(Integer idInchiriere);

    // Queriable Annotated Operation
    @RestResource(path = "byStartDate", rel = "byStartDate")
    @Query("SELECT i FROM Inchiriere i WHERE i.dataInchiriere = :dataStart")
    List<Inchiriere> findByStartDate(@Param("dataStart") Date dataStart);

    @RestResource(path = "byClientName", rel = "byClientName")
    @Query("SELECT i FROM Inchiriere i inner join Client c WHERE c.nume like %:nume%")
    List<Inchiriere> findByClientName(@Param("nume") String nume);

    @RestResource(path = "byCarModel", rel = "byCarModel")
    @Query("SELECT i FROM Inchiriere i inner join Autovehicul c WHERE c.model like %:model%")
    List<Inchiriere> findByCarModel(@Param("model") String model);
}