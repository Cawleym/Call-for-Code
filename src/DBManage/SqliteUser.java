/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManage;
import UserManage.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 *
 * @author Bingnan Dong
 */
public class SqliteUser extends SqliteDB  {
       private ArrayList<User> user = new ArrayList<>();
    
     /**
     * Create Table User
     */
    public  void createTable(Connection conn){
       String sql = "CREATE TABLE IF NOT EXISTS User " +
                   " (FirstName     CHAR(30)    NOT NULL, " + 
                   " LastName       CHAR(30)    NOT NULL, " + 
                   " Username       CHAR(30)    NOT NULL, " + 
                   " Password       CHAR(50), " + 
                   " Emailaddress   CHAR(60), " +
                   " birthDate      text, " +
                   " UserType       CHAR(20))";
       try (
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            stmt.close();
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
     /**
     * Insert a new user in DB
     * @param conn the connection of DB
     * @param user Insert a new user in DB
     */
    public  void insertTable(Connection conn,User user){
         
        try (
            Statement stmt = conn.createStatement()) {
           
           String sql = "INSERT INTO User (FirstName,LastName,Username,Password,Emailaddress,birthDate,UserType) " +
                   "VALUES (+"
                     + "'" + user.getFirstName() + "', " 
                     + "'" + user.getLastName() + "', " 
                     + "'" + user.getUsername() + "', "
                     + "'" + user.getPassword() + "', "
                     + "'" + user.getEmailaddress() + "', "
                     + "'" + user.getDateOfBirth().toString() + "', "
                     + "'" + user.getUsertype() + "'); ";
                
        //   format(DateTimeFormatter.ISO_LOCAL_DATE)
            stmt.execute(sql);
             
            stmt.close();
            System.out.println("Table inserted successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
     }
     
     /**
     * Returns all users in DB
     * @return   A Arraylist representing all users in DB
     * @param conn Sets the connection of DB
     */
     public ArrayList<User> selectTable(Connection conn)  {
           
           ArrayList<User> user = new ArrayList<>();
           try (
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery( "SELECT * FROM User;" );
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            while ( rs.next() ) {
             String  FirstName = rs.getString("FirstName");
             String  LastName = rs.getString("LastName");
             String  Username = rs.getString("Username");
             String  Password = rs.getString("Password");
             String  Emailaddress = rs.getString("Emailaddress");
             String  UserType = rs.getString("UserType");
             String  dateBirth = rs.getString("birthDate");
             
            // LocalDate.parse(dateBirth, DateTimeFormatter.ISO_DATE);
             user.add(new User(FirstName,LastName,Username,Emailaddress,Password,UserType,UserType));
           }
            rs.close();
            stmt.close();
            System.out.println("Table selected successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;  
      }
     
      /**
     * Returns whether a user existed in DB
     * @return   A Boolean representing whether a user existed in DB
     * @param conn  the connection of DB
     * @param username  the username of a given user to check
     */
     
      public  Boolean selectTableByUsername(Connection conn, String username){
           Boolean userExist = false;
           ResultSet rs =null;
           ArrayList<User> user = new ArrayList<>();
           try (
            Statement stmt = conn.createStatement()) {
             rs = stmt.executeQuery( "SELECT * FROM User where Username =" + "'" + username + "';" );
           if(rs.next()){
             userExist= true;
           }
           else{
             userExist= false;
           }
            rs.close();
            stmt.close();
          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userExist;  
      }
      
     /**
     * Returns  a user existed in DB
     * @return   A User existed in DB
     * @param conn   the connection of DB
     * @param username  the username of a given user to check
     * @param password  the password of a given user to check
     */
      public  User loginCheck(Connection conn, String username,String password) {
           User user=null;
           ResultSet rs =null;
         
           try (
            Statement stmt = conn.createStatement()) {
           
             rs = stmt.executeQuery( "SELECT * FROM User where Username =" + "'" + username + 
                                                               "' AND Password =" + "'" + password  +"';" );
             DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            if(rs.next()){
              
             String  FirstName = rs.getString("FirstName");
             String  LastName = rs.getString("LastName");
             String  Username = rs.getString("Username");
             String  Password = rs.getString("Password");
             String  Emailaddress = rs.getString("Emailaddress");
             String  dateBirth = rs.getString("birthDate");
             String  UserType = rs.getString("UserType");
             user = new User(FirstName,LastName,Username,Emailaddress,Password,UserType,UserType);   
           }
           
            rs.close();
            stmt.close();
          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;  
      }
      
      
       /**
     * Returns all patient username
     * @return   A Arraylist representing all patient username
     * @param conn  the connection of DB
     */
     public ArrayList<String> getPatientName(Connection conn)  {
           
           ArrayList<String> patientUsername = new ArrayList<>();
           try (
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery( "SELECT * FROM User where UserType =" + "'Patient';" );
            while ( rs.next() ) {
             String  patientID = rs.getString("Username");
             patientUsername.add(patientID);
           }
            rs.close();
            stmt.close();
         
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  patientUsername;  
      }
     
       /**
     * Returns all doctor username
     * @return   A Arraylist representing all patient username
     * @param conn  the connection of DB
     */
     public ArrayList<String> getDoctorName(Connection conn)  {
           
           ArrayList<String> doctorUsername = new ArrayList<>();
           try (
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery( "SELECT * FROM User where UserType =" + "'Doctor';" );
            while ( rs.next() ) {
             String  patientID = rs.getString("Username");
             doctorUsername.add(patientID);
           }
            rs.close();
            stmt.close();
         
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return doctorUsername;  
      }
   
   
}
