/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MongoDBManage;
import com.mongodb.ConnectionString;
import java.util.ArrayList;  
import java.util.List;  
//import com.mongodb.MongoClient;  
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.MongoCredential;  
import com.mongodb.ServerAddress;  
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientSettings;
/**
 *
 * @author Bingnan Dong
 */

public class MongoDB {
    private static String databaseName = "test";
    public static MongoDatabase mongoDatabase;
   
    
    public static MongoDatabase getConnection()
    {
         
    
     
        //MongoClient mongoClient = MongoClients.create("mongodb+srv://xxxxxx:xxxxxx@cluster0.lwtzc.mongodb.net/test?retryWrites=true&w=majority");
    
       MongoClient mongoClient = MongoClients.create("");
       //For security reasons, if you need to connect to the database, please contact us. Also you need to have an account for IBM cloud.

       mongoDatabase = mongoClient.getDatabase(databaseName);  
       //System.out.println("Connect to database successfully");
       return mongoDatabase;
    }
}
