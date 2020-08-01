/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MongoDBManage;
import UserManage.User;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.lt;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Dell
 */
public class MongoUser {
    
    public MongoCollection createCollection(MongoDatabase mongoDatabase){
         
       MongoCollection<Document> collection = mongoDatabase.getCollection("User");
       //System.out.println("Create collection successfully");
       return  collection;
     }
     
     /** 
     * Insert a new user in DB
     * @param collection the collection of DB
     * @param user Insert a new user in DB
     */
    public  void insertUser(MongoCollection collection,User user) throws ParseException{
          
         Document document = new Document("FirstName", user.getFirstName()). 
         append("LastName", user.getLastName()).
         append("Username", user.getUsername()). 
         append("Password",  user.getPassword()).
         append("Emailaddress",  user.getEmailaddress());
          
        
       /*  DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yyyy");
         String dateofbirthformat = user.getDateOfBirth().format(formatters);*/
         String dateofbirthformat = user.getDateOfBirth();
         SimpleDateFormat formatoutput = new SimpleDateFormat("MM/dd/yyyy");
         Date dateofbirth =  formatoutput.parse(dateofbirthformat);
 
         
         document.append("birthDate",dateofbirth).  
         append("UserType", user.getUsertype());
          
         List<Document> documents = new ArrayList<Document>();  
         documents.add(document);  
         collection.insertMany(documents);  
         System.out.println("Insert user successfully ");
         
    }
    
    /**
     * Returns all users in DB
     * @return   A Arraylist representing all users in DB
     * @param conn Sets the collection of DB
     */
     public ArrayList<User> findUser(MongoCollection collection) throws ParseException  {
         ArrayList<User> user = new ArrayList<>();
         FindIterable<Document> findIterable = collection.find(); 
          //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        /* SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy"); 
         Date dateofbirth1 =  format.parse("10/11/1998");
         Date dateofbirth2 =  format.parse("10/20/1998");
         FindIterable<Document> findIterable = collection.find(and(gte("birthDate", dateofbirth1), lt("birthDate",dateofbirth2))); */ 
         MongoCursor<Document> mongoCursor = findIterable.iterator();  
        
        
         while(mongoCursor.hasNext()){  
            
            Document document = mongoCursor.next();
          
             String  FirstName = document.getString("FirstName");
             String  LastName = document.getString("LastName");
             String  Username = document.getString("Username");
             String  Password = document.getString("Password");
             String  Emailaddress = document.getString("Emailaddress");
             String  UserType = document.getString("UserType");
             Date  dateBirth = (Date) document.get("birthDate");
             String sdf = new SimpleDateFormat("MM/dd/yyyy").format(dateBirth);
            
             DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            // LocalDate.parse(sdf, DateTimeFormatter.ISO_DATE);
             user.add(new User(FirstName,LastName,Username,Emailaddress,Password,sdf,UserType));
             System.out.println("aaa3:"+  sdf);
         }
         return user;
     }
     
     /**
     * Returns  a user existed in DB
     * @return   A User existed in DB
     * @param conn   the connection of DB
     * @param username  the username of a given user to check
     * @param password  the password of a given user to check
     */
      public  User loginCheck(MongoCollection collection, String username,String password) {
           User user=null;
         
         
        // collection.find(and(gte("stars", 2), lt("stars", 5), eq("categories", "Bakery")))
           FindIterable<Document> findIterable = collection.find(and(eq("Username", username),eq("Password",password)));  
           MongoCursor<Document> mongoCursor = findIterable.iterator(); 
           while(mongoCursor.hasNext()){ 
             Document document = mongoCursor.next();
             String  FirstName = document.getString("FirstName");
             String  LastName = document.getString("LastName");
             String  Username = document.getString("Username");
             String  Password = document.getString("Password");
             String  Emailaddress = document.getString("Emailaddress");
             String  UserType = document.getString("UserType");
             Date  dateBirth = (Date) document.get("birthDate");
             String sdf = new SimpleDateFormat("MM/dd/yyyy").format(dateBirth);
             //DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
             user = new User(FirstName,LastName,Username,Emailaddress,Password,sdf,UserType);   
           
            }
            
        return user;  
      }
      
      /**
     * Returns whether a user existed in DB
     * @return   A Boolean representing whether a user existed in DB
     * @param conn  the connection of DB
     * @param username  the username of a given user to check
     */
     
      public  Boolean checkUsername(MongoCollection collection, String username){
           Boolean userExist = false;
          
           ArrayList<User> user = new ArrayList<>();
           FindIterable<Document> findIterable = collection.find(eq("Username", username));
           MongoCursor<Document> mongoCursor = findIterable.iterator(); 
           if (mongoCursor.hasNext()){ 
             userExist= true;
           }
           else{
             userExist= false;
           }
       
           return userExist;  
      }
      
       public  void  deleteUser(MongoCollection collection, String username){
           collection.deleteOne(Filters.eq("Username",username));
       }
       
      
}