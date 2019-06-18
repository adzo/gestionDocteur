/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiMainFrame;

import entities.User;
import guiLogin.LoginWindow;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selmi
 */
public class MainFrameController implements Initializable {

    
    @FXML
    private Label connectedUser;
    @FXML
    private Button logoutBtn;
    @FXML
    private  Pane centerPane;

    
    
    public Pane getCenterPane() {
        return centerPane;
    }

    public void setCenterPane(Pane centerPane) {
        centerPane = centerPane;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectedUser.setText(User.getConnectedUser().getType() + " | " + User.getConnectedUser().getNom());
        if (User.getConnectedUser().getType().equals("admin")) {
            try {
                //admin loading screen docteur test
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/docteur/docteurList.fxml"));
                centerPane.getChildren().clear();
                centerPane.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (User.getConnectedUser().getType().equals("Docteur")){
            //loading docteur window
            try {
                //admin loading screen docteur test
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/Patient/PatientList.fxml"));
                centerPane.getChildren().clear();
                centerPane.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (User.getConnectedUser().getType().equals("Patient")){
            try {
                //loading Patient window
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/fiche/FicheList.fxml"));
                centerPane.getChildren().clear();
                centerPane.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(MainFrameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void logoutAction(ActionEvent event) throws Exception {
        User.setConnectedUser(null);
        User.setManagedUser(null);
        Stage loginStage = new Stage();
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.start(loginStage);
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
