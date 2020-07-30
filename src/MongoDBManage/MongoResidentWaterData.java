/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MongoDBManage;

import DataManage.ResidentWaterData;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
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
 * @author BinnanDong
 */
public class MongoResidentWaterData {
      public MongoCollection createCollection(MongoDatabase mongoDatabase){
          
       MongoCollection<Document> collection = mongoDatabase.getCollection("ResidentData");
       System.out.println("Create collection ResidentData successfully");
       return  collection;
     }
      /** 
     * Insert a ResidentWaterData in DB
     *
     */
    public  void insertResidentWaterData(MongoCollection collection,String username ,String dateOftest,String STName,String Zip,
                 String Lead250,String Lead750,String Lead1000,String Copper250,String Copper750,String Copper1000) throws ParseException{
         Document document = new Document("username", username);
         SimpleDateFormat formatoutput = new SimpleDateFormat("MM/dd/yyyy");
         Date TestDate =  formatoutput.parse(dateOftest);
 
         document.append("TestDate",TestDate).
         append("StreetName", STName).
         append("ZipCode", Zip);
         
         if(Lead250.length()>0)
                document.append("Lead250",Integer.parseInt(Lead250)) ;
         else
                document.append("Lead250", null) ;
         if(Lead750.length()>0)
                document.append("Lead750",Integer.parseInt(Lead750)) ;
         else
                document.append("Lead750", null) ;
         if(Lead1000.length()>0)
                document.append("Lead1000",Integer.parseInt(Lead1000)) ;
         else
                document.append("Lead1000", null) ;
         
         if(Copper250.length()>0)
                document.append("Copper250",Integer.parseInt(Copper250)) ;
         else
                document.append("Copper250", null) ;
         if(Copper750.length()>0)
                document.append("Copper750",Integer.parseInt(Copper750)) ;
         else
                document.append("Copper750", null) ;
         if(Copper1000.length()>0)
                document.append("Copper1000",Integer.parseInt(Copper1000)) ;
         else
                document.append("Copper1000", null) ;
         List<Document> documents = new ArrayList<Document>();  
         documents.add(document);  
         collection.insertMany(documents);  
         System.out.println("Insert ResidentWaterData successfully ");
    }
    
     /** 
     * update a ResidentWaterData in DB
     *
     */
    public void updateResidentWaterData(MongoCollection collection,String id, String username ,String dateOftest,String STName,String Zip,
                 String Lead250,String Lead750,String Lead1000,String Copper250,String Copper750,String Copper1000) throws ParseException{
        
         
         collection.updateOne(eq("_id", new ObjectId(id)),combine(set("StreetName",STName), set("StreetNum", Zip)));
         
         SimpleDateFormat formatoutput = new SimpleDateFormat("MM/dd/yyyy");
         Date TestDate =  formatoutput.parse(dateOftest);
         collection.updateOne(eq("_id", new ObjectId(id)),set("TestDate", TestDate));
         
         if (Lead250.length()==0)
            collection.updateOne(eq("_id", new ObjectId(id)), set("Lead250", null));
          else    
            collection.updateOne(eq("_id", new ObjectId(id)), set("Lead250",Integer.parseInt(Lead250)));
         
         if (Lead750.length()==0)
            collection.updateOne(eq("_id", new ObjectId(id)), set("Lead750", null));
          else    
            collection.updateOne(eq("_id", new ObjectId(id)), set("Lead750",Integer.parseInt(Lead750)));
         
         if (Lead1000.length()==0)
            collection.updateOne(eq("_id", new ObjectId(id)), set("Lead1000", null));
          else    
            collection.updateOne(eq("_id", new ObjectId(id)), set("Lead1000",Integer.parseInt(Lead1000)));
         
         if (Copper250.length()==0)
            collection.updateOne(eq("_id", new ObjectId(id)), set("Copper250", null));
          else    
            collection.updateOne(eq("_id", new ObjectId(id)), set("Copper250",Integer.parseInt(Copper250)));
         
         if (Copper750.length()==0)
            collection.updateOne(eq("_id", new ObjectId(id)), set("Copper750", null));
          else    
            collection.updateOne(eq("_id", new ObjectId(id)), set("Copper750",Integer.parseInt(Copper750)));
         
         if (Copper1000.length()==0)
            collection.updateOne(eq("_id", new ObjectId(id)), set("Copper1000", null));
         else    
            collection.updateOne(eq("_id", new ObjectId(id)), set("Copper1000",Integer.parseInt(Copper1000)));
              
              
         
    }
    public ArrayList<ResidentWaterData> findResidentWater(MongoCollection collection,String username){
        ArrayList<ResidentWaterData> residentWaterData = new ArrayList<>();
        FindIterable<Document> findIterable  = collection.find( eq("username",username));
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        
         while(mongoCursor.hasNext()){  
            Document document = mongoCursor.next();
            
             String id = document.getObjectId("_id").toString();
             Date  DateSubmitted =  document.getDate("TestDate");
             String sdf = new SimpleDateFormat("MM/dd/yyyy").format( DateSubmitted); 
             String  StreetName = document.getString("StreetName");
             String   ZipCode = document.getString("ZipCode");
             Integer Lead250 = document.getInteger("Lead250");
             Integer Lead750 = document.getInteger("Lead750");
             Integer Lead1000 = document.getInteger("Lead1000");
             Integer Copper250 = document.getInteger("Copper250");
             Integer Copper750 = document.getInteger("Copper750");
             Integer Copper1000 = document.getInteger("Copper1000");
             residentWaterData .add(new  ResidentWaterData(id ,username,sdf,StreetName,ZipCode, Lead250,Lead750,Lead1000, Copper250,Copper750, Copper1000)); 
         }
        
        return residentWaterData;
     }
    
     public  int  countUser(MongoCollection collection, String username){
           int count = 0;
           //collection.deleteOne(Filters.eq("username",username));
           FindIterable<Document> findIterable = collection.find(eq("username", username));
           MongoCursor<Document> mongoCursor = findIterable.iterator();
           while(mongoCursor.hasNext()){ 
               mongoCursor.next();
               count = count + 1;
               System.out.println("count:"+ count);
           }
          return count;
       }
}
