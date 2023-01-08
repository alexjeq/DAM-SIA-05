package org.scrum.domain.services;

import org.scrum.domain.rent.*;

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
    // (1) Create new rent with default template: dateStart
    Integer initiereInchiriere(Date dataInceput);

    // (2) Add client to rent: client
    Integer adaugareClient(Integer idInchiriere, String numeClient, String numarTelefon, String email);

    // (3) Add car to rent: car
    Integer alegereClient(Integer idInchiriere, Client client);

    // (3) Add car to rent: car
    Integer alegereAutovehicul(Integer idInchiriere, Autovehicul autovehicul);

    // (4) Set rent return date: dataRetur
    Integer setareDataRetur(Integer idInchiriere, Date dataRetur);

    // (5) Get rent summary data: CarRentView
    InchiriereView generareInchiriereSumar(Integer idInchiriere);

    //_________________________________________________________


}
