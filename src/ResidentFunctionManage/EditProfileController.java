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
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.bson.Document;

/**
 * FXML Controller class
 *
 * @author Bingnan Dong
 */
public class EditProfileController implements Initializable {
    private static Profile profile;
    private static User loginUser;
     @FXML private TextField CurrentLocationField;
     @FXML private TextField HobbyField;
     @FXML private TextField JobField;
     @FXML private RadioButton BottledWaterChoice;
     @FXML private RadioButton TapWaterChoice;
     @FXML private RadioButton OtherChoice;
     @FXML private  ToggleGroup WaterChoice;
     @FXML private RadioButton YesChoice;
     @FXML private RadioButton  NoChoice;
     private String  WaterChoiceData;
     private String  AgreeChoiceData;
     
    /**
     * Set the login user
     */
    public void userSet(User user){
        this.loginUser =user;
    }
    
     /**
     * Set the login user
     */
    public void profileSet(Profile profile){
        this.profile = profile;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         //BottledWaterChoice
         if(profile !=null){
            CurrentLocationField.setText(profile.getCurrentLocation());
            HobbyField.setText(profile.getHobby());
            JobField.setText(profile.getJob());
         }
          WaterChoiceData = "BottledWate";
          AgreeChoiceData ="Yes";
         BottledWaterChoice.setOnAction(e -> {
            WaterChoiceData = "BottledWater";
            
        });
          
         TapWaterChoice.setOnAction(e -> {
            WaterChoiceData = "TapWater";
        });
         
        OtherChoice.setOnAction(e -> {
            WaterChoiceData = "Other";
        });
        
        YesChoice.setOnAction(e -> {
            AgreeChoiceData ="Yes";;
        });
        
        NoChoice.setOnAction(e -> {
            AgreeChoiceData ="No";;
        });
           
          
    }    
    public void  handleBack(ActionEvent event) throws IOException{
      Parent root = FXMLLoader.load(getClass().getResource("ProfileView.fxml"));
      FlintWater.stage.setScene(new Scene(root));
      FlintWater.stage.setTitle("ResidentDashboard");
    }
    
    public void  handleSubmit(ActionEvent event) throws IOException, ParseException{
    MongoProfile mongo_profile= new  MongoProfile();
    MongoCollection<Document> collectionProfile =mongo_profile.createCollection(MongoDB.mongoDatabase);
     
     Profile profileget = mongo_profile.findProfile(collectionProfile, loginUser.getUsername());
     if(profileget ==null)
         mongo_profile.insertProfile(collectionProfile, loginUser.getUsername(),CurrentLocationField.getText(), HobbyField.getText(), JobField.getText(), WaterChoiceData, AgreeChoiceData);
     else
         mongo_profile.updatetProfile(collectionProfile, loginUser.getUsername(),CurrentLocationField.getText(), HobbyField.getText(), JobField.getText(), WaterChoiceData, AgreeChoiceData);
        
    }
    
}
