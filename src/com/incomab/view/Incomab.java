/*
 * Developed by: Alexis Peralta Holyoak.
 */
package com.incomab.view;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author peral
 */
public class Incomab extends Application {
       private final double FRM_WIDTH = 900;
    private final double FRM_HEIGHT = 600;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root,FRM_WIDTH,FRM_HEIGHT); 
        stage.setScene(scene);
       // startAnimation(imgContainer);
        stage.show();//
          
    }

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        launch(args);
    }
    
}
