/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiLogin;

import dao.AdminDAO;
import dao.DocteurDaoImpl;
import dao.PatientDaoImpl;
import entities.Docteur;
import entities.Patient;
import entities.User;
import guiMainFrame.MainFrame;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author selmi
 */
public class LoginWindowController implements Initializable {

    @FXML
    private Label msg;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    DocteurDaoImpl ddao = new DocteurDaoImpl();
    PatientDaoImpl pdao = new PatientDaoImpl();
    AdminDAO adao = new AdminDAO();

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        String email = login.getText();
        String pass = password.getText();
        Docteur doc = ddao.getDocteurbyLogin(email, pass);
        Patient pat = pdao.getPatientByLogin(email, pass);
        User admin = adao.getAdminbyLogin(email, pass);
        if (doc != null) {

            User.setConnectedUser(new User(doc.getId(), doc.getNom(), doc.getPrenom(), "Docteur"));
        } else if (pat != null) {

            User.setConnectedUser(new User(pat.getId(), pat.getNom(), pat.getPrenom(), "Patient"));
            User.setManagedUser(new User(pat.getId(), pat.getNom(), pat.getPrenom(), "Patient"));
        } else if (admin != null) {

            User.setConnectedUser(admin);
        } else {
            msg.setText("The email or password is incorrect.");
            return;
        }
        Stage mainStage = new Stage();
        MainFrame mainWindow = new MainFrame();
        mainWindow.start(mainStage);
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
