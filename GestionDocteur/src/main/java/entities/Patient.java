/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import java.util.List;

/**
 *
 * @author selmi
 */

public class Patient {
    private int id;
    private String nom;
    private String prenom;
    private Date dateDeNaissance;
    private String adresse;
    private String tel;
    private String email;
    private String password;
    private int idDocteur;
    private List<Fiche> fiches;

    public Patient() {
    }

    public Patient(String nom, String prenom, Date dateDeNaissance, String adresse, String tel, String email, String password ) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.password = password;
       
    }
    
    public Patient(int id, String nom, String prenom, Date dateDeNaissance, String adresse, String tel, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.password = password;
         
    }

    
    public Patient(int id, String nom, String prenom, Date dateDeNaissance, String adresse, String tel, String email, String password,int idDocteur) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.password = password;
         this.idDocteur = idDocteur;
    }

    public List<Fiche> getFiches() {
        return fiches;
    }

    public void setFiches(List<Fiche> fiches) {
        this.fiches = fiches;
    }

    
    
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

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateDeNaissance=" + dateDeNaissance + ", adresse=" + adresse + ", tel=" + tel + ", email=" + email + ", password=" + password + ", idDocteur=" + idDocteur + '}';
    }

    
    
    
}
