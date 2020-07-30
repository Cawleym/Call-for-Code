/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBManage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Bingnan Dong
 */
public class SqliteDB {
    private static String fileName = "FlintWater.db";
    private static Connection connection;
   
    
      /**
     * Insert a new user in DB
     * @param conn the connection of DB
     * @param user Insert a new user in DB
     */
    public static Connection getConnection()
    {
         String url = "jdbc:sqlite:" + fileName;
         try{
             connection = DriverManager.getConnection(url);
             System.out.println("Opened database successfully");
                
          }
          catch (SQLException e) {
             System.out.println(e.getMessage());
          }
          return connection;
    }
    
      /**
     * Create a table with a given string in DB
     * @param conn the connection of DB
     * @param sql  a given string for creating table
     */
    public void createTable(Connection conn , String sql ){
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
     * Delete all from a given table in DB
     * @param conn the connection of DB
     * @param tablename  a given tablename for deleting table
     */
     public void deleteTable(Connection conn ,String tableName){
        try (
           Statement stmt = conn.createStatement()) {
           String sql = "DELETE from " + tableName +";";
           stmt.execute(sql);
           stmt.close();
           System.out.println("Table deleted successfully"); 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }       
      }
       
     /**
     * Drop a given table in DB
     * @param conn the connection of DB
     * @param tablename  a given tablename for droping table
     */
      public  void dropTable(Connection conn,String tableName){
          try (
           Statement stmt = conn.createStatement()) {
           String sql = "DROP table " + tableName +";";
           stmt.execute(sql);
           stmt.close();
           System.out.println("Table dropped successfully"); 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }       
      }
    
}
