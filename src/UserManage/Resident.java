/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManage;



import DataManage.ResidentWaterData;
import ResidentFunctionManage.Profile;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Bingnan Dong
 */
public class Resident extends User{
 
    
    private ArrayList<ResidentWaterData> UWData;
    
    private ArrayList<Profile> profile;
 
    
    
    /**
     * This is the constructor for the Resident class
     * @param firstName Set first name for the Resident
     * @param lastName  Set last name for the Resident
     * @param username  set username for the Resident
     * @param emailaddress set email address for the Resident
     * @param  password set  password for thePatient
     * @param dateBirth set the birth date for the Resident
     * @param  type set type as "Resident"
   */
    
    
    public Resident(String firstName, String lastName, String username, String emailaddress, String password, String dateBirth,String type){
        super(firstName, lastName, username, emailaddress, password, dateBirth,type  );
    }
    
      /**
     * Returns a UserWaterData list of a Resident
     * @return a UserWaterData list of a Resident
     */
    
   public ArrayList<ResidentWaterData> getUWData(String username){
        return UWData;
    }
   
      /**
     * Returns a Profile list of a Resident
     * @return a Profile list of a Resident
     */
    
   public ArrayList<Profile> getProfileData(String username){
        return profile;
    }
   
   

   
    /**
     * Sets a UserWaterData list of a Resident
     * @param UWData a UserWaterData list of a Resident 
     */
    
   public void setUserWaterData(ArrayList<ResidentWaterData> UWData)
   {
       this.UWData = UWData;
   }
   
    /**
     * Sets a Profile list of a Resident
     * @param profile a Profile list of a Resident 
     */
    
   public void setProfile(ArrayList<Profile> profile)
   {
       this.profile = profile;
   }
    
    
    
}
