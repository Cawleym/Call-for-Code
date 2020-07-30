/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MongoDBManage;
import java.util.ArrayList;  
import java.util.List;  
//import com.mongodb.MongoClient;  
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.MongoCredential;  
import com.mongodb.ServerAddress;  
import com.mongodb.client.MongoDatabase;  
/**
 *
 * @author Bingnan Dong
 */

public class MongoDB {
    private static String databaseName = "test";
    public static MongoDatabase mongoDatabase;
   
    
    public static MongoDatabase getConnection()
    {
         
        // connect to mongodb service
    //    MongoClient mongoClient = MongoClients.create();
      MongoClient mongoClient = MongoClients.create("mongodb+srv://cdhdnsdbn:Jane621027@cluster0.lwtzc.mongodb.net/test?retryWrites=true&w=majority");
      
       // connect to database
       mongoDatabase = mongoClient.getDatabase(databaseName);  
       System.out.println("Connect to database successfully");
       return mongoDatabase;
    }
}
