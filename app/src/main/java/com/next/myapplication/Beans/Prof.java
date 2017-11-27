package com.next.myapplication.Beans;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lenovo on 27/11/2017.
 */

public class Prof extends RealmObject {

    @PrimaryKey
    private  int id_prof;
    private  String nom;
    private String adresse;
    private  String pdf;
    private  String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId_prof() {
        return id_prof;
    }

    public void setId_prof(int id_prof) {
        this.id_prof = id_prof;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public Prof(int id_prof, String nom, String adresse, String pdf) {
        this.id_prof = id_prof;
        this.nom = nom;
        this.adresse = adresse;
        this.pdf = pdf;
    }

    public Prof() {
    }
}
