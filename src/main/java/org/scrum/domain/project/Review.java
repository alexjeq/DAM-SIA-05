package org.scrum.domain.project;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable, Comparable<Review> {
    protected Integer idReview;
    protected Client client;
    protected Autovehicul autovehicul;
    private String descriere;
    private Date dataPostare;
    private Integer scorReview;

    public Review() {
        super();
    }

    public Review(Integer idReview, Client client, Autovehicul autovehicul, String descriere, Date dataPostare, Integer scorReview) {
        this.idReview = idReview;
        this.client = client;
        this.autovehicul = autovehicul;
        this.descriere = descriere;
        this.dataPostare = dataPostare;
        this.scorReview = scorReview;
    }

    public Integer getIdReview() {
        return idReview;
    }

    public Client getClient() {
        return client;
    }

    public Autovehicul getAutovehicul() {
        return autovehicul;
    }

    public String getDescriere() {
        return descriere;
    }

    public Date getDataPostare() {
        return dataPostare;
    }

    public Integer getScorReview() {
        return scorReview;
    }

    public void setIdReview(Integer idReview) {
        this.idReview = idReview;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public void setDataPostare(Date dataPostare) {
        this.dataPostare = dataPostare;
    }

    public void setScorReview(Integer scorReview) {
        this.scorReview = scorReview;
    }

    @Override
    public int compareTo(Review o) {
        if (this.equals(o))
            return 0;
        return this.getDataPostare().compareTo(o.getDataPostare());
    }

    @Override
    public String toString() {
        return "\n\t\tReview [idReview=" + idReview + ", client=" + client.getNume()
                + ", autovehicul=" + autovehicul.getModel() + ", descriere=" + descriere + ", dataPostare=" + dataPostare + ", continut=" + descriere + "]";
    }
}
