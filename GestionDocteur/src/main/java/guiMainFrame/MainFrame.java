/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiMainFrame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author selmi
 */
public class MainFrame extends Application{

    @Override
    public void start(Stage mainStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainFrame.fxml"));
        mainStage.setTitle("Gestion Docteur");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/fxmldashboard.css");
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }
    
}
