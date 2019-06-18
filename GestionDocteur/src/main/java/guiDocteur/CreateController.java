/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiDocteur;

import dao.DocteurDaoImpl;
import entities.Docteur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author selmi
 */
public class CreateController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField tel;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirm;
    @FXML
    private TextArea adress;
    @FXML
    private Label confirmMsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void register(ActionEvent event) {
        confirmMsg.setText("");
        if (nom.getText().isEmpty()
                || prenom.getText().isEmpty()
                || adress.getText().isEmpty()
                || tel.getText().isEmpty()
                || email.getText().isEmpty()
                || password.getText().isEmpty()) {
            confirmMsg.setText("All fields are required");
            return;
        }
        if (!password.getText().equals(confirm.getText())) {
            confirmMsg.setText("password Missmatch");

        } else {
            Docteur doc = new Docteur(
                    nom.getText(),
                    prenom.getText(),
                    adress.getText(),
                    tel.getText(),
                    email.getText(),
                    password.getText()
            );
            DocteurDaoImpl ddao = new DocteurDaoImpl();
            if (ddao.addDocteur(doc)) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Result");
                alert.setHeaderText("Good");
                alert.setContentText("Docteur inserted with success");
                alert.showAndWait();
                clearFields();
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
            }
        }
    }

    public void cancelAdd(ActionEvent event) {
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void clearFields() {
        nom.setText("");
        prenom.setText("");
        adress.setText("");
        tel.setText("");
        email.setText("");
        password.setText("");
        confirm.setText("");
    }
}
