/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiPatient;

import dao.PatientDaoImpl;
import entities.Docteur;
import entities.Patient;
import entities.User;
import guiDocteur.DocteurListController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author selmi
 */
public class PatientListController implements Initializable {

    @FXML
    private TableView<Patient> tableauPatients;
    @FXML
    private TableColumn<Patient, Integer> id;
    @FXML
    private TableColumn<Patient, String> nom;
    @FXML
    private TableColumn<Patient, String> prenom;
    @FXML
    private TableColumn<Patient, String> tel;

    PatientDaoImpl pdao = new PatientDaoImpl();
    List<Patient> listPatients = new ArrayList<>();
    ObservableList<Patient> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable();
    }

    private void updateTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        listPatients = pdao.getAllByDocteur();
        if (listPatients != null) {
            data = FXCollections.observableArrayList(listPatients);
            tableauPatients.setItems(data);
        } else {
            System.out.println("list empty from database");
        }

    }

    public void addPatient() {
        try {
            Stage addPatient = new Stage();
            addPatient.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    updateTable();
                }
            });
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Patient/PatientCreate.fxml"));
            Scene scene = new Scene(root);
            addPatient.setTitle("Add Patient");
            addPatient.setScene(scene);
            addPatient.initModality(Modality.APPLICATION_MODAL);
            addPatient.setResizable(false);
            addPatient.show();
        } catch (IOException ex) {
            Logger.getLogger(PatientListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePatient() {
        Patient p = tableauPatients.getSelectionModel().getSelectedItem();
        if (p != null) {
            try {
                Stage updatePatient = new Stage();
                updatePatient.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        updateTable();
                    }
                });
                UpdateController.setUsedPatient(p);
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/Patient/update.fxml"));
                Scene scene = new Scene(root);
                updatePatient.setTitle("Update patient");
                updatePatient.setScene(scene);
                updatePatient.initModality(Modality.APPLICATION_MODAL);
                updatePatient.setResizable(false);
                updatePatient.show();
            } catch (IOException ex) {
                Logger.getLogger(PatientListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            alertNullPointer();
        }
    }
public void alertNullPointer() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Patients manager");
        alert.setContentText("No patient selected");
        alert.showAndWait();
    }

    public void deletePatient() {
Patient d = tableauPatients.getSelectionModel().getSelectedItem();
        if (d != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "you are going to delete Mr \"" + d.getNom() + ", " + d.getPrenom() + "\"", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Doctors manager");
            alert.setHeaderText("Are you sure ?!");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                if (pdao.deletePatient(d)) {
                    updateTable();
                }
            }
        } else {
            alertNullPointer();
        }
    }

    public void openFiche() {
        Patient d = tableauPatients.getSelectionModel().getSelectedItem();
        if (d != null) {
            User.setManagedUser(new User(d.getId(), d.getNom(), d.getPrenom(), "Patient"));
            try {
                Stage listFiche = new Stage();
                listFiche.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        updateTable();
                    }
                });
                
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/fiche/FicheList.fxml"));
                Scene scene = new Scene(root);
                listFiche.setTitle("Liste Fiche");
                listFiche.setScene(scene);
                listFiche.initModality(Modality.APPLICATION_MODAL);
                listFiche.setResizable(false);
                listFiche.show();
            } catch (IOException ex) {
                Logger.getLogger(PatientListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            alertNullPointer();
        }
    }

}
