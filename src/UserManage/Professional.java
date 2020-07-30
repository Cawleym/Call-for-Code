/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManage;
import DataManage.ProfessionalWaterData;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author Bingnan Dong
 */
public class Professional extends User{
     
     private ArrayList<ProfessionalWaterData> OWData;
        
    /**
     * This is the constructor for the Resident class
     * @param firstName Set institution name for this organization
     * @param lastName  Set trade name for this organization
     * @param username  set username for this organization
     * @param emailaddress set email address for this organization
     * @param  password set  password for this account
     * @param dateBirth set the date of establishment for this organization
     * @param  type set type as "professional"
   */
    
    
    public Professional(String firstName, String lastName, String username, String emailaddress, String password, String dateBirth,String type){
        super(firstName, lastName, username, emailaddress, password, dateBirth,type  );
    }
    
          /**
     * Return an OffcialWaterData list of an organization
     * @return an OffcialWaterData list of an organization
     */
    
   public ArrayList<ProfessionalWaterData> getOWData(String username){
        return OWData;
    }
   
       /**
     * Set an OffcialWaterData list of an organization
     * @param OWData an OffcialWaterData list of an organization 
     */
    
   public void setOfficialWaterData(ArrayList<ProfessionalWaterData> OWData)
   {
       this.OWData = OWData;
   }
   
    
    
}
