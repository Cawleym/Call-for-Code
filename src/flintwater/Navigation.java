/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flintwater;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Bingnan Dong
 */
public class Navigation {
     /**
     * Display user interface to login
     */
    public void handleLogOut() throws IOException{
      
       Parent root = FXMLLoader.load(getClass().getResource("/UserManage/LoginUI.fxml"));
       FlintWater.stage.setScene(new Scene(root));
       FlintWater.stage.setTitle("Login");
    }
    
     public void  HandleOfficalData() throws IOException{
      
       Parent root = FXMLLoader.load(getClass().getResource("/ResidentFunctionManage/SearchOfficialData.fxml"));
       FlintWater.stage.setScene(new Scene(root));
       FlintWater.stage.setTitle("OfficialData");
    }
   
}
