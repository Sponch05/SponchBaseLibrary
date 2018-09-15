/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interaction;

import connection.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Sponch
 */
public class DBInteraction {

    private PreparedStatement preparedStatement = null;
    DBConnection connection = null;
    String dbUrl;
    String dbName;
    
    DBInteraction(String dbUrl, String dbName){
        this.dbUrl = dbUrl;
        this.dbName = dbName;
    }
    
 
    DBInteraction(){
    }
    
    
    public ResultSet query(String tableName, String[] projection,
            String selection, String selectionArgs) throws Exception {
        
        StringBuilder sqlStringBuilder = new StringBuilder();
        //StringBuffer stringbuffer = new StringBuffer();
        
        sqlStringBuilder.append("SELECT ");
        
        if(projection != null){
            for(int i = 0; i < projection.length; i++){
                
                sqlStringBuilder.append(projection[i]);
                
                if(i > 0 && i < projection.length - 1){
                    sqlStringBuilder.append(", ");
                }   
            }
        }else{
            sqlStringBuilder.append("* ");
        }
        
        sqlStringBuilder.append("FROM ".concat(tableName).concat(";"));
        
        // @TODO WHERE section
        
        // Debug verify string
        
        System.out.println(sqlStringBuilder.toString());
        
        return makeConnectedPreparedStatement(sqlStringBuilder.toString()).executeQuery();
    
}
    


   private PreparedStatement makeConnectedPreparedStatement(String SqlString){
       try{
           preparedStatement = connection.returnConnection().prepareStatement(SqlString);
                     
       }catch(SQLException sqlE){
           System.err.println("SponchBase: Error in the SQL");
           sqlE.printStackTrace();
       }
      
       return preparedStatement;
   
   }
       
}
