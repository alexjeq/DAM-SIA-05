package org.scrum.domain.services;

import org.scrum.domain.rent.Autovehicul;
import org.scrum.domain.rent.Client;
import org.scrum.domain.rent.Inchiriere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Logger;

@Component
@Scope("singleton")
public class RentCarEntityFactory implements IRentCarEntityFactory {

    private static final Logger logger = Logger.getLogger(RentCarEntityFactory.class.getName());

    public RentCarEntityFactory() {
        logger.info(">>> BEAN: CarRentEntityFactoryCDI instantiated!");
    }

    @Override
    public Inchiriere creareInchiriereStandard(Date startDate) {
        return new Inchiriere(startDate);
    }

    // Dependency
    @Autowired
    private IRentCarRepositorySpringData entityRepository;

    public void setRentCarEntityRepository(IRentCarRepositorySpringData repository) {
        this.entityRepository = repository;
    }

    public RentCarEntityFactory(IRentCarRepositorySpringData entityRepository) {
        super();
        this.entityRepository = entityRepository;
    }

    // create RentCar entity from DTO and update with DTO
    @Override
    public Inchiriere toEntity(Inchiriere inchiriereDTO) {
        Inchiriere inchiriereEntity = this.entityRepository.getById(inchiriereDTO.getIdInchiriere());
        inchiriereEntity.setClient(inchiriereDTO.getClient());
        inchiriereDTO.setDataInchiriere(inchiriereDTO.getDataInchiriere());
        return inchiriereEntity;
    }

    @Override
    public void initDomainServiceEntities() {
        logger.info(">> PostConstruct :: initDomainServiceEntities");
        for (int i = 1; i < 4; i++) {
//            Client newClient = new Client("Client_" + i, "07" + String.valueOf(i).repeat(8), "client_" + i + "@gmail.com");
//            Autovehicul newAutovehicul = new Autovehicul("Model" + i, "RO " + String.valueOf(i).repeat(3), i * 100.00);
            Inchiriere newInchiriere = creareInchiriereStandard(new Date());
            entityRepository.save(newInchiriere);
        }
        logger.info(">> EntityRepository inchiriere.count :: " + entityRepository.count());
    }
}
