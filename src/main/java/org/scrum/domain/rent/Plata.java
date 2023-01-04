package org.scrum.domain.rent;

import java.io.Serializable;
import java.util.Date;

public class Plata implements Serializable {
    protected Integer idPlata;
    protected Inchiriere inchiriere;
    protected Factura factura;
    private Date dataPlata;
    private Double valoarePlata;
    private Double valoarePlataTVA;

    public Plata() {
        super();
    }

    public Plata(Integer idPlata, Inchiriere inchiriere, Factura factura, Date dataPlata, Double valoarePlata, Double valoarePlataTVA) {
        this.idPlata = idPlata;
        this.inchiriere = inchiriere;
        this.factura = factura;
        this.dataPlata = dataPlata;
        this.valoarePlata = factura.getValoareFactura();
        this.valoarePlataTVA = factura.getValoareTVA();
    }

    public Integer getIdPlata() {
        return idPlata;
    }

    public Inchiriere getInchiriere() {
        return inchiriere;
    }

    public Factura getFactura() {
        return factura;
    }

    public Date getDataPlata() {
        return dataPlata;
    }

    public Double getValoarePlata() {
        return valoarePlata;
    }

    public Double getValoarePlataTVA() {
        return valoarePlataTVA;
    }

    public void setDataPlata(Date dataPlata) {
        this.dataPlata = dataPlata;
    }

    @Override
    public String toString() {
        return "Plata [" +
                "idPlata=" + idPlata +
                ", idInchiriere=" + inchiriere.getIdInchiriere() +
                ", idFactura=" + factura.idFactura +
                ", dataPlata=" + dataPlata +
                ", valoarePlata=" + valoarePlata +
                ']';
    }
}
