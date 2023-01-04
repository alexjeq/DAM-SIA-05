package org.scrum.domain.services;

import org.scrum.domain.rent.Autovehicul;
import org.scrum.domain.rent.Client;
import org.scrum.domain.rent.Inchiriere;

import java.util.Date;

public interface IRentCarEntityFactory {

    // create rent car record with: startDate is Now, duration 1 week (7 days)
     Inchiriere creareInchiriereStandard(Date startDate);

    // build entity from DTO

     Inchiriere toEntity(Inchiriere inchiriereDTO);

     void initDomainServiceEntities();
}
