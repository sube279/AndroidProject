package db.object;

/**
 * Created by Suzanne on 30.10.2015.
 */
public class CSang {

    private String id;
    private String donneur;
    private String dateDon;
    private String peremption;
    private String region;
    private String groupe;
    private String statut;
    private String dateIntervention;

    public CSang(String id, String donneur, String dateDon, String peremption, String region, String groupe, String statut, String dateIntervention){
        this.id = id;
        this.donneur = donneur;
        this.dateDon = dateDon;
        this.peremption = peremption;
        this.region = region;
        this.groupe = groupe;
        this.statut = statut;
        this.dateIntervention = dateIntervention;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDonneur() {
        return donneur;
    }

    public void setDonneur(String id) {
        this.donneur = donneur;
    }

    public String getDateDon() {
        return dateDon;
    }

    public void setDateDon(String dateDon) {
        this.dateDon = dateDon;
    }

    public String getPeremption() {
        return peremption;
    }

    public void setPeremption(String peremption) {
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

    public String getDateIntervention() {
        return dateIntervention;
    }

    public void setDateIntervention(String dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

}
