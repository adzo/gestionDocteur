/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiFiche;


import dao.FicheDaoImpl;
import entities.Fiche;
import entities.User;
import guiDocteur.DocteurListController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class FicheListController implements Initializable {

    @FXML
    private TableView<Fiche> tableauFiches;
    @FXML
    private TableColumn<Fiche, Integer> id;
    @FXML
    private TableColumn<Fiche, Integer> taille;
    @FXML
    private TableColumn<Fiche, Float> poid;
    @FXML
    private TableColumn<Fiche, String> type;
    @FXML
    private TableColumn<Fiche, Date> dateFiche;
    FicheDaoImpl ddao = new FicheDaoImpl();
    List<Fiche> listFiches = new ArrayList<>();
    ObservableList<Fiche> data;
    
    @FXML
    private Button updateObservation;
    
    @FXML
    private Button addNew;
    
    @FXML
    private Label description;
    @FXML
    private Label labelImc;
    @FXML
    private Label labelImc2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        updateTable();
        if(User.getConnectedUser().getType().equals("Docteur")){
            addNew.setVisible(false);
            updateObservation.setVisible(true);
            labelImc.setVisible(true);
            labelImc2.setVisible(true);
        }else{
            addNew.setVisible(true);
            updateObservation.setVisible(false);
            labelImc.setVisible(false);
            labelImc2.setVisible(false);
        }
    }

    @FXML
    public void mouseClick(MouseEvent event) {
        if (event.getClickCount() == 1) {
            updateDescription();
            
        }
    }

    public void addFiche() {
        try {
            Stage addDocteur = new Stage();
            addDocteur.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {

                    updateTable();
                }
            });
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/fiche/ficheCreate.fxml"));
            Scene scene = new Scene(root);
            addDocteur.setTitle("Add Fiche");
            addDocteur.setScene(scene);
            addDocteur.initModality(Modality.APPLICATION_MODAL);
            addDocteur.setResizable(false);
            addDocteur.show();
        } catch (IOException ex) {
            Logger.getLogger(DocteurListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateFiche.setCellValueFactory(new PropertyValueFactory<>("dateFiche"));
        taille.setCellValueFactory(new PropertyValueFactory<>("taille"));
        poid.setCellValueFactory(new PropertyValueFactory<>("poids"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        listFiches = ddao.getFiches(User.getManagedUser());
        data = FXCollections.observableArrayList(listFiches);
        tableauFiches.setItems(data);
    }

    private void updateDescription() {
        Fiche f = tableauFiches.getSelectionModel().getSelectedItem();
        if (f != null){
            FicheUpdateObservationController.setUpdatedFiche(f);
            System.out.println(FicheUpdateObservationController.getUpdatedFiche());
            description.setText(f.getObservation());
            labelImc.setText(String.valueOf(f.getImc()));
        }
    }
    
    public void updateByDoctor(){
        Fiche f = tableauFiches.getSelectionModel().getSelectedItem();
        System.out.println(f);
        
        try {
            Stage updateObservation = new Stage();
            updateObservation.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {

                    updateTable();
                }
            });
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/fiche/ficheUpdateObservation.fxml"));
            Scene scene = new Scene(root);
            updateObservation.setTitle("Update Observation");
            updateObservation.setScene(scene);
            updateObservation.initModality(Modality.APPLICATION_MODAL);
            updateObservation.setResizable(false);
            updateObservation.show();
        } catch (IOException ex) {
            Logger.getLogger(DocteurListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
