/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfessionalFunctionManage;

import MongoDBManage.MongoDB;
import MongoDBManage.MongoProfessionalWaterData;
import MongoDBManage.MongoUser;
import UserManage.User;
import com.mongodb.client.MongoCollection;
import flintwater.FlintWater;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.bson.Document;

/**
 * FXML Controller class
 *
 * @author Bingnan Dong
 */
public class UploadOWDataController implements Initializable {
    private static User loginUser;
    @FXML private Label userStatus;
                       
    @FXML private TextField STNameField;
    @FXML private TextField CityField;
    @FXML private TextField STNumField;
    @FXML private TextField ZipCodeField;
    @FXML private DatePicker testDate;
    @FXML private TextField Lead2BK250Field;
    @FXML private TextField Lead2BK750Field;
    @FXML private TextField Lead2BK1000Field;
    @FXML private TextField Lead1BK250Field;
    @FXML private TextField Lead1BK750Field;
    @FXML private TextField Lead1BK1000Field;
    @FXML private TextField Copper2BK250Field;
    @FXML private TextField Copper2BK750Field;
    @FXML private TextField Copper2BK1000Field;
    @FXML private TextField Copper1BK250Field;
    @FXML private TextField Copper1BK750Field;
    @FXML private TextField Copper1BK1000Field;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     /**
     * Set the login user
     */
    public void userSet(User user){
        this.loginUser =user;
    }
     public void  handleReset(ActionEvent event) throws IOException{
         testDate.setValue(null);
         STNameField.setText("");
         CityField.setText("");
         STNumField.setText("");
         ZipCodeField.setText("");
         Lead2BK250Field.setText("");
         Lead2BK750Field.setText("");
         Lead2BK1000Field.setText("");
         Lead1BK250Field.setText("");
         Lead1BK750Field.setText("");
         Lead1BK1000Field.setText("");
         Copper2BK250Field.setText("");
         Copper2BK750Field.setText("");
         Copper2BK1000Field.setText("");
         Copper1BK250Field.setText("");
         Copper1BK750Field.setText("");
         Copper1BK1000Field.setText("");
    }
     
     /**
     * Returns whether all inputs for creating account are correct
     * @return   A Boolean representing whether all inputs for creating aaccout are correct
     */
    public Boolean checkUserInput(){
        Boolean check = false;
        if (testDate.getValue() == null){
         userStatus.setText("Date can not be null.");
            return check;
       }
        
        if(STNameField.getText().length()==0){
          
            userStatus.setText("Street Name can not be null.");
            return check;
        }
        
         if( CityField.getText().length()==0){
          
            userStatus.setText("City Name can not be null.");
            return check;
        }
         
        if( ZipCodeField.getText().length()==0){
          
            userStatus.setText("Zipcode can not be null.");
            return check;
        }
       
        int count = 0;
        if(Lead2BK250Field.getText().length()==0)
          count = count + 1;
        if(Lead2BK750Field.getText().length()==0)
          count = count + 1;
        if(Lead2BK1000Field.getText().length()==0)
          count = count + 1;    
        if(Lead1BK250Field.getText().length()==0)
          count = count + 1;
        if(Lead1BK750Field.getText().length()==0)
          count = count + 1;
        if(Lead1BK1000Field.getText().length()==0)
          count = count + 1;   
         if(Copper2BK250Field.getText().length()==0)
          count = count + 1;
        if(Copper2BK750Field.getText().length()==0)
          count = count + 1;
        if(Copper2BK1000Field.getText().length()==0)
          count = count + 1;    
        if(Copper1BK250Field.getText().length()==0)
          count = count + 1;
        if(Copper1BK750Field.getText().length()==0)
          count = count + 1;
        if(Copper1BK1000Field.getText().length()==0)
          count = count + 1;   
        if(count == 12){
             userStatus.setText("At least input one value.");
            return check;
        }
        
        Pattern pattern = Pattern.compile("[0-9]*"); 
        if(Lead2BK250Field.getText().length()>0)
           if(!pattern.matcher(Lead2BK250Field.getText()).matches()) {
               userStatus.setText("please input number for Lead2BK250Field");
               return check;
           }
        if(Lead2BK750Field.getText().length()>0)
           if(!pattern.matcher(Lead2BK750Field.getText()).matches()) {
               userStatus.setText("please input number for Lead2BK750Field");
               return check;
           }
         if(Lead2BK1000Field.getText().length()>0)
           if(!pattern.matcher(Lead2BK1000Field.getText()).matches()) {
               userStatus.setText("please input number for Lead2BK1000Field");
               return check;
           }
         if(Lead1BK250Field.getText().length()>0)
           if(!pattern.matcher(Lead1BK250Field.getText()).matches()) {
               userStatus.setText("please input number for Lead1BK250Field");
               return check;
           }
        if(Lead1BK750Field.getText().length()>0)
           if(!pattern.matcher(Lead1BK750Field.getText()).matches()) {
               userStatus.setText("please input number for Lead1BK750Field");
               return check;
           }
         if(Lead1BK1000Field.getText().length()>0)
           if(!pattern.matcher(Lead1BK1000Field.getText()).matches()) {
               userStatus.setText("please input number for Lead1BK1000Field");
               return check;
           }
        
          if(Copper2BK250Field.getText().length()>0)
           if(!pattern.matcher(Copper2BK250Field.getText()).matches()) {
               userStatus.setText("please input number for Copper2BK250Field");
               return check;
           }
        if(Copper2BK750Field.getText().length()>0)
           if(!pattern.matcher(Copper2BK750Field.getText()).matches()) {
               userStatus.setText("please input number for Copper2BK750Field");
               return check;
           }
         if(Copper2BK1000Field.getText().length()>0)
           if(!pattern.matcher(Copper2BK1000Field.getText()).matches()) {
               userStatus.setText("please input number for Copper2BK1000Field");
               return check;
           }
         if(Copper1BK250Field.getText().length()>0)
           if(!pattern.matcher(Copper1BK250Field.getText()).matches()) {
               userStatus.setText("please input number for Copper1BK250Field");
               return check;
           }
        if(Copper1BK750Field.getText().length()>0)
           if(!pattern.matcher(Copper1BK750Field.getText()).matches()) {
               userStatus.setText("please input number for Copper1BK750Field");
               return check;
           }
         if(Copper1BK1000Field.getText().length()>0)
           if(!pattern.matcher(Copper1BK1000Field.getText()).matches()) {
               userStatus.setText("please input number for Copper1BK1000Field");
               return check;
           }
        

      check = true;
      return check;
    }
     
    public void  handleBack(ActionEvent event) throws IOException{
      Parent root = FXMLLoader.load(getClass().getResource("/UserManage/ProfessionalUI.fxml"));
       FlintWater.stage.setScene(new Scene(root));
       FlintWater.stage.setTitle("ProfessionaDashboard");
    }
    
    public void  handleUpload(ActionEvent event) throws IOException, ParseException{
        if(!checkUserInput())
            return;
        
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateOftest = testDate.getValue().format(formatters);
        String STName = STNameField.getText();
        String STNum =  STNumField.getText();
        String City =  CityField.getText();
        String Zip =  ZipCodeField.getText();
        
        String Lead2BK250 =   Lead2BK250Field.getText();
        String Lead2BK750 =   Lead2BK750Field.getText();
        String Lead2BK1000 =   Lead2BK1000Field.getText();
        String Lead1BK250 =   Lead1BK250Field.getText();
        String Lead1BK750 =   Lead1BK750Field.getText();
        String Lead1BK1000 =   Lead1BK1000Field.getText();
        String Copper2BK250 =   Copper2BK250Field.getText();
        String Copper2BK750 =   Copper2BK750Field.getText();
        String Copper2BK1000 =   Copper2BK1000Field.getText();
        String Copper1BK250 =   Copper1BK250Field.getText();
        String Copper1BK750 =   Copper1BK750Field.getText();
        String Copper1BK1000 =   Copper1BK1000Field.getText();
        
         MongoProfessionalWaterData mongo_PrefessionalWaterData= new MongoProfessionalWaterData();
         MongoCollection<Document> collectionPreofessionalWaterData =mongo_PrefessionalWaterData.createCollection(MongoDB.mongoDatabase);
         
         mongo_PrefessionalWaterData.insertPrefessionalWaterData(collectionPreofessionalWaterData, 
                 loginUser.getUsername(),dateOftest,STName,STNum,City,Zip,
                 Lead2BK250,Lead2BK750,Lead2BK1000,Lead1BK250,Lead1BK750,Lead1BK1000,
                 Copper2BK250,Copper2BK750,Copper2BK1000,Copper1BK250,Copper1BK750,Copper1BK1000
                 );
         userStatus.setText("Insert successfully");
    }
}
