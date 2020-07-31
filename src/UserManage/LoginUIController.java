/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManage;

import DBManage.SqliteDB;
import DBManage.SqliteUser;
import MongoDBManage.MongoDB;
import MongoDBManage.MongoUser;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

/**
 * FXML Controller class
 *
 * @author Bingnan Dong
 */
public class LoginUIController implements Initializable {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button createAccountButton;
    @FXML private Button loginButton;
    @FXML private Label errorLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameField.setText("bvd1234");
        passwordField.setText("bvd1234");
    }  
    
    
     /**
     * Check login user in DB
     */
    public void LoginCheck(ActionEvent event){
          
        String username = usernameField.getText();
        String password = passwordField.getText();
       
       /* Connection conn =SqliteDB.getConnection();
        SqliteUser sqlite_user= new SqliteUser();
        User user =sqlite_user.loginCheck(conn, username, password);
        if (user == null ){
           errorLabel.setText("Invalid credentials, please try again or create account");  
        }
        else { 
             Dashboard(user);
        }*/
        // MongoDatabase conn =MongoDB.getConnection();
        
         MongoUser mongo_user= new MongoUser();
         MongoCollection<Document> collectionUser =mongo_user.createCollection(MongoDB.mongoDatabase);
         //MongoCollection<Document> collectionUser =mongo_user.createCollection(conn);
         User user = mongo_user.loginCheck(collectionUser, username,password);
      if (user == null ){
           errorLabel.setText("Invalid credentials, please try again or create account");   
        }
       else {
              Dashboard(user);

        }
    }
    
    
    /**
     * Display user interface to create account
     */
    public void CreateAccount() {
        
        Stage mainStage = (Stage) createAccountButton.getScene().getWindow();
        Parent root;
        Scene scene;
        try {
            root = FXMLLoader.load(getClass().getResource("CreateAccountUI.fxml"));
            scene = new Scene(root);
            mainStage.setTitle("CreateAccount");
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
         /**
     * According to user type to display PatientDashboard or DoctorDashboard
     */
    
    public void Dashboard(User user){
        Stage mainStage = (Stage) loginButton.getScene().getWindow();
        Parent root;
        Scene scene;
        try {
            if(user.getUsertype().equalsIgnoreCase("professional")){
               ProfessionalUIController professionalController  = new ProfessionalUIController();
               professionalController.userSet(user);
               root = FXMLLoader.load(getClass().getResource("ProfessionalUI.fxml"));
               mainStage.setTitle("ProfessionalDashboard");
            }
            else{
                ResidentUIController residentController  = new ResidentUIController();
                residentController.userSet(user);
                root = FXMLLoader.load(getClass().getResource("ResidentUI.fxml"));
                mainStage.setTitle("ResidentDashboard");
            }
            scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
     /**
     * Return to login user interface
     */
     public  void handleLogoutAction(ActionEvent event) throws IOException {
        // TODO
        Parent Parent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene Scene = new Scene(Parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(Scene);
        window.setTitle("Login");
        window.show();
    }    
    
}
