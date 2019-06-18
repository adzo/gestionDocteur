/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiFiche;

import dao.FicheDaoImpl;
import entities.Fiche;
import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author selmi
 */
public class FicheCreateController implements Initializable {

     @FXML
    private TextField taille;
    @FXML
    private TextField poid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void addFiche(ActionEvent event){
        String tailleGot = taille.getText();
        String poidGot = poid.getText();
        int height = Integer.parseInt(tailleGot);
        float weight = Float.parseFloat(poidGot);
        Fiche fiche = new Fiche(height,weight,User.getManagedUser().getId());
        FicheDaoImpl fdao = new FicheDaoImpl();
        if(fdao.addFiche(fiche)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Result");
                alert.setHeaderText("Good");
                alert.setContentText("Fiche inserted with success");
                alert.showAndWait();
                Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
                stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        }
    }
}
