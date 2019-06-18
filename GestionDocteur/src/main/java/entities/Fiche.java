/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author selmi
 */
public class Fiche {
    private int id;
    private int taille;
    private float poids;
    private float imc;
    private String type;
    private String observation;
    private int idPatient;
    private Date dateFiche;

    public Fiche(int height, float weight, int id) {
        this.taille = height;
        this.poids = weight;
        idPatient = id;
    }

    public Date getDateFiche() {
        return dateFiche;
    }

    public void setDateFiche(Date dateFiche) {
        this.dateFiche = dateFiche;
    }

    public Fiche(int id, int taille, float poids, float imc, String type, String observation, int idPatient, Date dateFiche) {
        this.id = id;
        this.taille = taille;
        this.poids = poids;
        this.imc = imc;
        this.type = type;
        this.observation = observation;
        this.idPatient = idPatient;
        this.dateFiche = dateFiche;
    }
    
    

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public Fiche() {
    }

    public Fiche(int taille, float poids) {
        this.taille = taille;
        this.poids = poids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public float getImc() {
        
        return imc;
    }

    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public String toString() {
        return "Fiche{" + "id=" + id + ", taille=" + taille + ", poids=" + poids + ", imc=" + imc + ", type=" + type + ", observation=" + observation + ", idPatient=" + idPatient + ", dateFiche=" + dateFiche + '}';
    }
    
    
    
    
}
