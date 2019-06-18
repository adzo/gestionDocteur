/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiPatient;

import dao.PatientDaoImpl;
import entities.Patient;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
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
public class UpdateController implements Initializable {

    private static Patient usedPatient;

    public static Patient getUsedPatient() {
        return usedPatient;
    }

    public static void setUsedPatient(Patient usedPatient) {
        UpdateController.usedPatient = usedPatient;
    }
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
    @FXML
    private DatePicker birthDate;

    PatientDaoImpl pdao = new PatientDaoImpl();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        nom.setText(usedPatient.getNom());
        prenom.setText(usedPatient.getPrenom());
        tel.setText(usedPatient.getTel());
        email.setText(usedPatient.getEmail());
        adress.setText(usedPatient.getAdresse());
        birthDate.setValue(new Date(usedPatient.getDateDeNaissance().getTime()).toLocalDate());
    }

    public void update(ActionEvent event) {
        System.out.println("Update");
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
            Patient patient = new Patient(
                    usedPatient.getId(),
                    nom.getText(),
                    prenom.getText(),
                    Date.valueOf(birthDate.getValue()),
                    adress.getText(),
                    tel.getText(),
                    email.getText(),
                    password.getText()
            );
            System.out.println(patient);
            PatientDaoImpl pdao = new PatientDaoImpl();
            if (pdao.updatePatient(patient)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Result");
                alert.setHeaderText("Good");
                alert.setContentText("Patient updated with success");
                alert.showAndWait();
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
            }
        }
    }

    public void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
}
