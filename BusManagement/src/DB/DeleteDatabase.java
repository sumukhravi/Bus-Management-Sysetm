package DB;

import java.sql.Connection;
import javafx.collections.FXCollections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tanzeem
 */
public class DeleteDatabase {
      
      
      //  data.clear();
      //  data.removeAll(data);
       public static void deleteRecord(int id,String tableName){
        Connection c ; 
          String query = "";  
           try{  
         c = DBConnection.connect(); 
        if(tableName.equalsIgnoreCase("busTable")){
          query = "Delete from "+tableName+" where BId='"+id+"';";
        }else{
          query = "Delete from "+tableName+" where Id='"+id+"';";
        }
         c.createStatement().execute(query);
         c.close();
         
           
       }catch(Exception e){  
        System.out.println("Error on Deleting Data");        
      }  
       }
}
