package org.scrum.domain.services;

import org.scrum.domain.project.Autovehicul;
import org.scrum.domain.project.Client;
import org.scrum.domain.project.Inchiriere;
import org.scrum.domain.project.PermisConducere;

import java.util.Date;

/*
 * Business Workflow Steps/Actions:
 * (1) Create new rent with default template: client, car, startDate, durationRentDays
 * (2) Check client's driver's license and approve rent
 * (3) Change car status
 * (4) Generate the invoice for rent
 * (5) Get rent summary data: CarRentView
 */
public interface IRegisterCarRentBusinessWorkflowService {
    // (1) Create new rent with default template: client, car, startDate, durationRentDays
    Integer initiereInchiriere(Client client, Autovehicul autovehicul, Date dataInceput, Integer zile);

    // (2) Add car to rent: car
    Integer alegereAutovehicul(Integer idInchiriere, Autovehicul autovehicul);

    // (3) Set rent duration in days: durationRentDays
    Integer setareDurataInchiriere(Integer idInchiriere, Integer zile);

    // (4) Get rent amount: rentID, car
    Double generareCostInchiriere(Integer idInchiriere);

    // (5) Check client's driver's license
    Integer verificarePermisConducere(Integer idInchiriere, PermisConducere permisConducere);

    // (6) Get rent summary data: CarRentView
    Inchiriere generareInchiriereSumar(Integer idInchiriere);
}
