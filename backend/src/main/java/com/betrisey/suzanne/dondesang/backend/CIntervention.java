package com.betrisey.suzanne.dondesang.backend;

import com.googlecode.objectify.annotation.Id;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Suzanne on 17.12.2015.
 */
public class CIntervention {

    @com.googlecode.objectify.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

        private int id;
        private Date date;
        private String description;
        private int quantite;
        private String groupe;
        private String region;
        boolean selected;

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public void setId(int id) {this.id = id; }

        public void setDate(Date date) {
            this.date = date;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setQuantite(int quantite) {
            this.quantite = quantite;
        }

        public void setGroupe(String groupe) {
            this.groupe = groupe;
        }

        public Date getDate() {
            return date;
        }

        public String getDescription() {
            return description;
        }

        public int getQuantite() {
            return quantite;
        }

        public String getRegion() { return region; }

        public void setRegion(String region) { this.region = region; }

        public String getGroupe() {
            return groupe;
        }

        public int getId() {
            return id;
        }



}
