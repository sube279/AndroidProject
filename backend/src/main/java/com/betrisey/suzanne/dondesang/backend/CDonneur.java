package com.betrisey.suzanne.dondesang.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Suzanne on 17.12.2015.
 */

@Entity
public class CDonneur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private int id;
    private String nom;
    private String prenom;
    private String sexe;
    private Date naissance;
    private String adresse;
    private int NPA;
    private String lieu;
    private String region;
    private String telephone;
    private String groupe;
    private Integer donsPossibles;
    private Date disponibilite;

    private List<CSang> sangs = new ArrayList<CSang>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNPA() {
        return NPA;
    }

    public void setNPA(int NPA) {
        this.NPA = NPA;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public Integer getDonsPossibles() {
        return donsPossibles;
    }

    public void setDonsPossibles(Integer donsPossibles) {
        this.donsPossibles = donsPossibles;
    }

    public Date getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Date disponibilite) {
        this.disponibilite = disponibilite;
    }

    public List<CSang> getSangs() {
        return sangs;
    }

    public void setPhones(List<CSang> sangs) {
        this.sangs = sangs;
    }
}
