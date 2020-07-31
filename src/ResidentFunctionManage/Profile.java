/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResidentFunctionManage;

/**
 *
 * @author Bingnan Dong
 */
public class Profile {
    private String UserName;
    private String CurrentLocation;
    private String Hobby;
    private String Job;
    private String WaterChoice;
    private String DataAgree;
    
    public Profile(String UserName, String CurrentLocation, String Hobby, String Job,String WaterChoice, String DataAgree){
        this.UserName = UserName;
        this.CurrentLocation = CurrentLocation;
        this.Hobby = Hobby;
        this.Job = Job;
        this.WaterChoice = WaterChoice;
        this.DataAgree = DataAgree;
    }
    
    public String getUserName(){
        return UserName;
    }
    public String getCurrentLocation(){
        return CurrentLocation;
    }
    public String getHobby(){
        return Hobby;
    }
    public String getWaterChoice(){
        return WaterChoice;
    }
    public String getDataAgree(){
        return DataAgree;
    }

    /**
     * @return the Job
     */
    public String getJob() {
        return Job;
    }
    
}
