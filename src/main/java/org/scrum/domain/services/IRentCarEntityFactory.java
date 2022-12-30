package org.scrum.domain.services;

import org.scrum.domain.project.Autovehicul;
import org.scrum.domain.project.Client;
import org.scrum.domain.project.Inchiriere;

import java.util.Date;

public interface IRentCarEntityFactory {

    // create rent car record with: startDate is Now, duration 1 week (7 days)
     Inchiriere creareInchiriereStandard(Client client, Autovehicul autovehicul, Date startDate, Integer zile);

    // build entity from DTO

     Inchiriere toEntity(Inchiriere inchiriereDTO);

     void initDomainServiceEntities();
}
