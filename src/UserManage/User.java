/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManage;

/**
 *
 * @author Bingnan Dong
 */
import java.time.LocalDate;
import java.util.Date;


/**
 * An Abstract class that represents a login user. 
 * @author Bingnan Dong
 */
public  class User {

    private String firstName;
    private String lastName;
    private String username;
    private String emailaddress;
    private String password;
    private String   dateOfBirth;
    private String usertype; //Resident or Administrator
    
   /**
     * This is the constructor for the User class
     * @param firstName Set first name for the user
     * @param lastName  Set last name for the user
     * @param username  set username for the user
     * @param emailaddress set email address for the user 
     * @param  password set  password for the user
     * @param  type set type for the user
   */
   public User(String firstName, String lastName, String username, String emailaddress, String password, String dateOfBirth,String type  ){
          this.firstName =firstName;
          this.lastName = lastName;
          this.username = username;
          this.emailaddress = emailaddress;
          this.password = password;
          this.dateOfBirth = dateOfBirth;
          this.usertype = type;
    }
   
    

    public String getFirstName() {
        return firstName;
    }

 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    public String getLastName() {
        return lastName;
    }

   
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
   
    public String getUsername() {
        return username;
    }

 
    public void setUsername(String userName) {
        this.username =username;
    }
    
    
   
    public String getPassword() {
        return password;
    }

  
    public void setPassword(String password) {
        this.password =password;
    }
    
    
    
     public String getFullName(){
        
        return firstName+ " " + lastName;
    }
     
    
    public String getUsertype() {
        return usertype;
    }

    
    public void setUserType(String usertype) {
        this.usertype = usertype;
    }

   
    public String getEmailaddress() {
        return emailaddress;
    }

  
    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }
    
     
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

