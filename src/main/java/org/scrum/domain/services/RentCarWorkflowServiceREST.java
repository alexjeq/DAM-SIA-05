package org.scrum.domain.services;

import org.scrum.domain.rent.Autovehicul;
import org.scrum.domain.rent.Client;
import org.scrum.domain.rent.Inchiriere;
import org.scrum.domain.rent.InchiriereView;
import org.scrum.domain.repositories.IAutovehiculAggregateRepository;
import org.scrum.domain.repositories.IClientAggregateRepository;
import org.scrum.domain.repositories.IInchiriereAggregateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/workflow/registerCarRent") // REST.RPC Style
public class RentCarWorkflowServiceREST {
    private static Logger logger = Logger.getLogger(RentCarWorkflowServiceREST.class.getName());

    //Support Services
    @Autowired
    private IInchiriereAggregateRepository entityRepository;

    @Autowired
    private IClientAggregateRepository entityRepositoryClient;

    @Autowired
    private IAutovehiculAggregateRepository entityRepositoryAutovehicul;

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
        Inchiriere inchiriere = entityFactory.creareInchiriereStandard(startDateFromString);
        System.out.println(">>> Car rent init: " + inchiriere);
        Date tomorrow = new Date(startDateFromString.getTime() + 1000 * 60 * 60 * 24 * 1);
        inchiriere.setDataInchiriere(tomorrow);
        entityRepository.save(inchiriere);
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
            path = "/adaugareClient/{idInchiriere}/{numeClient}/{numarTelefon}/{email}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    // (1) Create new rent with default template: startDate
    public Integer adaugareClient(
            @PathVariable("idInchiriere") Integer idInchiriere,
            @PathVariable("numeClient") String numeClient,
            @PathVariable("numarTelefon") String numarTelefon,
            @PathVariable("email") String email) throws Exception {
        logger.info(">>> Start Procesing: adaugareClient /" + idInchiriere + "/" + numeClient + "/" + numarTelefon + "/" + email);

        Inchiriere inchiriere = entityRepository.getById(idInchiriere);
        inchiriere.adaugareClient(numeClient, numarTelefon, email);
        entityRepository.save(inchiriere);

        Integer id = inchiriere.getClient().getIdClient();
        logger.info(">>> End Procesing: adaugareClient/" + id);
        return id;
    }

    @RequestMapping(path = "/adaugareClient", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})

    @ResponseBody
    public Integer adaugareClientRequestHandler(
            @RequestParam("idInchiriere") Integer idInchiriere,
            @RequestParam("numeClient") String numeClient,
            @RequestParam("numarTelefon") String numarTelefon,
            @RequestParam("email") String email) throws Exception {
        return adaugareClient(idInchiriere, numeClient, numarTelefon, email);
    }

    @RequestMapping(
            path = "/alegereClient/{idInchiriere}/{idClient}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    // (1) Create new rent with default template: startDate
    public Integer alegereClient(
            @PathVariable("idInchiriere") Integer idInchiriere,
            @PathVariable("idClient") Integer idClient) throws Exception {
        logger.info(">>> Start Procesing: alegereClient /" + idInchiriere + "/" + idInchiriere + "/" + idClient);

        Inchiriere inchiriere = entityRepository.getById(idInchiriere);
        Client client = entityRepositoryClient.getById(idClient);
        inchiriere.setClient(client);
        entityRepository.save(inchiriere);

        Integer id = inchiriere.getClient().getIdClient();
        logger.info(">>> End Procesing: alegereClient/" + id);
        return id;
    }

    @RequestMapping(path = "/alegereClient", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})

    @ResponseBody
    public Integer alegereClientRequestHandler(
            @RequestParam("idInchiriere") Integer idInchiriere,
            @RequestParam("idClient") Integer idClient) throws Exception {
        return alegereClient(idInchiriere, idClient);
    }

    @RequestMapping(
            path = "/alegereAutovehicul/{idInchiriere}/{idAutovehicul}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    // (1) Create new rent with default template: startDate
    public Integer alegereAutovehicul(
            @PathVariable("idInchiriere") Integer idInchiriere,
            @PathVariable("idAutovehicul") Integer idAutovehicul) throws Exception {
        logger.info(">>> Start Procesing: alegereAutovehicul /" + idInchiriere + "/" + idInchiriere + "/" + idAutovehicul);

        //
        Inchiriere inchiriere = entityRepository.getById(idInchiriere);
        Autovehicul autovehicul = entityRepositoryAutovehicul.getById(idAutovehicul);
        Date start = inchiriere.getDataInchiriere();
        Date end = inchiriere.getDataReturnare();
        long millis = System.currentTimeMillis();
        Date today = new Date(millis);
        inchiriere.setAutovehicul(autovehicul);
        if (today.compareTo(start) >= 0 && today.compareTo(end) <= 0) {
            inchiriere.setStatus("1");
            inchiriere.getAutovehicul().setStatus("1");
        } else {
            inchiriere.setStatus("0");
            inchiriere.getAutovehicul().setStatus("0");
        }
        entityRepository.save(inchiriere);

        Integer id = inchiriere.getAutovehicul().getIdAutovehicul();
        logger.info(">>> End Procesing: alegereAutovehicul/" + id);
        return id;
    }

    @RequestMapping(path = "/alegereAutovehicul", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})

    @ResponseBody
    public Integer alegereAutovehiculRequestHandler(
            @RequestParam("idInchiriere") Integer idInchiriere,
            @RequestParam("idAutovehicul") Integer idAutovehicul) throws Exception {
        return alegereAutovehicul(idInchiriere, idAutovehicul);
    }

    @RequestMapping(
            path = "/setareDataRetur/{idInchiriere}/{dataRetur}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    // (3) Create new project with default template: projectName, startDate
    public Integer setareDataRetur(
            @PathVariable("idInchiriere") Integer idInchiriere,
            @PathVariable("dataRetur") String dataRetur) throws Exception {
        logger.info(">>> Start Procesing: setareDataRetur /" + idInchiriere + "/" + dataRetur);

        Date dataReturString = new SimpleDateFormat("dd-MM-yyyy").parse(dataRetur);
        Inchiriere inchiriere = entityRepository.getById(idInchiriere);
        if (inchiriere.getAutovehicul() != null) {
            inchiriere.modificareDataReturnare(dataReturString);
            Autovehicul autovehicul = inchiriere.getAutovehicul();
            Date start = inchiriere.getDataInchiriere();
            Date end = inchiriere.getDataReturnare();
            long millis = System.currentTimeMillis();
            Date today = new Date(millis);
            inchiriere.setAutovehicul(autovehicul);
            if (today.compareTo(end) > 0) {
                inchiriere.setStatus("0");
                inchiriere.getAutovehicul().setStatus("0");
            } else if (today.compareTo(start) >= 0 && today.compareTo(end) <= 0) {
                inchiriere.setStatus("1");
                inchiriere.getAutovehicul().setStatus("1");
            }
        } else {
            inchiriere.setDataReturnare(dataReturString);
            Date start = inchiriere.getDataInchiriere();
            Date end = inchiriere.getDataReturnare();
            long millis = System.currentTimeMillis();
            Date today = new Date(millis);
            if (today.compareTo(end) > 0) {
                inchiriere.setStatus("0");
            } else if (today.compareTo(start) >= 0 && today.compareTo(end) <= 0) {
                inchiriere.setStatus("1");
            }
        }
        entityRepository.save(inchiriere);

        logger.info(">>> End Procesing: setareDataRetur /" + idInchiriere + "/" + dataRetur);
        return idInchiriere;
    }

    @RequestMapping(path = "/setareDataRetur", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Integer setareDataReturRequestHandler(
            @RequestParam("idInchiriere") Integer idInchiriere,
            @RequestParam("dataRetur") String dataRetur) throws Exception {
        return setareDataRetur(idInchiriere, dataRetur);
    }

    @RequestMapping(
            path = "/getListaAutovehicul/{idInchiriere}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    // (3) Create new project with default template: projectName, startDate
    public String getListaAutovehicul(
            @PathVariable("idInchiriere") Integer idInchiriere) throws Exception {
        logger.info(">>> Start Procesing: getListaAutovehicul /" + idInchiriere);
        Inchiriere inchiriere = entityRepository.getById(idInchiriere);
        Date dataStart = inchiriere.getDataInchiriere();
        Date dataEnd = inchiriere.getDataReturnare();
        List<Inchiriere> rentsList = entityRepository.findAll();
        List<Inchiriere> rez = new ArrayList<>();
        String result = "";
        for (var el : rentsList) {
            if (el.getIdInchiriere() != idInchiriere) {
                if ((dataStart.compareTo(el.getDataInchiriere()) >= 0 && dataStart.compareTo(el.getDataReturnare()) <= 0) || (dataEnd.compareTo(el.getDataReturnare()) <= 0 && dataEnd.compareTo(el.getDataInchiriere()) >= 0)) {
                    if (el.getAutovehicul() != null) {
                        result += el.getAutovehicul().getIdAutovehicul() + "/";
                    }
                }
            }
        }
        StringBuffer sb = new StringBuffer(result);
        if (result.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    @RequestMapping(path = "/getListaAutovehicul", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String getListaAutovehiculRequestHandler(
            @RequestParam("idInchiriere") Integer idInchiriere) throws Exception {
        return getListaAutovehicul(idInchiriere);
    }

    @RequestMapping(
            path = "/returnareAutovehicul/{idInchiriere}/{dataRetur}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    // (1) Create new rent with default template: startDate
    public Integer returnareAutovehicul(
            @PathVariable("idInchiriere") Integer idInchiriere,
            @PathVariable("dataRetur") String dataRetur) throws Exception {
        logger.info(">>> Start Procesing: returnareAutovehicul /" + idInchiriere + "/" + dataRetur);
        Date dataReturString = new SimpleDateFormat("dd-MM-yyyy").parse(dataRetur);
        Inchiriere inchiriere = entityRepository.getById(idInchiriere);
        inchiriere.setDataReturnare(dataReturString);
        inchiriere.getAutovehicul().setStatus("0");
        inchiriere.setStatus("0");
        entityRepository.save(inchiriere);

        Integer id = inchiriere.getAutovehicul().getIdAutovehicul();
        logger.info(">>> End Procesing: returnareAutovehicul/" + id);
        return id;
    }

    @RequestMapping(path = "/returnareAutovehicul", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Integer returnareAutovehiculRequestHandler(
            @RequestParam("idInchiriere") Integer idInchiriere,
            @RequestParam("dataRetur") String dataRetur) throws Exception {
        return returnareAutovehicul(idInchiriere, dataRetur);
    }

    @RequestMapping(
            path = "/getAutovehiculName/{idInchiriere}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    // (1) Create new rent with default template: startDate
    public String getAutovehiculName(
            @PathVariable("idInchiriere") Integer idInchiriere) throws Exception {
        logger.info(">>> Start Procesing: getAutovehiculName /" + idInchiriere + "/");
        Inchiriere inchiriere = entityRepository.getById(idInchiriere);

        return inchiriere.getAutovehicul().getModel();
    }

    @RequestMapping(path = "/getAutovehiculName", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String getAutovehiculNameRequestHandler(
            @RequestParam("idInchiriere") Integer idInchiriere) throws Exception {
        return getAutovehiculName(idInchiriere);
    }

    @RequestMapping(
            path = "/getClientName/{idInchiriere}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    // (1) Create new rent with default template: startDate
    public String getClientName(
            @PathVariable("idInchiriere") Integer idInchiriere) throws Exception {
        logger.info(">>> Start Procesing: getClientName /" + idInchiriere + "/");
        Inchiriere inchiriere = entityRepository.getById(idInchiriere);

        return inchiriere.getClient().getNume();
    }

    @RequestMapping(path = "/getClientName", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public String getClientNameRequestHandler(
            @RequestParam("idInchiriere") Integer idInchiriere) throws Exception {
        return getClientName(idInchiriere);
    }

    @RequestMapping(
            path = "/generareInchiriereSumar/{idInchiriere}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    // (4) Get project summary data: ProjectCurrentReleaseView
    public InchiriereView generareInchiriereSumar(
            @PathVariable("idInchiriere") Integer idInchiriere) throws Exception {
        logger.info(">>> Start Procesing: generareInchiriereSumar /" + idInchiriere);

        Inchiriere inchiriere = entityRepository.getById(idInchiriere);
        InchiriereView viewData = new InchiriereView(idInchiriere, inchiriere.getClient().getNume(), inchiriere.getAutovehicul().getModel(), inchiriere.getDataInchiriere(), inchiriere.getDataReturnare(), inchiriere.getCostInchiriere());

        logger.info(">>> End Procesing: generareInchiriereSumar /" + idInchiriere + ":" + viewData.toString());
        return viewData;
    }

    @RequestMapping(path = "/generareInchiriereSumar", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public InchiriereView generareInchiriereSumarRequestHandler(
            @RequestParam("projectId") Integer idInchiriere) throws Exception {
        return generareInchiriereSumar(idInchiriere);
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
