/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManage;

import MongoDBManage.MongoDB;
import MongoDBManage.MongoResidentWaterData;
import ProfessionalFunctionManage.UploadOWDataController;
import ResidentFunctionManage.ProfileViewController;
import ResidentFunctionManage.UWDataHistoryViewController;
import ResidentFunctionManage.UploadUWDataController;
import com.mongodb.client.MongoCollection;
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
import org.bson.Document;

/**
 * FXML Controller class
 *
 * @author Bingnan Dong
 */
public class ResidentUIController implements Initializable {
    @FXML private Text  helloTxt;
    @FXML private Label Status;
    @FXML private Text birthDate;
    @FXML private Text email;
    @FXML private Text sets;
             
    private  static User loginUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int count =0;
        MongoResidentWaterData mongo_residentData= new MongoResidentWaterData();
        MongoCollection<Document> collectionResidentData =mongo_residentData.createCollection(MongoDB.mongoDatabase);
        count = mongo_residentData.countUser(collectionResidentData, loginUser.getUsername());
        
                 helloTxt.setText("Welcome, " +  loginUser.getUsername() +"   ");
                 birthDate.setText("BirthDate: " +  loginUser.getDateOfBirth() +"   ");
                 email.setText("Welcome: " +  loginUser.getEmailaddress() +"   ");
                 sets.setText(count +  "  sets of data have been uploaded");
                // sets of data have been uploaded



    }  
    
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
     * Display OfficalData
     */
    public void  HandleOfficalData(ActionEvent event) throws IOException{
        Navigation navigation = new  Navigation();
        navigation.HandleOfficalData();
    }
    /**
     * Update user profile
     */
    public void  HandleProfile(ActionEvent event) throws IOException{
       ProfileViewController profileViewController  = new ProfileViewController();
       profileViewController.userSet(loginUser);
       Parent root = FXMLLoader.load(getClass().getResource("/ResidentFunctionManage/ProfileView.fxml"));
       FlintWater.stage.setScene(new Scene(root));
       FlintWater.stage.setTitle("ProfileView");  
    }
      /**
     *  Resident insert water data
     */
    public void  HandleUploadData(ActionEvent event) throws IOException{
       UploadUWDataController  uploadUWDataController  = new UploadUWDataController();
       uploadUWDataController.userSet(loginUser);
       Parent root = FXMLLoader.load(getClass().getResource("/ResidentFunctionManage/UploadUWData.fxml"));
       FlintWater.stage.setScene(new Scene(root));
       FlintWater.stage.setTitle("Resident-Upload ");    
    }
     /**
     *  Resident view history water data
     */
    public void  HandleDataHistory(ActionEvent event) throws IOException{
       UWDataHistoryViewController  uwDataHistoryViewController  = new UWDataHistoryViewController();
       uwDataHistoryViewController.userSet(loginUser);
       Parent root = FXMLLoader.load(getClass().getResource("/ResidentFunctionManage/UWDataHistoryView.fxml"));
       FlintWater.stage.setScene(new Scene(root));
       FlintWater.stage.setTitle("Resident-Data "); 
    }
    
}
