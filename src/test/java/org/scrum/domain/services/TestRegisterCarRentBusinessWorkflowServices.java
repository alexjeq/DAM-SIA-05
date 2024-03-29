package org.scrum.domain.services;

import org.junit.jupiter.api.Test;
import org.scrum.domain.rent.Autovehicul;
import org.scrum.domain.rent.Client;
import org.scrum.domain.rent.InchiriereView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.logging.Logger;

@SpringBootTest
class TestRegisterCarRentBusinessWorkflowServices {

    private static final Logger logger = Logger.getLogger(TestRegisterCarRentBusinessWorkflowServices.class.getName());

    @Autowired
    private IRegisterCarRentBusinessWorkflowService registerCarRentBusinessWorkflowService;

    @Test
    public void test_WorkflowService() {
        logger.info("Domain Service implementation instance:: " + registerCarRentBusinessWorkflowService);
        logger.info("Domain Service implementation class::" + registerCarRentBusinessWorkflowService.getClass().getName());

        Date currentDate = new Date();
        Date after2Days = new Date(currentDate.getTime() + 1000 * 60 * 60 * 24 * 2);
        Autovehicul autovehicul = new Autovehicul("Model Test", "RO xxx", 999.00);
        Integer idInchiriere = registerCarRentBusinessWorkflowService.initiereInchiriere(currentDate);
        registerCarRentBusinessWorkflowService.setareDataRetur(idInchiriere, after2Days);
        registerCarRentBusinessWorkflowService.alegereAutovehicul(idInchiriere, autovehicul);
        // 1 case
        registerCarRentBusinessWorkflowService.adaugareClient(idInchiriere, "Client 1", "0711111111", "test@gmail.com");
        // 2 case
        // Client client = new Client("Test client", "07xxxxxxxx", "test_client@gmail.com");
        // registerCarRentBusinessWorkflowService.alegereClient(idInchiriere, client);
        InchiriereView viewData = registerCarRentBusinessWorkflowService.generareInchiriereSumar(idInchiriere);
        logger.info(viewData.toString());
    }

}
