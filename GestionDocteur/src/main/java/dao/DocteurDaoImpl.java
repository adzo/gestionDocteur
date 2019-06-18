/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Connection;
import entities.Docteur;
import iDao.IDocteurDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author selmi
 */
public class DocteurDaoImpl implements IDocteurDao {

    private static Docteur connectedDoc;

    PreparedStatement pst, pst1; //l'entité qui gère la requête
    Connection connection;

    public DocteurDaoImpl() {
        DataSource ds = DataSource.getInstance();
        connection = ds.getConnection();
    }

    boolean result = false;

    @Override
    public boolean addDocteur(Docteur docteur) {
        try {
            String req = "INSERT INTO `docteurs` ( `nom`, `prenom`,`password`,`email`,`tel`,`adresse`) VALUES (?,?,?,?,?,?)";
            pst = connection.prepareStatement(req);
            pst.setString(1, docteur.getNom());
            pst.setString(2, docteur.getPrenom());
            pst.setString(3, docteur.getPassword());
            pst.setString(4, docteur.getEmail());
            pst.setString(5, docteur.getTel());
            pst.setString(6, docteur.getAdresse());

            pst.executeUpdate();//Exécution de la requête
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(DocteurDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean deleteDocteur(Docteur docteur) {
        try {
            //toDo
            String req = "DELETE FROM `docteurs` WHERE `id` = ? ";
            pst = connection.prepareStatement(req);
            pst.setInt(1, docteur.getId());
            pst.executeUpdate();
            result = true;
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(DocteurDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean updateDocteur(Docteur docteur) {
        try {
            String req = "UPDATE `gestiondocteur`.`docteurs` SET `nom`=? , `prenom`= ? , `adresse`= ? , `tel`= ? , `email`= ? , `password`= ?  WHERE `id`=? ";
            pst = connection.prepareStatement(req);
            pst.setString(1, docteur.getNom());
            pst.setString(2, docteur.getPrenom());
            pst.setString(3, docteur.getPassword());
            pst.setString(4, docteur.getEmail());
            pst.setString(5, docteur.getTel());
            pst.setString(6, docteur.getAdresse());
            pst.setInt(7, docteur.getId());

            pst.executeUpdate();//Exécution de la requête
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(DocteurDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<Docteur> getAll() {
        List<Docteur> result = new ArrayList<>();
        try {
            String req = "SELECT * FROM `docteurs`";
            pst = connection.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            Docteur u;
            while (rs.next()) {
                u = new Docteur(rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("tel"),
                        rs.getString("email"),
                        rs.getString("password"));
                result.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocteurDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public Docteur getDocteurById(int id) {
        try {
            String req = "SELECT * FROM `docteurs` WHERE `id` = " + id;
            pst = connection.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            Docteur u;
            if (rs.next()) {
                u = new Docteur(rs.getInt(1),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("tel"),
                        rs.getString("email"),
                        rs.getString("password"));
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocteurDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Docteur getDocteurbyLogin(String email, String pass) {
        try {
            String req = "SELECT * FROM `docteurs` WHERE `email` like '" + email + "' and `password` like '" + pass + "'";
            pst = connection.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            Docteur u;
            if (rs.next()) {
                u = new Docteur(rs.getInt(1),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse"),
                        rs.getString("tel"),
                        rs.getString("email"),
                        rs.getString("password"));
                System.out.println(u);
                connectedDoc = u;
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocteurDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Docteur getConnectedDoc() {
        return connectedDoc;
    }

    public static void setConnectedDoc(Docteur connectedDoc) {
        DocteurDaoImpl.connectedDoc = connectedDoc;
    }

}
