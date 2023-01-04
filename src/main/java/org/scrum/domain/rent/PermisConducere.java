package org.scrum.domain.rent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PermisConducere implements Serializable {

    protected Integer idPermis;
    protected Client client;
    private Date dataEmitere;
    private Date dataExpirare;
    private List<CategoriePermis> categorii = new ArrayList<>();

    public PermisConducere() {
        super();
    }

    public PermisConducere(Integer idPermis, Client client, Date dataEmitere, Date dataExpirare) {
        super();
        this.idPermis = idPermis;
        this.client = client;
        this.dataEmitere = dataEmitere;
        this.dataExpirare = dataExpirare;
    }

    public PermisConducere(Integer idPermis, Client client, Date dataEmitere) {
        super();
        this.idPermis = idPermis;
        this.client = client;
        this.dataEmitere = dataEmitere;
    }

    public Integer getIdPermis() {
        return idPermis;
    }

    public Client getClient() {
        return client;
    }

    public Date getDataEmitere() {
        return dataEmitere;
    }

    public void setDataEmitere(Date dataEmitere) {
        this.dataEmitere = dataEmitere;
    }

    public Date getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(Date dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    public List<CategoriePermis> getCategorii() {
        return categorii;
    }

    public void setCategorii(List<CategoriePermis> categorii) {
        this.categorii = categorii;
    }

    @Override
    public String toString() {
        return "\n\t\tPermis [idPermis=" + idPermis + ", numeClient=" + client.getNume()
                + ", dataEmitere=" + dataEmitere + ", dataExpirare=" + dataExpirare + ", categorii={" + categorii.stream().map(Object::toString)
                .collect(Collectors.joining(", ")) + "}" + "]";
    }
}
