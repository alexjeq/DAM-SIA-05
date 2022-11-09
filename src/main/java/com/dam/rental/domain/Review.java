package com.dam.rental.domain;

import java.io.Serializable;
import java.util.Date;

public class Review implements Comparable<Review>, Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer ReviewId;
	
	private Date DataPostare;
	
	private String Descriere;
	
	private Integer ScorReview;
	
	private Client client;
	
	private Autovehicul auto;
	
	public Review(Integer reviewId, Date dataPostare, String descriere, Client client, Autovehicul auto) {
		super();
		ReviewId = reviewId;
		DataPostare = dataPostare;
		Descriere = descriere;
		this.client = client;
		this.auto = auto;
	}

	public Review(Integer reviewId, Date dataPostare, String descriere, Integer scorReview, Client client,
			Autovehicul auto) {
		super();
		ReviewId = reviewId;
		DataPostare = dataPostare;
		Descriere = descriere;
		ScorReview = scorReview;
		this.client = client;
		this.auto = auto;
	}

	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Review [ReviewId=" + ReviewId + ", DataPostare=" + DataPostare + ", client=" + client + ", auto=" + auto
				+ ", Descriere=" + Descriere + ", ScorReview=" + ScorReview + "]";
	}

	public Integer getReviewId() {
		return ReviewId;
	}
	
	public void setReviewId(Integer reviewId) {
		ReviewId = reviewId;
	}
	
	public Date getDataPostare() {
		return DataPostare;
	}
	
	public void setDataPostare(Date dataPostare) {
		DataPostare = dataPostare;
	}

	public String getDescriere() {
		return Descriere;
	}

	public void setDescriere(String descriere) {
		Descriere = descriere;
	}

	public Integer getScorReview() {
		return ScorReview;
	}

	public void setScorReview(Integer scorReview) {
		ScorReview = scorReview;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public Autovehicul getAuto() {
		return auto;
	}

	public void setAuto(Autovehicul auto) {
		this.auto = auto;
	}

	@Override
	public int compareTo(Review r) {
		if (this.equals(r))
			return 0;
		return this.getDataPostare().compareTo(r.getDataPostare());
	}

}
