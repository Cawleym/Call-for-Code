/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManage;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import UserManage.Professional;
/**
 *
 * @author Bingnan Dong
 */
public class ProfessionalWaterData {
    private String id;
    private String username;
    private String TestDate;
    private String StreetName;
    private String StreetNum;
    private String CityName;
    private String ZipCode;
    private Integer Lead2k250;
    private Integer Lead2k750;
    private Integer Lead2k1000;
    private Integer Lead1k250;
    private Integer Lead1k750;
    private Integer Lead1k1000;
    private Integer Copper2k250;
    private Integer Copper2k750;
    private Integer Copper2k1000;
    private Integer Copper1k250;
    private Integer Copper1k750;
    private Integer Copper1k1000;
   
   ;
    
     /**
     * This creates an OfficialWaterData for an organization
     * @param username Set ProfessionalID for the OfficialWaterData 
     * @param TestDate Set date for the OfficialWaterData
     * @param Lead2k250 Set 2 Bottle Kits 250 ml lead testing result for the OfficialWaterData
     * @param Lead2k750 Set 2 Bottle Kits 750 ml lead testing result for the OfficialWaterData
     * @param Lead2k1000 Set 2 Bottle Kits 1000 ml lead testing result for the OfficialWaterData
     * @param Lead1k1000 Set 1 Bottle Kits 1000 ml lead testing result for the OfficialWaterData
     * @param Copper2k250 Set 2 Bottle Kits 250 ml copper testing result for the OfficialWaterData
     * @param Copper2k750 Set 2 Bottle Kits 750 ml copper testing result for the OfficialWaterData
     * @param Copper2k1000 Set 2 Bottle Kits 1000 ml copper testing result for the OfficialWaterData
     * @param Copper1k1000 Set 1 Bottle Kits 1000 ml copper testing result for the OfficialWaterData
     * @param StreetNum Set Street Number for the OfficialWaterData
     * @param StreetName Set Street Name for the OfficialWaterData
     * @param CityName Set City Name for the OfficialWaterData
     * @param ZipCode Set zip code for the OfficialWaterData
     */
    
    public ProfessionalWaterData(String id ,String username,String TestDate, Integer Lead2k250, 
            Integer Lead2k750, Integer Lead2k1000, Integer Lead1k250,Integer Lead1k750,Integer Lead1k1000, Integer Copper2k250, 
            Integer Copper2k750, Integer Copper2k1000,Integer Copper1k250, 
            Integer Copper1k750, Integer Copper1k1000, String StreetNum, 
            String StreetName, String CityName, String ZipCode ){
        this.id = id;
        this.username= username;
        this.TestDate = TestDate;
      
        this.Lead2k250 = Lead2k250;
        this.Lead2k750 = Lead2k750;
        this.Lead2k1000 = Lead2k1000;
        this.Lead1k250 = Lead1k250;
        this.Lead1k750 = Lead1k750;
        this.Lead1k1000 = Lead1k1000;
        this.Copper2k250 = Copper2k250;
        this.Copper2k750 = Copper2k750;
        this.Copper2k1000 = Copper2k1000;
         this.Copper1k250 = Copper1k250;
        this.Copper1k750 = Copper1k750;
        this.Copper1k1000 = Copper1k1000;
        this.StreetNum = StreetNum;
        this.StreetName = StreetName;
        this.CityName = CityName;
        this.ZipCode = ZipCode;
        
    }
    
    
    
     public String getId(){
      
       return id;
    }  
    
      /**
     * Returns the ProfessionalID of an ProfessionalWaterData
     * @return  A String representing the ProfessionalID of an ProfessionalWaterData
     */
    
    public String getUsername(){
      
       return username;
    }  
    
                 /**
     * Returns the TestDate of an ProfessionalWaterData
     * @return  A LocalDate representing the TestDate of an ProfessionalWaterData
     */
    
    public String getTestDate(){
      
       return TestDate;
    } 
    
                  
  
    
    
             /**
     * Returns the Lead2k250 of an ProfessionalWaterData
     * @return  An integer representing the Lead2k250 of an ProfessionalWaterData
     */
    
    public Integer getLead2k250(){
      
       return Lead2k250;
    }   
    
                 /**
     * Returns the Lead2k750 of an ProfessionalWaterData
     * @return  An integer representing the Lead2k750 of an ProfessionalWaterData
     */
    
    public Integer getLead2k750(){
      
       return Lead2k750;
    } 
    
                 /**
     * Returns the Lead2k1000 of an ProfessionalWaterData
     * @return  An integer representing the Lead2k1000 of an ProfessionalWaterData
     */
    
    public Integer getLead2k1000(){
      
       return Lead2k1000;
    } 
    
                 /**
     * Returns the Lead1k1000 of an ProfessionalWaterData
     * @return  An integer representing the Lead1k1000 of an ProfessionalWaterData
     */
    
    public Integer getLead1k1000(){
      
       return Lead1k1000;
    } 
    
                 /**
     * Returns the Copper2k250 of an ProfessionalWaterData
     * @return  An integer representing the Copper2k250 of an ProfessionalWaterData
     */
    
    public Integer getCopper2k250(){
      
       return Copper2k250;
    } 
    
     /**
     * Returns the Copper2k750 of an ProfessionalWaterData
     * @return  An integer representing the Copper2k750 of an ProfessionalWaterData
     */
    
    public Integer getCopper2k750(){
      
       return Copper2k750;
    } 
    
                    /**
     * Returns the Copper2k1000 of an ProfessionalWaterData
     * @return  An integer representing the Copper2k1000 of an ProfessionalWaterData
     */
    
    public Integer getCopper2k1000(){
      
       return Copper2k1000;
    } 
    
                        /**
     * Returns the Copper1k1000 of an ProfessionalWaterData
     * @return  An integer representing the Copper1k1000 of an ProfessionalWaterData
     */
    
    public Integer getCopper1k1000(){
      
       return Copper1k1000;
    } 
    
                           /**
     * Returns the StreetNum of an ProfessionalWaterData
     * @return  An integer representing the StreetNum of an ProfessionalWaterData
     */
    
    public String getStreetNum(){
      
       return StreetNum;
    } 
    
                               /**
     * Returns the StreetName of an ProfessionalWaterData
     * @return  A String representing the StreetName of an ProfessionalWaterData
     */
    
    public String getStreetName(){
      
       return StreetName;
    } 
    
   /**
     * Return the CityName of an ProfessionalWaterData
     * @return  A String representing the CityName of an ProfessionalWaterData
     */
    
    public String getCityName(){
      
       return CityName;
    } 
   
    /**
    / * Return the CityName of an ProfessionalWaterData
     * @return  A String representing the CityName of an ProfessionalWaterData
     */
    
    public String getZipCode(){
      
       return ZipCode;
    } 

    /**
     * @return the Lead1k250
     */
    public Integer getLead1k250() {
        return Lead1k250;
    }

    /**
     * @param Lead1k250 the Lead1k250 to set
     */
    public void setLead1k250(Integer Lead1k250) {
        this.Lead1k250 = Lead1k250;
    }

    /**
     * @return the Lead1k750
     */
    public Integer getLead1k750() {
        return Lead1k750;
    }

    /**
     * @param Lead1k750 the Lead1k750 to set
     */
    public void setLead1k750(Integer Lead1k750) {
        this.Lead1k750 = Lead1k750;
    }

    /**
     * @return the Copper1k250
     */
    public Integer getCopper1k250() {
        return Copper1k250;
    }

    /**
     * @param Copper1k250 the Copper1k250 to set
     */
    public void setCopper1k250(Integer Copper1k250) {
        this.Copper1k250 = Copper1k250;
    }

    /**
     * @return the Copper1k750
     */
    public Integer getCopper1k750() {
        return Copper1k750;
    }

    /**
     * @param Copper1k750 the Copper1k750 to set
     */
    public void setCopper1k750(Integer Copper1k750) {
        this.Copper1k750 = Copper1k750;
    }
    
  
}
