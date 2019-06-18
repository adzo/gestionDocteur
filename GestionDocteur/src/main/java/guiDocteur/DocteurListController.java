/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiDocteur;

import dao.DocteurDaoImpl;
import entities.Docteur;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author selmi
 */
public class DocteurListController implements Initializable {

    @FXML
    private TableView<Docteur> tableauDocteurs;
    @FXML
    private TableColumn<Docteur, Integer> id;
    @FXML
    private TableColumn<Docteur, String> nom;
    @FXML
    private TableColumn<Docteur, String> prenom;
    @FXML
    private TableColumn<Docteur, String> tel;
    @FXML
    private TableColumn<Docteur, String> email;
    DocteurDaoImpl ddao = new DocteurDaoImpl();
    List<Docteur> listDocteurs = new ArrayList<>();
    ObservableList<Docteur> data;

    @FXML
    public void mouseClick(MouseEvent event){
        if (event.getClickCount() ==2){
            updateDocteur();
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Populatibg table:
        updateTable();
    }

    public void updateDocteur() {
        Docteur d = tableauDocteurs.getSelectionModel().getSelectedItem();
        if (d != null) {
            System.out.println(d);
            // show update window
            try {
                Stage addDocteur = new Stage();
                addDocteur.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        updateTable();
                    }
                });
                UpdateController.setUsedDocteur(d);
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/docteur/docteurUpdate.fxml"));
                Scene scene = new Scene(root);
                addDocteur.setTitle("Update docteur");
                addDocteur.setScene(scene);
                addDocteur.initModality(Modality.APPLICATION_MODAL);
                addDocteur.setResizable(false);
                addDocteur.show();
            } catch (IOException ex) {
                Logger.getLogger(DocteurListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            alertNullPointer();
        }

    }

    public void deleteDocteur() {
        Docteur d = tableauDocteurs.getSelectionModel().getSelectedItem();
        if (d != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "you are going to delete Mr \"" + d.getNom() + ", " + d.getPrenom() + "\"", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Doctors manager");
            alert.setHeaderText("Are you sure ?!");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                if (ddao.deleteDocteur(d)) {
                    updateTable();
                }
            }
        } else {
            alertNullPointer();
        }
    }

    public void alertNullPointer() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Doctors manager");
        alert.setContentText("No Doctor selected");
        alert.showAndWait();
    }

    public void updateTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        listDocteurs = ddao.getAll();
        data = FXCollections.observableArrayList(listDocteurs);
        tableauDocteurs.setItems(data);
    }

    public void addDocteur() {
        try {
            Stage addDocteur = new Stage();
            addDocteur.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {

                    updateTable();
                }
            });
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/docteur/docteurCreate.fxml"));
            Scene scene = new Scene(root);
            addDocteur.setTitle("Add doctor");
            addDocteur.setScene(scene);
            addDocteur.initModality(Modality.APPLICATION_MODAL);
            addDocteur.setResizable(false);
            addDocteur.show();
        } catch (IOException ex) {
            Logger.getLogger(DocteurListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
