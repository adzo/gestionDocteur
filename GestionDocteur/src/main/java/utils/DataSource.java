/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author selmi
 */
public class DataSource {

    private Connection connection; //pont pour etablir la cnx
    private String url;
    private String login;
    private String mdp;
    private Properties properties; //
    private static DataSource instance = null;

    //Rendre le constructeur privé   (Singleton)
    private DataSource() {
        try {
//            properties = new Properties();
//            properties.load(new FileInputStream(new File("configuration.properties")));
            url = "jdbc:mysql://localhost:3306/gestiondocteur";
            login = "root";
            mdp = "root";

            connection = (Connection) DriverManager.
                    getConnection(url, login, mdp);

//        } catch (IOException ex) {
//            System.err.println("Problème de connection!!");
//            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.err.println("Problème SQL!!");
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connexion établie !!!");
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }
}
