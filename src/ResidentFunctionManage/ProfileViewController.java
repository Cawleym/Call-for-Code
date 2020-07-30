/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResidentFunctionManage;

import MongoDBManage.MongoDB;
import MongoDBManage.MongoProfile;
import UserManage.User;
import com.mongodb.client.MongoCollection;
import flintwater.FlintWater;
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
import org.bson.Document;

/**
 * FXML Controller class
 *
 * @author Bingnan Dong
 */
public class ProfileViewController implements Initializable {
    private static User loginUser;
    private static  Profile  profile;
   
    @FXML private Label userStatus;
    @FXML private Label  LocationLabel;
    @FXML private Label  HobbyLabel;
    @FXML private Label  JobLabel;
    @FXML private Label  WaterChoiceLabel;
    @FXML private Label  AgreementLabel;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    MongoProfile mongo_profile= new  MongoProfile();
    MongoCollection<Document> collectionProfile =mongo_profile.createCollection(MongoDB.mongoDatabase);
     
     profile = mongo_profile.findProfile(collectionProfile, loginUser.getUsername());
     if(profile !=null){
         LocationLabel.setText(profile.getCurrentLocation());
         HobbyLabel.setText(profile.getHobby());
         JobLabel.setText(profile.getJob());
         WaterChoiceLabel.setText(profile.getWaterChoice());
         AgreementLabel.setText(profile.getDataAgree());
     }
    }    
    /**
     * Set the login user
     */
    public void userSet(User user){
        this.loginUser =user;
    }
    
    public void  handleEdit(ActionEvent event) throws IOException{
      EditProfileController editProfileController  = new EditProfileController();
      editProfileController.userSet(loginUser);
      editProfileController.profileSet(profile);
      Parent root = FXMLLoader.load(getClass().getResource("EditProfile.fxml"));
      FlintWater.stage.setScene(new Scene(root));
      FlintWater.stage.setTitle("ProfileEdit");
    }
    
    public void  handleBack(ActionEvent event) throws IOException{
      Parent root = FXMLLoader.load(getClass().getResource("/UserManage/ResidentUI.fxml"));
      FlintWater.stage.setScene(new Scene(root));
      FlintWater.stage.setTitle("ResidentDashboard");
    }
    
}
