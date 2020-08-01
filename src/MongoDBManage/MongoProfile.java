/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MongoDBManage;

import DataManage.ResidentWaterData;
import ResidentFunctionManage.Profile;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Bingnan Dong
 */
public class MongoProfile {
    
    public MongoCollection createCollection(MongoDatabase mongoDatabase){
       
       MongoCollection<Document> collection = mongoDatabase.getCollection("Profile");
       //System.out.println("Create collection successfully");
       return  collection;
     }
    
    /** 
     * Insert a ResidentWaterData in DB
     *
     */
    public  void insertProfile(MongoCollection collection,String UserName , String CurrentLocation,String Hobby, String Job, String WaterChoice, String DataAgree) throws ParseException{
         Document document = new Document("UserName",UserName);
         
          document.append("CurrentLocation", CurrentLocation);
          document.append("Hobby", Hobby);
          document.append("Job", Job);
          document.append("WaterChoice",WaterChoice);
          document.append("DataAgree",DataAgree);
           List<Document> documents = new ArrayList<Document>();  
           documents.add(document);  
           collection.insertMany(documents);  
           System.out.println("Insert Profile successfully ");
          
    }
    
    public  void updatetProfile(MongoCollection collection,String UserName , String CurrentLocation,String Hobby, String Job, String WaterChoice, String DataAgree){
        // collection.updateOne(eq("UserName", UserName),combine(set("CurrentLocation" CurrentLocation), set("Hobby",  Hobby)));
        
         collection.updateOne(eq("UserName", UserName),combine(set("CurrentLocation",CurrentLocation), set("Hobby", Hobby),set("Job", Job),set("WaterChoice", WaterChoice),set("DataAgree",DataAgree)));
    }
    
     public Profile findProfile(MongoCollection collection,String username){
        Profile profile =null ;
        FindIterable<Document> findIterable  = collection.find( eq("UserName",username));
        MongoCursor<Document> mongoCursor = findIterable.iterator();
         while(mongoCursor.hasNext()){  
            Document document = mongoCursor.next();
            profile = new Profile(username,document.getString("CurrentLocation"),document.getString("Hobby"),document.getString("Job"),document.getString("WaterChoice"),document.getString("DataAgree"));
         }
        return profile;
}
}
