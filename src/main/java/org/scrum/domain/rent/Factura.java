package org.scrum.domain.rent;

import java.io.Serializable;
import java.util.Date;

public class Factura implements Serializable {
    protected Integer idFactura;
    protected Inchiriere inchiriere;
    private Integer nrFactura;
    private Date dataFactura;
    private Double valoareFactura;
    private Double valoareTVA;
    private String status = "Emisa";

    public Factura() {
        super();
    }

    public Factura(Integer idFactura, Inchiriere inchiriere, Integer nrFactura, Date dataFactura, Double valoareFactura) {
        this.idFactura = idFactura;
        this.inchiriere = inchiriere;
        this.nrFactura = nrFactura;
        this.dataFactura = dataFactura;
        this.valoareFactura = valoareFactura;
        this.valoareTVA = calculValoareTVA(valoareFactura);
    }

    private Double calculValoareTVA(Double suma) {
        return suma * 19 / 119;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Inchiriere getInchiriere() {
        return inchiriere;
    }

    public void setInchiriere(Inchiriere inchiriere) {
        this.inchiriere = inchiriere;
    }

    public Integer getNrFactura() {
        return nrFactura;
    }

    public void setNrFactura(Integer nrFactura) {
        this.nrFactura = nrFactura;
    }

    public Date getDataFactura() {
        return dataFactura;
    }

    public void setDataFactura(Date dataFactura) {
        this.dataFactura = dataFactura;
    }

    public Double getValoareFactura() {
        return valoareFactura;
    }

    public void setValoareFactura(Double valoareFactura) {
        this.valoareFactura = valoareFactura;
        this.valoareTVA = calculValoareTVA(valoareFactura);
    }

    public Double getValoareTVA() {
        return valoareTVA;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\n\t\tFactura [" +
                "idFactura=" + idFactura +
                ", idInchiriere=" + inchiriere.getIdInchiriere() +
                ", nrFactura=" + nrFactura +
                ", dataFactura=" + dataFactura +
                ", valoareFactura=" + valoareFactura +
                ", status='" + status +
                ']';
    }
}
