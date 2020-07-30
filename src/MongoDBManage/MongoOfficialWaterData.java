/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MongoDBManage;

import DataManage.OfficialData;
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
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author Bingnan Dong
 */
public class MongoOfficialWaterData {
    public MongoCollection createCollection(MongoDatabase mongoDatabase){
          // 连接到集合
       MongoCollection<Document> collection = mongoDatabase.getCollection("OfficialWaterData");
       System.out.println("Create collection OfficialWaterData successfully");
       return  collection;
     }
    
        /**
     * Returns all water data in DB
     * @return   A Arraylist representing all water data in DB
     * @param collection Sets the collection of DB
     */
     public ArrayList<OfficialData> findWater(MongoCollection collection,String date1, String date2,String address1,String zip1) throws ParseException  {
         ArrayList<OfficialData> officialData = new ArrayList<>();
       //  FindIterable<Document> findIterable = collection.find(); 
        
         // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
         SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); 
         Date dateSearch1 =  format.parse(date1);
         Date dateSearch2 =  format.parse(date2);
         // Date dateSearch1 =  format.parse("1/1/2020");
        // Date dateSearch2 =  format.parse("3/1/2020");
          FindIterable<Document> findIterable  = collection.find(and(gte("Date",dateSearch1), lt("Date",dateSearch2)));
         
          if(address1.length() ==0 && zip1.length() ==0)
             findIterable = collection.find(and(gte("Date",dateSearch1), lt("Date",dateSearch2)));   
          else
             if(address1.length() ==0 && zip1.length()> 0)
              findIterable = collection.find(and(gte("Date",dateSearch1), lt("Date",dateSearch2),eq("Zip",zip1)));  
           else
             if(address1.length() > 0 && zip1.length()== 0)
               findIterable = collection.find(and(gte("Date",dateSearch1), lt("Date",dateSearch2),eq("Address",address1)));
             else
               if(address1.length() > 0 && zip1.length()> 0)
                  findIterable = collection.find(and(gte("Date",dateSearch1), lt("Date",dateSearch2),eq("Address",address1),eq("Zip",zip1)));
      // FindIterable<Document> findIterable = collection.find(eq("Zip","48504"));
         // FindIterable<Document> findIterable = collection.find(and(gte("Date", date1), lt("Date",date2),eq("Zip","48504"),eq("Address","WOLCOTT ST")));
       //  FindIterable<Document> findIterable = collection.find(and(gte("birthDate", dateofbirth1), lt("birthDate",dateofbirth2)));  
       
         MongoCursor<Document> mongoCursor = findIterable.iterator();  
  
          System.out.println( "Information tt ");
          int count =0;
         while(mongoCursor.hasNext()){  
            Document document = mongoCursor.next();
        
             String  Address = document.getString("Address");
           
             Date  DateSubmitted =  document.getDate("Date");
             String  Zip = document.getString("Zip");
             Integer Leadppb250ml = document.getInteger("Leadppb250ml");
             Integer Leadppb750ml = document.getInteger("Leadppb750ml");
             Integer Leadppb1liter = document.getInteger("Leadppb1liter");
             Integer Copperppb250ml = document.getInteger("Copperppb250ml");
             Integer Copperppb750ml = document.getInteger("Copperppb750ml");
             Integer Copperppb1liter = document.getInteger("Copperppb1liter");
            
             count =count +1;

             System.out.println( "Information " + count);
        
             System.out.println( "Address: " + Address );
              System.out.println( "Date: " + DateSubmitted );
              String sdf = new SimpleDateFormat("MM/dd/yyyy").format( DateSubmitted);
              System.out.println( "Date: " + sdf);
             
              System.out.println( "Zip: " +Zip );
              System.out.println( "Leadppb250ml: " + Leadppb250ml);
              System.out.println( "Leadppb750ml: " + Leadppb750ml );
              System.out.println( "Leadppb1liter: " + Leadppb1liter);
              System.out.println( "Copperppb250ml: " + Copperppb250ml);
              System.out.println( "Copperppb750ml: " + Copperppb750ml );
              System.out.println( "Copperppb1liter: " + Copperppb1liter);
             ;
              System.out.println( " ");
              //  public OfficialData(String Address, String Date, String Zip,  Integer Lead250,Integer Lead750,Integer Lead1000,Integer Copper250,Integer Copper750,Integer Copper1000  ){

            officialData.add(new OfficialData(Address,sdf,Zip,Leadppb250ml,Leadppb750ml,Leadppb1liter,Copperppb250ml,Copperppb750ml,Copperppb1liter));
            
         }
         return officialData;
     }
     
}
