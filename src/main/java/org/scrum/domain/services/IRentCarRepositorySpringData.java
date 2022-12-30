package org.scrum.domain.services;

import org.scrum.domain.project.Inchiriere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * http://localhost:8080/scrum/data/sprintprojects
 */
@Repository
public interface IRentCarRepositorySpringData extends JpaRepository<Inchiriere, Integer> {
    List<Inchiriere> findAll(); // extends Repository only

    //Queriable Named Operations
//    List<Inchiriere> findByClient(String name);
//    Inchiriere findByIdInchiriere(Integer idInchiriere);

    // Queriable Annotated Operation
//    @Query("SELECT q1 from Inchiriere q1 inner join Client q2 on q1.idClient = q2.idClient where q2.numeClient like %:nume%")
//    List<Inchiriere> findByClient(@Param("nume") String numeClient);

    @Query("SELECT i from Inchiriere i where i.idInchiriere = :id")
    List<Inchiriere> findByIdInchiriere(@Param("id") Integer idInchiriere);
}
