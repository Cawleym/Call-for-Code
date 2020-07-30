/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResidentFunctionManage;

import DataManage.OfficialData;
import MongoDBManage.MongoDB;
import MongoDBManage.MongoOfficialWaterData;
import com.mongodb.client.MongoCollection;
import flintwater.FlintWater;
import flintwater.Navigation;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class SearchOfficialDataController implements Initializable {
    @FXML private TableView<OfficialData> tableOfficialData;
    @FXML private TableColumn address;
    @FXML private TableColumn dateSubmmit;
    @FXML private TableColumn zip;
    @FXML private TableColumn Lead250;
    @FXML private TableColumn Lead750;
    @FXML private TableColumn Lead1Liter;
    @FXML private TableColumn Copper250;
    @FXML private TableColumn Copper750;
    @FXML private TableColumn Copper1Liter;
    @FXML private DatePicker DatePicker1;
    @FXML private DatePicker DatePicker2;
    @FXML private TextField addressSearch;
    @FXML private TextField zipCode;
    @FXML private Label userStatus;
    
     
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }  
       /**
     * Display user interface to login
     */
    public void handleLogout(ActionEvent event) throws IOException{
        //Navigation navigation = new  Navigation();
       // navigation.handleLogOut();
       Parent root = FXMLLoader.load(getClass().getResource("/UserManage/ResidentUI.fxml"));
       FlintWater.stage.setScene(new Scene(root));
       FlintWater.stage.setTitle("ResidentDashboard");
    }
    
       
    public void  handleSubmit(ActionEvent event) throws IOException{
         if(DatePicker1.getValue() == null){
           System.out.println("Date1 can not be null.");
            userStatus.setText("Date can not be null.");
            return;
        }
       if(DatePicker2.getValue() == null){
            System.out.println("Date2 can not be null.");
            userStatus.setText("Date can not be null.");
            return;
        }
       
      DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yyyy");
      String date1 = DatePicker1.getValue().format(formatters);
      String date2 = DatePicker2.getValue().format(formatters);
      String address1 =addressSearch.getText();
      String zip1 =zipCode.getText();
       userStatus.setText("");
    
       
      DisplayTable( date1, date2, address1, zip1);
    }
    
    public void  handleReset(ActionEvent event) throws IOException{
          DatePicker1.setValue(null);
          DatePicker2.setValue(null);
          addressSearch.setText("");
          zipCode.setText("");
    }
     public void DisplayTable(String date1, String date2, String address1,String zip1){
          ArrayList<OfficialData> officialData =  new ArrayList<>();
         //MongoDatabase conn =MongoDB.getConnection();
         MongoOfficialWaterData mongo_officialData= new MongoOfficialWaterData();
         MongoCollection<Document> collectionOfficialData =mongo_officialData.createCollection(MongoDB.mongoDatabase);
          
         try {
           officialData = mongo_officialData.findWater(collectionOfficialData,date1,date2,address1,zip1);
          
          } catch (ParseException ex) {
          // Logger.getLogger(ViewAccountController.class.getName()).log(Level.SEVERE, null, ex);
       }
        ObservableList<OfficialData> listOfficialData = FXCollections.observableArrayList(officialData);
        address.setCellValueFactory(new PropertyValueFactory<OfficialData,String >("Address"));
        dateSubmmit.setCellValueFactory(new PropertyValueFactory<OfficialData,String >("Date"));
        zip.setCellValueFactory(new PropertyValueFactory<OfficialData,String >("Zip"));
        Lead250.setCellValueFactory(new PropertyValueFactory<OfficialData,Integer >("Lead250"));
        Lead750.setCellValueFactory(new PropertyValueFactory<OfficialData,Integer >("Lead750"));
        Lead1Liter.setCellValueFactory(new PropertyValueFactory<OfficialData,Integer >("Lead1000"));
        Copper250.setCellValueFactory(new PropertyValueFactory<OfficialData,Integer >("Copper250"));
        Copper750.setCellValueFactory(new PropertyValueFactory<OfficialData,Integer >("Copper750"));
        Copper1Liter.setCellValueFactory(new PropertyValueFactory<OfficialData,Integer >("Copper1000"));
        tableOfficialData.setItems(listOfficialData);
     }
}
