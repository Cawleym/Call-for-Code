/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManage;

/**
 *
 * @authorBingnanDong
 */
public class ResidentWaterData {
    private String id;
    private String username;
    private String TestDate;
    private String StreetName;
    private String ZipCode;
    private Integer Lead250;
    private Integer Lead750;
    private Integer Lead1000;
    private Integer Copper250;
    private Integer Copper750;
    private Integer Copper1000;

     public ResidentWaterData(String id ,String username,String TestDate,  String StreetName,String ZipCode,
             Integer Lead250,Integer Lead750,Integer Lead1000, Integer Copper250,Integer Copper750,Integer Copper1000)
     {
        this.id = id;
        this.username= username;
        this.TestDate = TestDate;
        this.StreetName = StreetName;
        this.ZipCode = ZipCode;
        this.Lead250 = Lead250;
        this.Lead750 = Lead750;
        this.Lead1000 = Lead1000;
        this.Copper250 = Copper250;
        this.Copper750 = Copper750;
        this.Copper1000 = Copper1000;
        
     }
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the TestDate
     */
    public String getTestDate() {
        return TestDate;
    }

    /**
     * @return the StreetName
     */
    public String getStreetName() {
        return StreetName;
    }

    /**
     * @return the ZipCode
     */
    public String getZipCode() {
        return ZipCode;
    }

    /**
     * @return the Lead250
     */
    public Integer getLead250() {
        return Lead250;
    }

    /**
     * @return the Lead750
     */
    public Integer getLead750() {
        return Lead750;
    }

    /**
     * @return the Lead1000
     */
    public Integer getLead1000() {
        return Lead1000;
    }

    /**
     * @return the Copper250
     */
    public Integer getCopper250() {
        return Copper250;
    }

    /**
     * @return the Copper750
     */
    public Integer getCopper750() {
        return Copper750;
    }

    /**
     * @return the Copper1000
     */
    public Integer getCopper1000() {
        return Copper1000;
    }
}
