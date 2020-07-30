/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResidentFunctionManage;

import DataManage.ResidentWaterData;
import MongoDBManage.MongoDB;
import MongoDBManage.MongoResidentWaterData;
import UserManage.User;
import com.mongodb.client.MongoCollection;
import flintwater.FlintWater;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
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
public class EditUWDataController implements Initializable {
  private static User loginUser;
   @FXML private Label userStatus;
   private static ResidentWaterData residentWaterData;
   @FXML private TextField STNameField;
   @FXML private TextField ZipCodeField;
   @FXML private DatePicker TestDatePicker;
   @FXML private TextField Lead250Field;
   @FXML private TextField Lead750Field;
   @FXML private TextField Lead1000Field;
   @FXML private TextField Copper250Field;
   @FXML private TextField Copper750Field;
   @FXML private TextField Copper1000Field;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         STNameField.setText(residentWaterData.getStreetName());
         ZipCodeField.setText(residentWaterData.getZipCode());
         DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
         LocalDate dateoftest =  LocalDate.parse(residentWaterData.getTestDate(), fmt); 
         TestDatePicker.setValue(dateoftest);
         
         if(residentWaterData.getLead250() == null){
            Lead250Field.setText("");
         }
         else
            Lead250Field.setText(residentWaterData.getLead250().toString());
         
         if(residentWaterData.getLead750() == null){
            Lead750Field.setText("");
         }
         else
            Lead750Field.setText(residentWaterData.getLead750().toString());
         
         if(residentWaterData.getLead1000() == null){
            Lead1000Field.setText("");
         }
         else
            Lead1000Field.setText(residentWaterData.getLead1000().toString());
         
          if(residentWaterData.getCopper250() == null){
            Copper250Field.setText("");
         }
         else
            Copper250Field.setText(residentWaterData.getCopper250().toString());
         
         if(residentWaterData.getCopper750() == null){
            Copper750Field.setText("");
         }
         else
            Copper750Field.setText(residentWaterData.getCopper750().toString());
         
         if(residentWaterData.getCopper1000() == null){
            Copper1000Field.setText("");
         }
         else
            Copper1000Field.setText(residentWaterData.getCopper1000().toString());
         
    }    
     /**
     * Set the login user
     */
    public void userSet(User user){
        this.loginUser =user;
    }
    
    /**
     * Sets the professionalWaterData of login user
     * @param professionalWaterData Sets the the username of login
     */
    public void residentWaterDataSet(ResidentWaterData residentWaterData){
        this.residentWaterData = residentWaterData;
    }
    
    public void  handleBack(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("UWDataHistoryView.fxml"));
       FlintWater.stage.setScene(new Scene(root));
       FlintWater.stage.setTitle("ResidentData");
    }
    public void  handleUpload(ActionEvent event) throws IOException, ParseException{
        if(!checkUserInput())
           return;
         userStatus.setText("");
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateOftest = TestDatePicker.getValue().format(formatters);
          userStatus.setText("");
        MongoResidentWaterData mongo_residentWaterData= new MongoResidentWaterData();
        MongoCollection<Document> collectionResidentWaterData =mongo_residentWaterData.createCollection(MongoDB.mongoDatabase);
        mongo_residentWaterData.updateResidentWaterData(collectionResidentWaterData, residentWaterData.getId(), loginUser.getUsername(), dateOftest, STNameField.getText(), ZipCodeField.getText(),
                                                         Lead250Field.getText(), Lead750Field.getText(), Lead1000Field.getText(), Copper250Field.getText(), Copper750Field.getText(), Copper1000Field.getText());
        userStatus.setText("");
        
    }
    
      public void  handleReset(ActionEvent event) throws IOException{
         TestDatePicker.setValue(null);
         STNameField.setText("");
         ZipCodeField.setText("");
         Lead250Field.setText("");
         Lead750Field.setText("");
         Lead1000Field.setText("");
         Copper250Field.setText("");
         Copper750Field.setText("");
         Copper1000Field.setText("");
                  

     }
       /**
     * Returns whether all inputs for creating account are correct
     * @return   A Boolean representing whether all inputs for creating aaccout are correct
     */
    public Boolean checkUserInput(){
        Boolean check = false;
        if (TestDatePicker.getValue() == null){
         userStatus.setText("Date can not be null.");
            return check;
       }
        
        if(STNameField.getText().length()==0){
          
            userStatus.setText("Street Name can not be null.");
            return check;
        }
        
        if( ZipCodeField.getText().length()==0){
          
            userStatus.setText("Zip can not be null.");
            return check;
        }
         
       
        int count = 0;
        if(Lead250Field.getText().length()==0)
          count = count + 1;
        if(Lead750Field.getText().length()==0)
          count = count + 1;
        if(Lead1000Field.getText().length()==0)
          count = count + 1;
        if(Copper250Field.getText().length()==0)
          count = count + 1;
        if(Copper750Field.getText().length()==0)
          count = count + 1;
        if(Copper1000Field.getText().length()==0)
          count = count + 1;
         
        if(count == 6){
             userStatus.setText("At least input one value.");
            return check;
        }
        
        Pattern pattern = Pattern.compile("[0-9]*"); 
         if(Lead250Field.getText().length()>0)
           if(!pattern.matcher(Lead250Field.getText()).matches()) {
               userStatus.setText("please input number for Lead250");
               return check;
           }
         
         if(Lead750Field.getText().length()>0)
           if(!pattern.matcher(Lead750Field.getText()).matches()) {
               userStatus.setText("please input number for Lead750");
               return check;
           }
         
          if(Lead1000Field.getText().length()>0)
           if(!pattern.matcher(Lead1000Field.getText()).matches()) {
               userStatus.setText("please input number for Lead1000");
               return check;
           }
          
           if(Copper250Field.getText().length()>0)
           if(!pattern.matcher(Copper250Field.getText()).matches()) {
               userStatus.setText("please input number for Copper250");
               return check;
           }
         
         if(Copper750Field.getText().length()>0)
           if(!pattern.matcher(Copper750Field.getText()).matches()) {
               userStatus.setText("please input number for Copper750");
               return check;
           }
         
          if(Copper1000Field.getText().length()>0)
           if(!pattern.matcher(Copper1000Field.getText()).matches()) {
               userStatus.setText("please input number for Copper1000");
               return check;
           }
          
        

      check = true;
      return check;
    }
}
