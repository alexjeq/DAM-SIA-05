package org.scrum.domain.rent;

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

@XmlRootElement(name="inchiriere")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class InchiriereView {
    private Integer idInchiriere;
    protected String numeClient;
    protected String modelAutovehicul;
    private Date dataInchiriere;
    private Date dataReturnare;
    private Double costInchiriere;

    public InchiriereView(Integer idInchiriere, String numeClient, String modelAutovehicul, Date dataInchiriere, Date dataReturnare, Double costInchiriere) {
        this.idInchiriere = idInchiriere;
        this.numeClient = numeClient;
        this.modelAutovehicul = modelAutovehicul;
        this.dataInchiriere = dataInchiriere;
        this.dataReturnare = dataReturnare;
        this.costInchiriere = costInchiriere;
    }

    public Integer getIdInchiriere() {
        return idInchiriere;
    }

    public void setIdInchiriere(Integer idInchiriere) {
        this.idInchiriere = idInchiriere;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getModelAutovehicul() {
        return modelAutovehicul;
    }

    public void setModelAutovehicul(String modelAutovehicul) {
        this.modelAutovehicul = modelAutovehicul;
    }

    public Date getDataInchiriere() {
        return dataInchiriere;
    }

    public void setDataInchiriere(Date dataInchiriere) {
        this.dataInchiriere = dataInchiriere;
    }

    public Date getDataReturnare() {
        return dataReturnare;
    }

    public void setDataReturnare(Date dataReturnare) {
        this.dataReturnare = dataReturnare;
    }

    public Double getCostInchiriere() {
        return costInchiriere;
    }

    public void setCostInchiriere(Double costInchiriere) {
        this.costInchiriere = costInchiriere;
    }

    @Override
    public String toString() {
        return "\n\t\tInchiriereView [idInchiriere=" + idInchiriere + ", client=" + numeClient + ", autovehicul=" + modelAutovehicul + ", dataInchiriere=" + dataInchiriere + ", dataReturnare=" + dataReturnare + ", costInchiriere=" + costInchiriere + "]";

    }
}
