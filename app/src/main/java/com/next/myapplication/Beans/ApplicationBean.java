package com.next.myapplication.Beans;

import com.next.myapplication.Beans.Eleve;
import com.next.myapplication.Beans.Prof;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lenovo on 27/11/2017.
 */

public class ApplicationBean extends RealmObject {

    @PrimaryKey
    private int id;
    private RealmList<Prof> profs;
    private RealmList<Eleve> eleves;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RealmList<Prof> getProfs() {
        return profs;
    }

    public void setProfs(RealmList<Prof> profs) {
        this.profs = profs;
    }

    public RealmList<Eleve> getEleves() {
        return eleves;
    }

    public void setEleves(RealmList<Eleve> eleves) {
        this.eleves = eleves;
    }

    public ApplicationBean() {
    }

    public ApplicationBean(int id, RealmList<Prof> profs, RealmList<Eleve> eleves) {
        this.id = id;
        this.profs = profs;
        this.eleves = eleves;
    }
}
