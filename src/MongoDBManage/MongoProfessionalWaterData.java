/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MongoDBManage;

import DataManage.OfficialData;
import DataManage.ProfessionalWaterData;
import UserManage.User;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lt;
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
public class MongoProfessionalWaterData {
     public MongoCollection createCollection(MongoDatabase mongoDatabase){
          // 连接到集合
       MongoCollection<Document> collection = mongoDatabase.getCollection("ProfessionalWaterData");
       //System.out.println("Create collection ProfessionalWaterData successfully");
       return  collection;
     }
 
     /** 
     * Insert a new user in DB
     * @param collection the collection of DB
     * @param user Insert a new user in DB
     */
    /*public  void insertPrefessionalWaterData(MongoCollection collection,ProfessionalWaterData prefessionalWaterData) throws ParseException{
          
         Document document = new Document("username", prefessionalWaterData.getUsername());
        
          
         String dateoftestformat = prefessionalWaterData.getTestDate();
         SimpleDateFormat formatoutput = new SimpleDateFormat("MM/dd/yyyy");
         Date dateoftest =  formatoutput.parse(dateoftestformat);
 
         document.append("TestDate",dateoftest).  
         append("StreetName", prefessionalWaterData.getStreetName()).
         append("CityName", prefessionalWaterData.getCityName()).
         append("ZipCode", prefessionalWaterData.getZipCode()). 
         append("Lead2k250",  prefessionalWaterData.getLead2k250()).
         append("Lead2k750",  prefessionalWaterData.getLead2k750()).
         append("Lead2k1000",  prefessionalWaterData.getLead2k1000()).
         append("Lead1k250",  prefessionalWaterData.getLead1k250()).
         append("Lead1k750",  prefessionalWaterData.getLead1k750()).
         append("Lead1k1000",  prefessionalWaterData.getLead1k1000()).
         append("Copper2k250",  prefessionalWaterData.getCopper2k250()).
         append("Copper2k750",  prefessionalWaterData.getCopper2k750()).
         append("Copper2k1000",  prefessionalWaterData.getCopper2k1000()).
         append("Copper1k250",  prefessionalWaterData.getCopper1k250()).
         append("Copper1k750",  prefessionalWaterData.getCopper1k750()).
         append("Copper1k1000",  prefessionalWaterData.getCopper1k1000());
          
         List<Document> documents = new ArrayList<Document>();  
         documents.add(document);  
         collection.insertMany(documents);  
         System.out.println("Insert ProfessionalWaterData successfully ");
         
    }*/
   /** 
     * Insert a PrefessionalWaterData in DB
     * 
     */
    public  void insertPrefessionalWaterData(MongoCollection collection,String username ,String dateOftest,String STName,String STNum, String City,String Zip,
                 String Lead2BK250,String Lead2BK750,String Lead2BK1000,String Lead1BK250,String Lead1BK750,String Lead1BK1000,
                 String Copper2BK250,String Copper2BK750,String Copper2BK1000,String Copper1BK250,String Copper1BK750,String Copper1BK1000) throws ParseException{
     
         Document document = new Document("username", username);
         SimpleDateFormat formatoutput = new SimpleDateFormat("MM/dd/yyyy");
         Date TestDate =  formatoutput.parse(dateOftest);
 
         document.append("TestDate",TestDate).
         append("StreetName", STName).
         append("StreetNum", STNum).
         append("CityName", City).
         append("ZipCode", Zip);
        
         if(Lead2BK250.length()>0)
                document.append("Lead2BK250",Integer.parseInt(Lead2BK250)) ;
         else
                document.append("Lead2BK250", null) ;
         if(Lead2BK750.length()>0)
                document.append("Lead2BK750",Integer.parseInt(Lead2BK750)) ;
         else
                document.append("Lead2BK750",null) ;
          if(Lead2BK1000.length()>0)
                document.append("Lead2BK1000",Integer.parseInt(Lead2BK1000)) ;
         else
                document.append("Lead2BK1000",null) ;
          if(Lead1BK250.length()>0)
                document.append("Lead1BK250",Integer.parseInt(Lead1BK250)) ;
         else
                document.append("Lead1BK250",null) ;
         if(Lead1BK750.length()>0)
                document.append("Lead1BK750",Integer.parseInt(Lead1BK750)) ;
         else
                document.append("Lead1BK750",null) ;
          if(Lead1BK1000.length()>0)
                document.append("Lead1BK1000",Integer.parseInt(Lead1BK1000)) ;
         else
                document.append("Lead1BK1000",null) ;
        if(Copper2BK250.length()>0)
                document.append("Copper2BK250",Integer.parseInt(Copper2BK250)) ;
         else
                document.append("Copper2BK250",null) ;
         if(Copper2BK750.length()>0)
                document.append("Copper2BK750",Integer.parseInt(Copper2BK750)) ;
         else
                document.append("Copper2BK750",null) ;
          if(Copper2BK1000.length()>0)
                document.append("Copper2BK1000",Integer.parseInt(Copper2BK1000)) ;
         else
                document.append("Copper2BK1000",null) ;
          if(Copper1BK250.length()>0)
                document.append("Copper1BK250",Integer.parseInt(Copper1BK250)) ;
         else
                document.append("Copper1BK250",null) ;
         if(Copper1BK750.length()>0)
                document.append("Copper1BK750",Integer.parseInt(Copper1BK750)) ;
         else
                document.append("Copper1BK750",null) ;
          if(Copper1BK1000.length()>0)
                document.append("Copper1BK1000",Integer.parseInt(Copper1BK1000)) ;
         else
                document.append("Lead1BK1000",null) ;
         
         List<Document> documents = new ArrayList<Document>();  
         documents.add(document);  
         collection.insertMany(documents);  
         System.out.println("Insert ProfessionalWaterData successfully ");
    }
    
    public ArrayList<ProfessionalWaterData> findProfessionalWater(MongoCollection collection,String username){
        ArrayList<ProfessionalWaterData> professionalWaterData = new ArrayList<>();
     
    
     //BasicDBObject queryObject = new BasicDBObject("_id",new ObjectId("5f213e1c5001b82858aae947"));
    // FindIterable<Document> findIterable  = collection.find( queryObject);
        FindIterable<Document> findIterable  = collection.find( eq("username",username));
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while(mongoCursor.hasNext()){  
            Document document = mongoCursor.next();
        
             String  StreetName = document.getString("StreetName");
             String  StreetNum = document.getString("StreetNum");
             String   CityName = document.getString("CityName");
             String   ZipCode = document.getString("ZipCode");
             
            
             Date  DateSubmitted =  document.getDate("TestDate");
             String sdf = new SimpleDateFormat("MM/dd/yyyy").format( DateSubmitted); 
             String id = document.getObjectId("_id").toString();
             System.out.println("id:"+ document.getObjectId("_id").toString());
            
             Integer Lead2BK250 = document.getInteger("Lead2BK250");
             Integer Lead2BK750 = document.getInteger("Lead2BK750");
             Integer Lead2BK1000 = document.getInteger("Lead2BK1000");
             Integer Lead1BK250 = document.getInteger("Lead1BK250");
             Integer Lead1BK750 = document.getInteger("Lead1BK750");
             Integer Lead1BK1000 = document.getInteger("Lead1BK1000");
             Integer Copper2BK250 = document.getInteger("Copper2BK250");
             Integer Copper2BK750 = document.getInteger("Copper2BK750");
             Integer Copper2BK1000 = document.getInteger("Copper2BK1000");
             Integer Copper1BK250 = document.getInteger("Copper1BK250");
             Integer Copper1BK750 = document.getInteger("Copper1BK750");
             Integer Copper1BK1000 = document.getInteger("Copper1BK1000");
             professionalWaterData.add(new ProfessionalWaterData(id,username,sdf,Lead2BK250,Lead2BK750,Lead2BK1000,Lead1BK250,Lead1BK750,Lead1BK1000,
             Copper2BK250,Copper2BK750,Copper2BK1000,Copper1BK250,Copper1BK750,Copper1BK1000,StreetNum,StreetName,CityName,ZipCode));

         }
        
        return professionalWaterData;
    }
     
}
