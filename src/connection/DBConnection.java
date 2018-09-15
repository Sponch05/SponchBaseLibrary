/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Sponch
 */
public class DBConnection {
    
    private String dbUrl;
    protected Connection connect = null;
    
    DBConnection(String dbUrl){
        this.dbUrl = dbUrl;
    }
    
    private boolean connectDB() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        String strURLParams = " ?autoRecconect=true&allowPublicKeyRetriebal=true&useSSL=false";
        String strFinalURL = dbUrl + strURLParams;
        boolean isConnected = false;
        if((connect = (Connection)DriverManager.getConnection(strFinalURL, "program", "program")) != null){
            isConnected =  !connect.isClosed();
        }
        
        return isConnected;
        
       
    }
    
    
    public Connection returnConnection (){
         
        try{
            if(connectDB()){
               return connect; 
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    
        return null;

    }
    
   
    
}
