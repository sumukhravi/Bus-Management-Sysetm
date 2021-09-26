/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Model.Passenger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author Sameer
 */
public class UpdateBusDatabase {
    
    public static void UpdateTicketTable(String bNum, String src, String dest, double tFare,ObservableList<Passenger> passengerList){
        int ticketId=0;
        try {
            Connection c = DBConnection.connect();
              PreparedStatement preparedStmt;
           
              String query = "Insert into ticketTable (Bus_Number,Src,Dest,TotalFare) values('"+bNum+"','"+src+"','"+dest+"','"+tFare+"');";
           System.out.println(query);
              preparedStmt = c.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStmt.execute();
            ResultSet rs1=preparedStmt.getGeneratedKeys();
            rs1.next();
          
             ticketId=  Integer.parseInt(rs1.getString(1));
     //       System.out.println( "Same Date ID, rs.getString:"+rs1.getString(1));
           
            
              //Write the new items to DB
          
                  // copy temp item table into invo item table
              
               query = "insert into passengertable (TicketId,Name,Age,Gender) values (?,?,?,?)";
                 
                  preparedStmt = c.prepareStatement(query);
                   for(Passenger i: passengerList){
                    
                     preparedStmt.setInt(1, ticketId);
                     preparedStmt.setString(2, i.getName());
                     preparedStmt.setString(3, i.getAge());
                     preparedStmt.setString(4, i.getGender());
                     preparedStmt.addBatch();
                }
                   preparedStmt.executeBatch();




                 c.close();
              } catch (SQLException ex) {
                  Logger.getLogger(UpdateBusDatabase.class.getName()).log(Level.SEVERE, null, ex);
              }

            
    }
    
    
}
