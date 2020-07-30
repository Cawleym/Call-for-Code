/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManage;

import MongoDBManage.MongoDB;
import MongoDBManage.MongoUser;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import flintwater.Navigation;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;


/**
 * FXML Controller class
 *
 * @author Bingnan Dong
 */
public class CreateAccountUIController implements Initializable {

    @FXML private Button createAccountButton;
    @FXML private Button goBack;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField passwordField1;
    @FXML private TextField emaildField;
    @FXML private Label userStatus;
    @FXML private ChoiceBox<String> userChoice;
    @FXML private DatePicker birthDate;
    @FXML private MenuItem AddUser;
    private String passwordValue;
    private boolean passwordsMatch = false;
     public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          String[] options = {"Professional","Resident"};
        ObservableList<String> list = FXCollections.observableArrayList(options);
        userChoice.setItems(list);
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
           this.updatePasswordStatus(newValue);
           this.passwordValue = newValue;
        });
    }    
    
     /**
     * check all input fields and store user in DB
     */
    public void handleCreateAccount(ActionEvent event) throws IOException, ParseException{
        
        if(!checkUserInput())
            return;
        
        String fName = firstNameField.getText();
        String lName = lastNameField.getText();
        String uName =  usernameField.getText();
        String email = emaildField.getText();
        String password = passwordField.getText();
        String password2 = passwordField1.getText();
        String userType = userChoice.getValue();
         //LocalDate dateofbirth  =birthDate.getValue();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateofBirth = birthDate.getValue().format(formatters);
        //access user in DB
        Boolean userExist=accessUserInDB( uName);
         if (userExist){
            userStatus.setText("username has existed, please input again");
            return;
        }
          //insert user data in DB
         User user= new User(fName,lName,uName,email,password ,dateofBirth,userType);
         insertUserInDB(user);
         userStatus.setText("Account has saved in DB");  
         handleGoBack(event);
     
    }
     /**
     * Returns whether all inputs for creating account are correct
     * @return   A Boolean representing whether all inputs for creating aaccout are correct
     */
    public Boolean checkUserInput(){
        
        Boolean check = false;
        String fName = firstNameField.getText();
        String lName = lastNameField.getText();
        String uName =  usernameField.getText();
        String email = emaildField.getText();
        String password = passwordField.getText();
        String password2 = passwordField1.getText();
        String userType = userChoice.getValue();
        LocalDate dateofbirth  =birthDate.getValue();
        //System.out.println("date of Birth:"+ dateofbirth.toString());
         
       
      if(fName.length()==0){
          
            userStatus.setText("First name can not be null.");
            return check;
        }
        
       if(lName.length()==0){
            userStatus.setText("Last name can not be null.");
             return check;
        }
        
       if(uName.length()==0){
            userStatus.setText("user name can not be null.");
             return check;
        }
        
     
        if(password.length()==0){
            userStatus.setText("Password can not be null.");
            return check;
        }
        
        if(!password.equals(password2)){
             passwordsMatch = false;
             userStatus.setText("Passwords do not match.");
             return check;
          } 
        else{
             userStatus.setText(" ");
        }
        
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        if(!matcher.find()){
              userStatus.setText("Wrong email format.");
              return check;
          }
         
       if (birthDate.getValue() == null){
         userStatus.setText("Birth of date can not be null.");
            return check;
       }
        if (userType == null){
            userStatus.setText("User type can not be null.");
            return check;
        }
        
        check = true;
        return check;
    }
   /**
     * Takes the user inputted password from CreateAccountUI and
     * determines the strength of the password based on their input
     * @param password 
     */
    public void updatePasswordStatus(String password) {

        if(password.length() == 0) {
            userStatus.setText("");
        }
        else if(password.length() < 5) {
            userStatus.setText("WEAK PASSWORD");
        }
        else if(password.length() < 9 && password.length() >= 5) {
            userStatus.setText("MODERATE PASSWORD");
        }
        else {
             userStatus.setText("STRONG PASSWORD");
        }   
    }
    
    
    /**
     * Insert a new user in DB
     * @param user Insert a new user in DB
     */ 
    public void insertUserInDB(User user) throws ParseException{
       //connect DataBase
       //  MongoDatabase conn =MongoDB.getConnection();
         MongoUser mongo_user= new MongoUser();
         MongoCollection<Document> collectionUser =mongo_user.createCollection(MongoDB.mongoDatabase);
         mongo_user.insertUser(collectionUser, user);
         
          //access user data from DB
         ArrayList<User> users = new ArrayList<>();
         users =  mongo_user.findUser(collectionUser);
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
 
         for ( int i=0 ; i <users.size();i++){
             System.out.println( "The infromation of user"+(i+1) +";" );
             System.out.println( "FirstName: " + users.get(i).getFirstName() );
             System.out.println( "LastName: " + users.get(i).getLastName() );
             System.out.println( "Username: " + users.get(i).getUsername() );
             System.out.println( "emailaddress: " +  users.get(i).getEmailaddress() );
             System.out.println( "Password: " +users.get(i).getPassword() );
               
             System.out.println( "dateBirth: " +users.get(i).getDateOfBirth() );
             System.out.println( "UserType: " +users.get(i).getUsertype() );
             System.out.println();
         }
     }
     /**
     * Returns whether a user in DB
     * @return   A Boolean representing whether a user in DB
     */
    public Boolean accessUserInDB(String uName){
         //connect DataBase
         MongoDatabase conn =MongoDB.getConnection();
         
         MongoUser mongo_user= new MongoUser();
         MongoCollection<Document> collectionUser =mongo_user.createCollection(MongoDB.mongoDatabase);
          
         Boolean userExist= mongo_user.checkUsername(collectionUser, uName);
         return userExist;
    }
       /**
     * Display user interface to login
     */
    public void handleGoBack(ActionEvent event) throws IOException{
        Navigation navigation = new  Navigation();
        navigation.handleLogOut();
    }
    
    
}
