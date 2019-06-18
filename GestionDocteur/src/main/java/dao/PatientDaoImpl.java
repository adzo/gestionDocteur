/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Connection;
import entities.Docteur;
import entities.Patient;
import entities.User;
import iDao.IPatientDao;
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
public class PatientDaoImpl implements IPatientDao {

    private Patient connectedPatient;
    boolean result = false;
    PreparedStatement pst, pst1; //l'entité qui gère la requête
    Connection connection;

    public PatientDaoImpl() {
        DataSource ds = DataSource.getInstance();
        connection = ds.getConnection();
    }

    @Override
    public boolean addPatient(Patient patient) {
        try {
            String req = "INSERT INTO `patients` ( `nom`, `prenom`,`password`,`email`,`tel`,`adresse`,`dateDeNaissance`,`idDocteur`) VALUES (?,?,?,?,?,?,?,?)";
            pst = connection.prepareStatement(req);
            pst.setString(1, patient.getNom());
            pst.setString(2, patient.getPrenom());
            pst.setString(3, patient.getPassword());
            pst.setString(4, patient.getEmail());
            pst.setString(5, patient.getTel());
            pst.setString(6, patient.getAdresse());
            pst.setObject(7, patient.getDateDeNaissance());
            pst.setInt(8, User.getConnectedUser().getId());

            pst.executeUpdate();//Exécution de la requête
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(PatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean deletePatient(Patient patient) {
        try {
            //toDo
            String req = "DELETE FROM `patients` WHERE `id` = ? ";
            pst = connection.prepareStatement(req);
            pst.setInt(1, patient.getId());
            pst.executeUpdate();
            result = true;
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(DocteurDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean updatePatient(Patient patient) {
        try {
            String req = "UPDATE `gestiondocteur`.`patients` SET `nom`=? , `prenom`= ? , `adresse`= ? , `tel`= ? , `email`= ? , `password`= ? ,`dateDeNaissance`= ?  WHERE `id`=? ";
            pst = connection.prepareStatement(req);
            pst.setString(1, patient.getNom());
            pst.setString(2, patient.getPrenom());
            pst.setString(3, patient.getAdresse());
            pst.setString(4, patient.getTel());
            pst.setString(5, patient.getEmail());
            pst.setString(6, patient.getPassword());
            pst.setObject(7, patient.getDateDeNaissance());
            pst.setObject(8, patient.getId());

            pst.executeUpdate();//Exécution de la requête
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(DocteurDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<Patient> getAllByDocteur() {
        try {
            String req = "SELECT * FROM `patients` WHERE `idDocteur` = " + User.getConnectedUser().getId();
            pst = connection.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            Patient u;
            List<Patient> resultList = new ArrayList<>();
            while (rs.next()) {
                u = new Patient(rs.getInt(1),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("dateDeNaissance"),
                        rs.getString("adresse"),
                        rs.getString("tel"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("idDocteur"));
                resultList.add(u);
            }
            return resultList;
        } catch (SQLException ex) {
            Logger.getLogger(PatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Patient getPatientById(int id) {
        try {
            String req = "SELECT * FROM `patients` WHERE `id` =" + id;
            pst = connection.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            Patient u;
            if (rs.next()) {
                u = new Patient(rs.getInt(1),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("dateDeNaissance"),
                        rs.getString("adresse"),
                        rs.getString("tel"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("idDocteur"));
                System.out.println(u);
                connectedPatient = u;
                return u;
            } else {
                System.out.println("no result found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Patient getPatientByLogin(String email, String pass) {
        try {
            String req = "SELECT * FROM `patients` WHERE `email` like '" + email + "' and `password` like '" + pass + "'";
            pst = connection.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            Patient u;
            if (rs.next()) {
                u = new Patient(rs.getInt(1),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("dateDeNaissance"),
                        rs.getString("adresse"),
                        rs.getString("tel"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getInt("idDocteur"));

                System.out.println(u);
                connectedPatient = u;
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Patient getConnectedPatient() {
        return connectedPatient;
    }

    public void setConnectedPatient(Patient connectedPatient) {
        this.connectedPatient = connectedPatient;
    }

}
