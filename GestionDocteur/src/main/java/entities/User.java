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
public class User {
    
    private int id;
    private String type;
    private String nom;
    private String prenom;
    private static User connectedUser;
    private static User managedUser;

    public static User getManagedUser() {
        return managedUser;
    }

    public static void setManagedUser(User managedUser) {
        User.managedUser = managedUser;
    }

    
    
    public static User getConnectedUser() {
        return connectedUser;
    }

    public static void setConnectedUser(User connectedUser) {
        User.connectedUser = connectedUser;
    }
    
    public User(int id, String nom, String prenom, String type) {
        this.id = id;
        this.type = type;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return type+": " + nom + ", " + prenom;
    }
    
    
    
}
