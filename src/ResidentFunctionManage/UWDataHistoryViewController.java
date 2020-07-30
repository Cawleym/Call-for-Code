/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResidentFunctionManage;

import DataManage.ProfessionalWaterData;
import DataManage.ResidentWaterData;
import MongoDBManage.MongoDB;
import MongoDBManage.MongoResidentWaterData;
import UserManage.User;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import flintwater.FlintWater;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * FXML Controller class
 *
 * @author Bingnan Dong
 */
public class UWDataHistoryViewController implements Initializable {
    private static User loginUser;
    @FXML private Label userStatus;
    @FXML private TableView<ResidentWaterData> UserLeadTable;
    @FXML private TableView<ResidentWaterData> UserCopperTable;
    @FXML private TableColumn LeadDateCol;
    @FXML private TableColumn LeadSNameCol;
    @FXML private TableColumn LeadZipCol;
    @FXML private TableColumn Lead250Col;
    @FXML private TableColumn Lead750Col;
    @FXML private TableColumn Lead1000Col;
    @FXML private TableColumn CopperDateCol;
    @FXML private TableColumn CopperSNameCol;
    @FXML private TableColumn CopperZipCol;
    @FXML private TableColumn Copper250Col;
    @FXML private TableColumn Copper750Col;
    @FXML private TableColumn Copper1000Col;
            ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displaytable();
    }    
    
     /**
     * Set the login user
     */
    public void userSet(User user){
        this.loginUser =user;
    }
    public void  handleBack(ActionEvent event) throws IOException{
      Parent root = FXMLLoader.load(getClass().getResource("/UserManage/ResidentUI.fxml"));
      FlintWater.stage.setScene(new Scene(root));
      FlintWater.stage.setTitle("ResidentDashboard");
       
    }
     public void  handleEdit(ActionEvent event) throws IOException{
        ResidentWaterData residentWaterData = UserLeadTable.getSelectionModel().getSelectedItem();
        ResidentWaterData residentWaterData2 = UserCopperTable.getSelectionModel().getSelectedItem();
        if (residentWaterData == null && residentWaterData2 == null){
           userStatus.setText("Please select the edit data in the table");
            return;
         }
       EditUWDataController editUWDataController  = new EditUWDataController();
       editUWDataController.userSet(loginUser);
       if (residentWaterData !=null)
         editUWDataController.residentWaterDataSet(residentWaterData);
       else
           
           editUWDataController.residentWaterDataSet(residentWaterData2);
       
       Parent root = FXMLLoader.load(getClass().getResource("EditUWData.fxml"));
       FlintWater.stage.setScene(new Scene(root));
       FlintWater.stage.setTitle("Resident-Edit ");
     }
     public void  handleDelete(ActionEvent event) throws IOException{
        ResidentWaterData residentWaterData = UserLeadTable.getSelectionModel().getSelectedItem();
        ResidentWaterData residentWaterData2 = UserCopperTable.getSelectionModel().getSelectedItem();
        if (residentWaterData == null && residentWaterData2 == null){
           userStatus.setText("Please select the drop data in the table");
            return;
         }
        BasicDBObject queryObject;
       if (residentWaterData !=null)
            queryObject = new BasicDBObject("_id",new ObjectId(residentWaterData.getId().toString()));
       else
             queryObject = new BasicDBObject("_id",new ObjectId(residentWaterData2.getId().toString()));
       
         MongoResidentWaterData mongo_residentWaterData= new MongoResidentWaterData();
         MongoCollection<Document> collectionResidentWaterData =mongo_residentWaterData.createCollection(MongoDB.mongoDatabase);
         collectionResidentWaterData.deleteOne(queryObject);
         displaytable();
         userStatus.setText("");
     }
     public void displaytable(){
        ArrayList<ResidentWaterData> residentWaterData =  new ArrayList<>();
        MongoResidentWaterData mongo_residentData= new MongoResidentWaterData();
        MongoCollection<Document> collectionResidentData =mongo_residentData.createCollection(MongoDB.mongoDatabase);
        residentWaterData = mongo_residentData.findResidentWater(collectionResidentData, loginUser.getUsername());
        ObservableList<ResidentWaterData> listResidentWaterData = FXCollections.observableArrayList(residentWaterData);
        
        LeadDateCol.setCellValueFactory(new PropertyValueFactory<ResidentWaterData,String >("TestDate"));
        LeadSNameCol.setCellValueFactory(new PropertyValueFactory<ResidentWaterData,String >("StreetName"));
        LeadZipCol.setCellValueFactory(new PropertyValueFactory<ResidentWaterData,String >("ZipCode"));
        Lead250Col.setCellValueFactory(new PropertyValueFactory<ResidentWaterData,Integer >("Lead250"));
        Lead750Col.setCellValueFactory(new PropertyValueFactory<ResidentWaterData,Integer >("Lead750"));
        Lead1000Col.setCellValueFactory(new PropertyValueFactory<ResidentWaterData,Integer >("Lead1000"));
        UserLeadTable.setItems(listResidentWaterData);
        
        CopperDateCol.setCellValueFactory(new PropertyValueFactory<ResidentWaterData,String >("TestDate"));
        CopperSNameCol.setCellValueFactory(new PropertyValueFactory<ResidentWaterData,String >("StreetName"));
        CopperZipCol.setCellValueFactory(new PropertyValueFactory<ResidentWaterData,String >("ZipCode"));
        Copper250Col.setCellValueFactory(new PropertyValueFactory<ResidentWaterData,Integer >("Copper250"));
        Copper750Col.setCellValueFactory(new PropertyValueFactory<ResidentWaterData,Integer >("Copper750"));
        Copper1000Col.setCellValueFactory(new PropertyValueFactory<ResidentWaterData,Integer >("Copper1000"));
        UserCopperTable.setItems(listResidentWaterData);
     }
    
}
