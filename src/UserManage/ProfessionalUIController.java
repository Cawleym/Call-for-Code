/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManage;

import ProfessionalFunctionManage.UploadOWDataController;
import ProfessionalFunctionManage.ViewOWDataController;
import flintwater.FlintWater;
import flintwater.Navigation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Bingnan Dong
 */
public class ProfessionalUIController implements Initializable {
 @FXML private Text  helloTxt;
    @FXML private Label Status;
    private  static User loginUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        helloTxt.setText("Welcome, " +  loginUser.getUsername() +"   ");
    }    
    
        /**
     * Set the login user
     */
    public void userSet(User user){
        this.loginUser = user;
    }
    
      /**
     * Display user interface to login
     */
    public void handleLogOut(ActionEvent event) throws IOException{
        Navigation navigation = new  Navigation();
        navigation.handleLogOut();
    }
    
    /**
     * Display HistoryData
     */
    public void handleDataHistory(ActionEvent event) throws IOException{
       ViewOWDataController  viewOWDataController  = new ViewOWDataController();
       viewOWDataController.userSet(loginUser);
       Parent root = FXMLLoader.load(getClass().getResource("/ProfessionalFunctionManage/ViewOWData.fxml"));
       FlintWater.stage.setScene(new Scene(root));
       FlintWater.stage.setTitle("ProfessionalData ");
    }
    
    /**
     * Display HistoryData
     */
    public void handleUploadData(ActionEvent event) throws IOException{
      
       UploadOWDataController  uploadOWDataController  = new UploadOWDataController();
       uploadOWDataController.userSet(loginUser);
       Parent root = FXMLLoader.load(getClass().getResource("/ProfessionalFunctionManage/UploadOWData.fxml"));
       FlintWater.stage.setScene(new Scene(root));
       FlintWater.stage.setTitle("Professional-Upload ");     
    }
}
