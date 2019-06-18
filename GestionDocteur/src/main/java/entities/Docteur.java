/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author selmi
 */
public class Docteur {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String tel;
    private String email;
    private String password;

    public Docteur() {
    }

    public Docteur(int id, String nom, String prenom, String adresse, String tel, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.password = password;
    }

    public Docteur(String nom, String prenom, String adresse, String tel, String email, String password) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.email = email;
        this.password = password;
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
        return "Docteur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", tel=" + tel + ", email=" + email + ", password=" + password + '}';
    }
    
    
    
}
