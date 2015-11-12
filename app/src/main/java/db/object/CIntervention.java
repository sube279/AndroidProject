package db.object;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Suzanne on 25.10.2015.
 */
public class CIntervention extends Activity {

    private int id;
    private String date;
    private String description;
    private int quantite;
    private String groupe;
    boolean selected;



    public CIntervention() {

    }

    public CIntervention(String date, String description, int quantite, String groupe, boolean selected) {
        this.date = date;
        this.description = description;
        this.quantite = quantite;
        this.groupe = groupe;
        this.selected = selected;

    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setId(int id) {this.id = id; }

    public void setDate(String date) {
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

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getGroupe() {
        return groupe;
    }



}

