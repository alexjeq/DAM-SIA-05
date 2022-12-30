package org.scrum.domain.services;

import org.scrum.domain.project.Autovehicul;
import org.scrum.domain.project.Client;
import org.scrum.domain.project.Inchiriere;
import org.scrum.domain.project.PermisConducere;
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

    // (1) Create new rent with default template: client, startDate
    @Override
    public Integer initiereInchiriere(Client client, Autovehicul autovehicul, Date dataInceput, Integer zile) {
        Inchiriere inchiriere = entityFactory.creareInchiriereStandard(client, autovehicul, dataInceput, zile);
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
    public Integer setareDurataInchiriere(Integer idInchiriere, Integer zile) {
        Inchiriere inchiriere = entityRepository.getReferenceById(idInchiriere);
        inchiriere.setZileInchiriere(zile);
        inchiriere = entityRepository.save(inchiriere);
        return inchiriere.getIdInchiriere();
    }

    // (4) Get rent amount: rentID, car
    public Double generareCostInchiriere(Integer idInchiriere) {
        Inchiriere inchiriere = entityRepository.getReferenceById(idInchiriere);
        return inchiriere.getCostInchiriere();
    }

    // (5) Check client's driver's license
    public Integer verificarePermisConducere(Integer idInchiriere, PermisConducere permisConducere) {
        Inchiriere inchiriere = entityRepository.getReferenceById(idInchiriere);
        Integer idClient = inchiriere.getClient().getIdClient();
        return null;
    }

    // (6) Get rent summary data: CarRentView
    public Inchiriere generareInchiriereSumar(Integer idInchiriere) {
        Inchiriere inchiriere = entityRepository.getReferenceById(idInchiriere);
        return inchiriere;
    }

}
