/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProfessionalFunctionManage;

import DataManage.OfficialData;
import DataManage.ProfessionalWaterData;
import MongoDBManage.MongoDB;
import MongoDBManage.MongoProfessionalWaterData;
import UserManage.User;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
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
public class ViewOWDataController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static User loginUser;
    @FXML private Label userStatus;
    @FXML private TableView<ProfessionalWaterData> UserLeadTable;
    @FXML private TableView<ProfessionalWaterData> UserCopperTable;
    @FXML private TableColumn LeadDateCol;
    @FXML private TableColumn Lead2BK250Col;
    @FXML private TableColumn Lead2BK750Col;
    @FXML private TableColumn Lead2BK1000Col;
    @FXML private TableColumn Lead1BK250Col;
    @FXML private TableColumn Lead1BK750Col;
    @FXML private TableColumn Lead1BK1000Col;
    @FXML private TableColumn CopperDateCol;
    @FXML private TableColumn Copper2BK250Col;
    @FXML private TableColumn Copper2BK750Col;
    @FXML private TableColumn Copper2BK1000Col;
    @FXML private TableColumn Copper1BK250Col;
    @FXML private TableColumn Copper1BK750Col;
    @FXML private TableColumn Copper1BK1000Col;
    
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
      Parent root = FXMLLoader.load(getClass().getResource("/UserManage/ProfessionalUI.fxml"));
       FlintWater.stage.setScene(new Scene(root));
       FlintWater.stage.setTitle("ProfessionaDashboard");
    }
     public void  handleEdit(ActionEvent event) throws IOException{
        ProfessionalWaterData professionalWaterData = UserLeadTable.getSelectionModel().getSelectedItem();
        ProfessionalWaterData professionalWaterData2 = UserCopperTable.getSelectionModel().getSelectedItem();
        if (professionalWaterData == null && professionalWaterData2 == null){
           userStatus.setText("Please select the edit data in the table");
            return;
         }
       EditOWDataController editOWDataController  = new EditOWDataController();
       editOWDataController.userSet(loginUser);
       if (professionalWaterData !=null)
         editOWDataController.professionalWaterDataSet(professionalWaterData);
       else
           editOWDataController.professionalWaterDataSet(professionalWaterData2);
       
    
       Parent root = FXMLLoader.load(getClass().getResource("EditOWData.fxml"));
       FlintWater.stage.setScene(new Scene(root));
       FlintWater.stage.setTitle("Professional-Edit ");
    }
    public void  handleDelete(ActionEvent event) throws IOException{
        ProfessionalWaterData professionalWaterData = UserLeadTable.getSelectionModel().getSelectedItem();
        ProfessionalWaterData professionalWaterData2 = UserCopperTable.getSelectionModel().getSelectedItem();
        if (professionalWaterData == null && professionalWaterData2 == null){
           userStatus.setText("Please select the dropped data in the table");
            return;
         }
        BasicDBObject queryObject;
        if (professionalWaterData !=null)
           queryObject = new BasicDBObject("_id",new ObjectId(professionalWaterData.getId().toString()));
        else 
            queryObject = new BasicDBObject("_id",new ObjectId(professionalWaterData2.getId().toString()));
        
         MongoProfessionalWaterData mongo_professionalWaterData= new MongoProfessionalWaterData();
         MongoCollection<Document> collectionProfessionalWaterData =mongo_professionalWaterData.createCollection(MongoDB.mongoDatabase);
         collectionProfessionalWaterData.deleteOne(queryObject);
         displaytable();
         userStatus.setText("");
    
    }
    
     public void displaytable(){
          ArrayList<ProfessionalWaterData> professionalWaterData =  new ArrayList<>();
         //MongoDatabase conn =MongoDB.getConnection();
        MongoProfessionalWaterData mongo_professionalData= new MongoProfessionalWaterData();
        MongoCollection<Document> collectionProfessionalData =mongo_professionalData.createCollection(MongoDB.mongoDatabase);
        professionalWaterData = mongo_professionalData.findProfessionalWater(collectionProfessionalData, loginUser.getUsername());
        ObservableList<ProfessionalWaterData> listProfessionalWaterData = FXCollections.observableArrayList(professionalWaterData);
        
        LeadDateCol.setCellValueFactory(new PropertyValueFactory<ProfessionalWaterData,String >("TestDate"));
        Lead2BK250Col.setCellValueFactory(new PropertyValueFactory<ProfessionalWaterData,Integer >("Lead2k250"));
        Lead2BK750Col.setCellValueFactory(new PropertyValueFactory<ProfessionalWaterData,Integer >("Lead2k750"));
        Lead2BK1000Col.setCellValueFactory(new PropertyValueFactory<ProfessionalWaterData,Integer >("Lead2k1000"));
        Lead1BK250Col.setCellValueFactory(new PropertyValueFactory<ProfessionalWaterData,Integer >("Lead1k250"));
        Lead1BK750Col.setCellValueFactory(new PropertyValueFactory<ProfessionalWaterData,Integer >("Lead1k750"));
        Lead1BK1000Col.setCellValueFactory(new PropertyValueFactory<ProfessionalWaterData,Integer >("Lead1k1000"));
        UserLeadTable.setItems(listProfessionalWaterData);
        
        CopperDateCol.setCellValueFactory(new PropertyValueFactory<ProfessionalWaterData,String >("TestDate"));
        Copper2BK250Col.setCellValueFactory(new PropertyValueFactory<ProfessionalWaterData,Integer >("Copper2k250"));
        Copper2BK750Col.setCellValueFactory(new PropertyValueFactory<ProfessionalWaterData,Integer >("Copper2k750"));
        Copper2BK1000Col.setCellValueFactory(new PropertyValueFactory<ProfessionalWaterData,Integer >("Copper2k1000"));
        Copper1BK250Col.setCellValueFactory(new PropertyValueFactory<ProfessionalWaterData,Integer >("Copper1k250"));
        Copper1BK750Col.setCellValueFactory(new PropertyValueFactory<ProfessionalWaterData,Integer >("Copper1k750"));
        Copper1BK1000Col.setCellValueFactory(new PropertyValueFactory<ProfessionalWaterData,Integer >("Copper1k1000"));
        UserCopperTable.setItems(listProfessionalWaterData);
     }
    
}
