package com.betrisey.suzanne.androidproject;

import java.util.Date;

/**
 * Created by Chacha on 27.10.2015.
 */
public class CDonneur {

    private String nom;
    private String prenom;
    private String sexe;
    private String naissance;
    private String adresse;
    private String NPA;
    private String lieu;
    private String region;
    private String telephone;
    private String groupe;
    private Integer donsPossibles;
    private Boolean disponibilite;

    public CDonneur(String nom, String prenom, String sexe, String naissance, String adresse, String NPA, String lieu, String region, String telephone, String groupe, Integer donsPossibles, Boolean disponibilite){
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.naissance = naissance;
        this.adresse = adresse;
        this.NPA = NPA;
        this.lieu = lieu;
        this.region = region;
        this.telephone = telephone;
        this.groupe = groupe;
        this.donsPossibles = donsPossibles;
        this.disponibilite = disponibilite;
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

    public String getNaissance() {
        return naissance;
    }

    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNPA() {
        return NPA;
    }

    public void setNPA(String NPA) {
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

    public Boolean getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Boolean disponibilite) {
        this.disponibilite = disponibilite;
    }
}
