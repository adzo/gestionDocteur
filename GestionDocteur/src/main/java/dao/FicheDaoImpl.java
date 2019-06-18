/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Connection;
import entities.Docteur;
import entities.Fiche;
import entities.Patient;
import entities.User;
import iDao.IFicheDao;
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
public class FicheDaoImpl implements IFicheDao {
    
    
    boolean result = false;
    PreparedStatement pst, pst1; //l'entité qui gère la requête
    Connection connection;
    
    public FicheDaoImpl() {
        DataSource ds = DataSource.getInstance();
        connection = ds.getConnection();
    }
    @Override
    public boolean addFiche(Fiche fiche) {
        try {
            String req = "INSERT INTO `fiches` (`taille`,`poid`,`idPatient`) VALUES (?,?,?)";
            pst = connection.prepareStatement(req);
            pst.setInt(1, fiche.getTaille());
            pst.setFloat(2, fiche.getPoids());
            pst.setInt(3, fiche.getIdPatient());
            pst.executeUpdate();//Exécution de la requête
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(PatientDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public List<Fiche> getFiches(User user) {
        List<Fiche> result = new ArrayList<>();
        try {
            String req = "SELECT * FROM `fiches` WHERE `idPatient` =  "+user.getId();
            pst = connection.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            Fiche u;
            while (rs.next()) {
                u = new Fiche(rs.getInt("id"),
                        rs.getInt("taille"),
                        rs.getFloat("poid"),
                        rs.getFloat("imc"),
                        rs.getString("type"),
                        rs.getString("observation"),
                        rs.getInt("idPatient"),
                        rs.getDate("dateFiche"));
                result.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FicheDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean updateFiche(Fiche fiche) {
        try {
            String req = "UPDATE `fiches` SET `observation`='"+fiche.getObservation()+"' WHERE `id`='"+fiche.getId()+"';";
            
            pst = connection.prepareStatement(req);
            pst.executeUpdate();//Exécution de la requête
            result = true;
            
        } catch (SQLException ex) {
            Logger.getLogger(FicheDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        return result;
    }

    @Override
    public boolean deleteFiche(Fiche fiche) {
        
        return result;
    }
    
}
