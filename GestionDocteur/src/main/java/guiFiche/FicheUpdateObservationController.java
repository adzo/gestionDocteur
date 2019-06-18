/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiFiche;

import dao.FicheDaoImpl;
import entities.Fiche;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author selmi
 */
public class FicheUpdateObservationController implements Initializable {

    private static Fiche updatedFiche;

    public static Fiche getUpdatedFiche() {
        return updatedFiche;
    }

    public static void setUpdatedFiche(Fiche updatedFiche) {
        FicheUpdateObservationController.updatedFiche = updatedFiche;
    }

    @FXML
    private TextArea observationTextArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        observationTextArea.setText(updatedFiche.getObservation());
    }

    public void update(ActionEvent event) {
        String obs = observationTextArea.getText();
        updatedFiche.setObservation(obs);
        FicheDaoImpl fdao = new FicheDaoImpl();
        if (fdao.updateFiche(updatedFiche)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Result");
            alert.setHeaderText("Good");
            alert.setContentText("observation updated with success");
            alert.showAndWait();
            Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        }

    }
}
