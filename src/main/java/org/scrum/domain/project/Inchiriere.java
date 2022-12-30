package org.scrum.domain.project;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@XmlRootElement(name = "inchiriere")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Transactional
public class Inchiriere implements Serializable {
    @Min(1)
    @NotNull(message = "IdInchiriere is required!")
    @Id
    @GeneratedValue
    @Column(name = "id_inchiriere")
    private Integer idInchiriere;
    @ManyToOne(cascade = CascadeType.ALL)
    protected Client client;
    @ManyToOne(cascade = CascadeType.ALL)
    protected Autovehicul autovehicul;

    @NotNull(message = "DataInchiriere is required!")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_inchiriere")
    private Date dataInchiriere;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_returnare")
    private Date dataReturnare;

    @NotNull(message = "ZileInchiriere is required!")
    @Min(value = 1, message = "ZileInchiriere must be equal or bigger then 1!")
    @Column(name = "zile_inchiriere")
    private Integer zileInchiriere;
    @Column(name = "cost_inchiriere")
    private Double costInchiriere = 0.0;

    public Inchiriere() {
        super();
    }

    public Inchiriere(Integer idInchiriere, Client client, Autovehicul autovehicul, Date dataInchiriere, Integer zileInchiriere) {
        this.idInchiriere = idInchiriere;
        this.client = client;
        this.autovehicul = autovehicul;
        this.dataInchiriere = dataInchiriere;
        this.zileInchiriere = zileInchiriere;
        this.dataReturnare = calculDataReturnare(dataInchiriere, zileInchiriere);
        this.costInchiriere = calculCostInchiriere(autovehicul, zileInchiriere);
    }

    public Inchiriere(Integer idInchiriere, Client client, Autovehicul autovehicul, Date dataInchiriere) {
        this.idInchiriere = idInchiriere;
        this.client = client;
        this.dataInchiriere = dataInchiriere;
        this.autovehicul = autovehicul;
    }

    private Date calculDataReturnare(Date data, Integer zile) {
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        c.add(Calendar.DATE, zile);
        return c.getTime();
    }

    private Double calculCostInchiriere(Autovehicul a, Integer zile) {
        return a.getCostInchiriere() * zile;
    }

    public Integer getIdInchiriere() {
        return idInchiriere;
    }

    public void setIdInchiriere(Integer idInchiriere) {
        this.idInchiriere = idInchiriere;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Autovehicul getAutovehicul() {
        return autovehicul;
    }

    public void setAutovehicul(Autovehicul autovehicul) {
        this.autovehicul = autovehicul;
    }

    public Date getDataInchiriere() {
        return dataInchiriere;
    }

    public void setDataInchiriere(Date dataInchiriere) {
        this.dataInchiriere = dataInchiriere;
    }

    public Integer getZileInchiriere() {
        return zileInchiriere;
    }

    public void setZileInchiriere(Integer zileInchiriere) {
        this.zileInchiriere = zileInchiriere;
    }

    public Date getDataReturnare() {
        return dataReturnare;
    }

    public Double getCostInchiriere() {
        return costInchiriere;
    }

    @Override
    public String toString() {
        return "\n\t\tInchiriere [idInchiriere=" + idInchiriere + ", client=" + client.getNume() + ", autovehicul=" + autovehicul.getModel() + ", dataInchiriere=" + dataInchiriere + ", dataReturnare=" + dataReturnare + ", costInchiriere=" + costInchiriere + "]";

    }
}
