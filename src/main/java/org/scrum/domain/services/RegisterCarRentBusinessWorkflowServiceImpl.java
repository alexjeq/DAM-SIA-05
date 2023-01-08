package org.scrum.domain.services;

import org.scrum.domain.rent.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class RegisterCarRentBusinessWorkflowServiceImpl implements IRegisterCarRentBusinessWorkflowService {
    //Suport Services

    @Autowired
    private IRentCarRepositorySpringData entityRepository;

    @Autowired
    private IRentCarEntityFactory entityFactory;

    // (1) Create new rent with default template: startDate
    @Override
    public Integer initiereInchiriere(Date dataInceput) {
        Inchiriere inchiriere = entityFactory.creareInchiriereStandard(dataInceput);
        inchiriere = entityRepository.save(inchiriere);
        return inchiriere.getIdInchiriere();
    }

    @Override
    public Integer adaugareClient(Integer idInchiriere, String numeClient, String numarTelefon, String email) {
        Inchiriere inchiriere = entityRepository.getById(idInchiriere);
        inchiriere.adaugareClient(numeClient, numarTelefon, email);
        inchiriere = entityRepository.save(inchiriere);
        return inchiriere.getIdInchiriere();
    }

    @Override
    public Integer alegereClient(Integer idInchiriere, Client client){
        Inchiriere inchiriere = entityRepository.getById(idInchiriere);
        inchiriere.setClient(client);
        inchiriere = entityRepository.save(inchiriere);
        return inchiriere.getIdInchiriere();
    }

    // (2) Add car to rent: car
    @Override
    public Integer alegereAutovehicul(Integer idInchiriere, Autovehicul autovehicul) {
        Inchiriere inchiriere = entityRepository.getReferenceById(idInchiriere);
        inchiriere.setAutovehicul(autovehicul);
        inchiriere = entityRepository.save(inchiriere);
        return inchiriere.getIdInchiriere();
    }

    // (3) Set rent duration in days: durationRentDays
    public Integer setareDataRetur(Integer idInchiriere, Date dataRetur) {
        Inchiriere inchiriere = entityRepository.getReferenceById(idInchiriere);
        inchiriere.setDataReturnare(dataRetur);
        inchiriere = entityRepository.save(inchiriere);
        return inchiriere.getIdInchiriere();
    }

    public InchiriereView generareInchiriereSumar(Integer idInchiriere) {
        Inchiriere inchiriere = entityRepository.getReferenceById(idInchiriere);
        return new InchiriereView(idInchiriere, inchiriere.getClient().getNume(), inchiriere.getAutovehicul().getModel(), inchiriere.getDataInchiriere(), inchiriere.getDataReturnare(), inchiriere.getCostInchiriere());
    }
}
