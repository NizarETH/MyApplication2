package com.next.myapplication.Beans;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lenovo on 27/11/2017.
 */

public class Eleve extends RealmObject {

    @PrimaryKey
    private int id_eleve;
    private  String nom;
    private  String ville;
    private  String adresse;
    private  String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId_eleve() {
        return id_eleve;
    }

    public void setId_eleve(int id_eleve) {
        this.id_eleve = id_eleve;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Eleve(int id_eleve, String nom, String ville, String adresse, String photo) {
        this.id_eleve = id_eleve;
        this.nom = nom;
        this.ville = ville;
        this.adresse = adresse;
        this.photo = photo;
    }

    public Eleve() {
    }
}
