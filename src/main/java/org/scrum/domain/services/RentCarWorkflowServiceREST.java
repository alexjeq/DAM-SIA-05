package org.scrum.domain.services;

import org.scrum.domain.rent.Inchiriere;
import org.scrum.domain.repositories.IInchiriereAggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@RestController
@RequestMapping("/workflow/registerCarRent") // REST.RPC Style
public class RentCarWorkflowServiceREST {
    private static Logger logger = Logger.getLogger(RentCarWorkflowServiceREST.class.getName());

    //Support Services
    @Autowired
    private IInchiriereAggregateRepository entityRepository;

    @Autowired
    private IRentCarEntityFactory entityFactory;

    @RequestMapping(
            path = "/initiereInchiriere/{dataInceput}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    // (1) Create new rent with default template: startDate
    public Integer initiereInchiriere(
            @PathVariable("dataInceput") String dataInceput) throws Exception {
        logger.info(">>> Start Procesing: initiereInchiriere /" + dataInceput);

        Date startDateFromString = new SimpleDateFormat("dd-MM-yyyy").parse(dataInceput);
        //
        Inchiriere inchiriere = entityFactory.creareInchiriereStandard(startDateFromString);
        System.out.println(">>> Car rent init: " + inchiriere);
        Date tomorrow = new Date(startDateFromString.getTime() + 1000 * 60 * 60 * 24 * 1);
        inchiriere.setDataInchiriere(tomorrow);
        //
        entityRepository.save(inchiriere);
        //
        Integer id = inchiriere.getIdInchiriere();
        logger.info(">>> End Procesing: initiereInchiriere/" + tomorrow + ": " + id);
        return id;
    }

    @RequestMapping(path = "/initiereInchiriere", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})

    @ResponseBody
    public Integer initiereInchiriereRequestHandler(
            @RequestParam("dataInceput") String dataInceput) throws Exception {
        return initiereInchiriere(dataInceput);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public WorkflowDescriptor getWorkflowDescriptor() {
        WorkflowDescriptor descriptor = new WorkflowDescriptor();

        logger.info(">>> End Procesing: getWorkflowDescriptor:" + descriptor.toString());
        return descriptor;
    }


}

@XmlRootElement(name = "WorkflowDescriptor")
@XmlAccessorType(XmlAccessType.NONE)
class WorkflowDescriptor {
    private String serviceBaseURL = AtomLink.BASE_URL + "/workflow/registerCarRent";

    @XmlElement(name = "action-initiereInchiriere")
    public AtomLink getLinkOfActionInitiereInchiriere() throws Exception {
        String restUrl = serviceBaseURL + "/initiereInchiriere/:dataInceput";
        return new AtomLink(restUrl, "action-initiereInchiriere");
    }

    @XmlElement(name = "request-initiereInchiriere")
    public AtomLink getLinkOfRequestInitiereInchiriere() throws Exception {
        String restUrl = serviceBaseURL + "/initiereInchiriere?dataInceput=:dataInceput";
        return new AtomLink(restUrl, "request-initiereInchiriere");
    }

    @Override
    public String toString() {
        try {
            return "WorkflowDescriptor [getLinkOfActionInitiereInchiriere()=" + getLinkOfActionInitiereInchiriere()
                    + ", getLinkOfRequestInitiereInchiriere()=" + getLinkOfRequestInitiereInchiriere() + "]";
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

@XmlAccessorType(value = XmlAccessType.NONE)
class AtomLink implements Serializable {
    public static String BASE_URL = "http://localhost:8080/scrum/data";

    private URI href;
    private String rel;
    private String type;

    @XmlAttribute(name = "href")
    public URI getHref() {
        return href;
    }

    @XmlAttribute(name = "rel")
    public String getRel() {
        return rel;
    }

    @XmlAttribute(name = "type")
    public String getType() {
        return type;
    }

    public AtomLink(String href, String rel) throws Exception {
        super();
        this.href = new URI(href);
        this.rel = rel;
        this.type = "text/html";
    }

    public AtomLink() {
    }
}
