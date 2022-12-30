package org.scrum.domain.services;

import org.junit.jupiter.api.Test;
import org.scrum.domain.project.Autovehicul;
import org.scrum.domain.project.Client;
import org.scrum.domain.project.Inchiriere;
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
//        logger.info("Domain Service implementation instance:: ");
        logger.info("Domain Service implementation instance:: " + registerCarRentBusinessWorkflowService);
        logger.info("Domain Service implementation class::" + registerCarRentBusinessWorkflowService.getClass().getName());

        Date currentDate = new Date();
        Client client = new Client("Test client", "07xxxxxxxx", "test_client@gmail.com");
        Autovehicul autovehicul = new Autovehicul("Model Test", "RO xxx",999.00);
        Integer idInchiriere = registerCarRentBusinessWorkflowService.initiereInchiriere(client, autovehicul, currentDate, 5);

        Inchiriere viewData = registerCarRentBusinessWorkflowService.generareInchiriereSumar(idInchiriere);
//        logger.info(String.valueOf(idInchiriere));
        logger.info(viewData.toString());
    }

}
