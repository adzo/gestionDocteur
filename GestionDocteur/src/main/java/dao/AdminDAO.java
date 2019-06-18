/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.Connection;
import entities.Docteur;
import entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author selmi
 */
public class AdminDAO {
    
    PreparedStatement pst, pst1; //l'entité qui gère la requête
    Connection connection;

    public AdminDAO() {
        DataSource ds = DataSource.getInstance();
        connection = ds.getConnection();
    }
    
    
    public User getAdminbyLogin(String email, String pass) {
        try {
            String req = "SELECT * FROM `admins` WHERE `login` like '" + email + "' and `password` like '" + pass + "'";
            pst = connection.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            User u;
            if (rs.next()) {
                u = new User(
                        rs.getInt("idAdmins"),
                        rs.getString("login"),
                        "admin",
                        rs.getString("type"));
                return u;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DocteurDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
