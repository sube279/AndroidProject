package com.betrisey.suzanne.dondesang.backend;

import com.googlecode.objectify.annotation.Id;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Suzanne on 17.12.2015.
 */
public class CSang {

    @com.googlecode.objectify.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private int id;
    private int donneur;
    private Date dateDon;
    private Date peremption;
    private String region;
    private String groupe;
    private String statut;
    private int intervention;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDonneur() {
        return donneur;
    }

    public void setDonneur(int donneur) {
        this.donneur = donneur;
    }

    public Date getDateDon() {
        return dateDon;
    }

    public void setDateDon(Date dateDon) {
        this.dateDon = dateDon;
    }

    public Date getPeremption() {
        return peremption;
    }

    public void setPeremption(Date peremption) {
        this.peremption = peremption;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getIntervention() {
        return intervention;
    }

    public void setIntervention(int intervention) {
        this.intervention = intervention;
    }

}
