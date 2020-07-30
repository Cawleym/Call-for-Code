/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManage;

/**
 *
 * @author BinnanDong
 */
public class OfficialData {
    private String Address;
    private String Date;
    private String Zip;
    private Integer Lead250;
    private Integer Lead750;
    private Integer Lead1000;
    private Integer Copper250;
    private Integer Copper750;
    private Integer Copper1000;

    public OfficialData(String Address, String Date, String Zip,  Integer Lead250,Integer Lead750,Integer Lead1000,Integer Copper250,Integer Copper750,Integer Copper1000  ){
        this.Address = Address;
        this.Date = Date;
        this.Zip =Zip;
        this.Lead250 =Lead250;
        this.Lead750 =Lead750;
        this.Lead1000 =Lead1000;
        this.Copper250 =Copper250;
        this.Copper750 =Copper750;
        this.Copper1000 =Copper1000;
        
    }
    /**
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * @return the Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(String Date) {
        this.Date = Date;
    }

    /**
     * @return the Zip
     */
    public String getZip() {
        return Zip;
    }

    /**
     * @param Zip the Zip to set
     */
    public void setZip(String Zip) {
        this.Zip = Zip;
    }

    /**
     * @return the Lead250
     */
    public Integer getLead250() {
        return Lead250;
    }

    /**
     * @param Lead250 the Lead250 to set
     */
    public void setLead250(Integer Lead250) {
        this.Lead250 = Lead250;
    }

    /**
     * @return the Lead750
     */
    public Integer getLead750() {
        return Lead750;
    }

    /**
     * @param Lead750 the Lead750 to set
     */
    public void setLead750(Integer Lead750) {
        this.Lead750 = Lead750;
    }

    /**
     * @return the Lead1000
     */
    public Integer getLead1000() {
        return Lead1000;
    }

    /**
     * @param Lead1000 the Lead1000 to set
     */
    public void setLead1000(Integer Lead1000) {
        this.Lead1000 = Lead1000;
    }

    /**
     * @return the Copper250
     */
    public Integer getCopper250() {
        return Copper250;
    }

    /**
     * @param Copper250 the Copper250 to set
     */
    public void setCopper250(Integer Copper250) {
        this.Copper250 = Copper250;
    }

    /**
     * @return the Copper750
     */
    public Integer getCopper750() {
        return Copper750;
    }

    /**
     * @param Copper750 the Copper750 to set
     */
    public void setCopper750(Integer Copper750) {
        this.Copper750 = Copper750;
    }

    /**
     * @return the Copper1000
     */
    public Integer getCopper1000() {
        return Copper1000;
    }

    /**
     * @param Copper1000 the Copper1000 to set
     */
    public void setCopper1000(Integer Copper1000) {
        this.Copper1000 = Copper1000;
    }
}
