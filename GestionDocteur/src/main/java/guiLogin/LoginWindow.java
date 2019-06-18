/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiLogin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author selmi
 */
public class LoginWindow extends Application {

    @Override
    public void start(Stage loginStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginWindow.fxml"));
        loginStage.setTitle("Gestion Docteur");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        loginStage.setScene(scene);
        loginStage.setResizable(true);
        loginStage.show();
    }
    
}
