/*
 * Developed by: Alexis Peralta Holyoak.
 */
package com.incomab.controller.impl;

import com.incomab.util.ErroresUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author peral
 */
public class LoginController implements Initializable {
    private final double IMG_WIDTH = 900;
    private final double IMG_HEIGHT = 600;
    Image imgCajamarca;
    Image imgChiclayo;
    Image imgLoro;
    Image imgMacchupicchu;
    ErroresUtil error;
    private final int NUM_OF_IMGS = 4 ;
    private final int SLIDE_FREQ = 4; // in secs
    @FXML
    private MaterialDesignIconView btnClose;
    @FXML
    private MaterialDesignIconView btnMinimize;
    private ImageView bckLogin;
    @FXML
    private HBox hbxLogin;
    
   
    
    @FXML
    private JFXButton btnIngresar;
    @FXML
    private JFXTextField txtUSER;
    @FXML
    private JFXPasswordField txtCONTRA;
    @FXML
    private JFXSnackbar snackMensaje;
    @FXML
    private AnchorPane LOGINPANE;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       LoadSlide();
       snackMensaje=new JFXSnackbar(LOGINPANE);
       error=new ErroresUtil();
       }    
    //A LIST OF IMAGES IN A ETHERNAL LOOP TRANSITION
    private void LoadSlide(){
         try {
            //define image
            imgChiclayo=new Image(getClass().getResourceAsStream("/images/chiclayo.png"));
            imgCajamarca=new Image(getClass().getResourceAsStream("/images/cajamarca.png"));
            imgLoro=new Image(getClass().getResourceAsStream("/images/loro.png"));
            imgMacchupicchu=new Image(getClass().getResourceAsStream("/images/macchupicchu.png"));
            //create imageview
            ImageView vwChiclayo=new ImageView(imgChiclayo);
            ImageView vwCajamarca=new ImageView(imgCajamarca);
            ImageView vwLoro=new ImageView(imgLoro);
            ImageView vwMacchupicchu=new ImageView(imgMacchupicchu);
            //fil the HBox
            hbxLogin.getChildren().addAll(vwChiclayo,vwCajamarca,vwLoro,vwMacchupicchu);
            //transition
            EventHandler<ActionEvent> slideAction = (ActionEvent t) -> {
                TranslateTransition trans = new TranslateTransition(Duration.seconds(1.5), hbxLogin);           
                trans.setByX(-IMG_WIDTH);
                trans.setInterpolator(Interpolator.EASE_BOTH);
                trans.play();
            };
            //eventHandler
            EventHandler<ActionEvent> resetAction = (ActionEvent t) -> {
                TranslateTransition trans = new TranslateTransition(Duration.seconds(1), hbxLogin);
                trans.setByX((NUM_OF_IMGS - 1) * IMG_WIDTH);
                trans.setInterpolator(Interpolator.EASE_BOTH);
                trans.play();
            };
            //handle frames of animation
            List<KeyFrame> keyFrames = new ArrayList<>();
            for (int i = 1; i <= NUM_OF_IMGS; i++) {
                if (i == NUM_OF_IMGS) {
                    keyFrames.add(new KeyFrame(Duration.seconds(i * SLIDE_FREQ), resetAction));
                } else {
                    keyFrames.add(new KeyFrame(Duration.seconds(i * SLIDE_FREQ), slideAction));
                }
            }
            //add timeLine
            Timeline anim = new Timeline(keyFrames.toArray(new KeyFrame[NUM_OF_IMGS]));
            anim.setCycleCount(Timeline.INDEFINITE);
            anim.playFromStart();
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    //to close the login screen
    @FXML
    private void handleClose(MouseEvent event) {
        System.exit(0);
    }
    //to minimize the login screen
    @FXML
    private void handleMinimize(MouseEvent event) {
        Stage stage = (Stage)((MaterialDesignIconView)event.getSource()).getScene().getWindow();
        // is stage minimizable into task bar. (true | false)
        stage.setIconified(true);
    }

  
    //show the snackbar on screen whenever mousevent on login button 
    private void Show_snack(MouseEvent e,String m){
        snackMensaje.show(m, 3000);   
        Input_fail(m);
    }
    //handle style of textfields when something went wrong
    private void Input_fail(String m){
        String stilo="-fx-text-fill: red; -jfx-focus-color: red; -jfx-unfocus-color:red";
        if(m.equals(error.getIncorrectUser())){
        txtUSER.setStyle(stilo);        
        }
        if(m.equals(error.getIncorrectPassw())){
         txtCONTRA.setStyle(stilo);    
        }
    }
    

    
    //handle style of textfields when everything is correct
    private void Input_ok(){
        String stilo="-fx-text-fill: black; -jfx-focus-color: #2c4251; -jfx-unfocus-color: #4d4d4d;";
        txtUSER.setStyle(stilo);
        txtCONTRA.setStyle(stilo);
    }  
    
    private void Load_main_window(MouseEvent event){               
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/incomab/view/MainView.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
          }
}

    @FXML
    private void handleLogin(MouseEvent event) {
    
  
         Input_ok();
         String ex="";
         if(txtUSER.getText().trim().equals("")){
             ex=error.getIncorrectUser();
             Show_snack(event, ex);
         }else if(txtCONTRA.getText().trim().equals("")){
             ex=error.getIncorrectPassw();
             Show_snack(event, ex);
         } else{
             Stage stage = (Stage) btnIngresar.getScene().getWindow();
            // close login view
            stage.close();
             Load_main_window(event);
             
             
             
             
         }       
    
    
        
    }
}
    
    
    
    

