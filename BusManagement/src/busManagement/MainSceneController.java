/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busManagement;

import DB.DBConnection;
import DB.DeleteDatabase;
import DB.DisplayDatabase;
import DB.QueryDatabase;
import DB.UpdateBusDatabase;
import Model.Passenger;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 *
 * @author tanzeem
 */
public class MainSceneController implements Initializable {

   
    @FXML
    private Button addBusBtn;
   
    @FXML
    private TextField bName;
    @FXML
    private TextField bNum;
    
    @FXML
    private Button addRouteBtn;
    @FXML
    private TextField arT;
    @FXML
    private TextField drT;
    @FXML
    private ComboBox<String> cRoute;
    @FXML
    private ComboBox<String> cStation;
    @FXML
    private TextField sCode;
    @FXML
    private TextField sName;
    @FXML
    private Button aStation;
    @FXML
    private TableView<?> stTableView;
    @FXML
    private TableView<?> bTableView;
    @FXML
    private TableView<?> rTableView;
    @FXML
    private TextField distSrc;
    @FXML
    private Button bUpdateBtn;
    @FXML
    private MenuItem tDeleteMenu;
    
   
    @FXML
    private ComboBox<String> cDriver;
    @FXML
    private ComboBox<String> cConductor;
    @FXML
    private TextField seatNum;
    @FXML
    private TextField bMake;
    @FXML
    private TextField bModel;
    @FXML
    private TextField bType;
    @FXML
    private TextField eName;
    @FXML
    private ComboBox<String> empRole;
    @FXML
    private TableView<?> empTableView;
   
    
    
    ObservableList<String> routeList = FXCollections.observableArrayList(); 
    ObservableList<String> driverList = FXCollections.observableArrayList(); 
    ObservableList<String> conductorList = FXCollections.observableArrayList(); 
    ObservableList<String> stationList = FXCollections.observableArrayList();  
   

    DisplayDatabase busTableData = new  DisplayDatabase();
    DisplayDatabase stTableData =new  DisplayDatabase();
    DisplayDatabase rTableData =new  DisplayDatabase();
    DisplayDatabase empTableData =new  DisplayDatabase();

  
    @FXML
    private Button addEmpBtn;
    @FXML
    private Label warnMsg;
    @FXML
    private Button viewRouteBtn;
    @FXML
    private TextField rId;
    @FXML
    private TextField pName;
    @FXML
    private ComboBox<String> pGender;
    @FXML
    private Button addPassengerBtn;
    @FXML
    private TextField pAge;
    @FXML
    private TableView<Passenger> addPTableView;
    @FXML
    private ComboBox<String> cBNum;
    @FXML
    private ComboBox<String> cSrc;
    @FXML
    private ComboBox<String> cDest;
    @FXML
    private Button cSubmitBtn;
    @FXML
    private Label cFare;
    @FXML
    private TextField cTicketNum;
    @FXML
    private TableView<?> sTicketTableView;
    @FXML
    private Label tWarnMsg;
    @FXML
    private TabPane mainPage;
    @FXML
    private AnchorPane loginPage;
    @FXML
    private Label loginMsg;
    @FXML
    private TextField uName;
    @FXML
    private PasswordField pass;
    @FXML
    private Button logOutBtn;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       createAddPassengerTable(addPTableView);
      busTableData.buildData(bTableView, "Select * from bustable;");
      stTableData.buildData(stTableView, "Select * from stationtable;");
            ticketData.buildData(sTicketTableView, "Select * from passengerTable;");
       
       ObservableList<String> roles = FXCollections.observableArrayList();  
        roles.add("Driver");
        roles.add("Conductor");
        empRole.setItems(roles);
        
        
        
        refreshRouteIds();
        
       
        // Fill driver combobox
       ResultSet rs = QueryDatabase.query("Select Name from EmployeeTable where Designation='Driver';");
        if(rs!=null){
           try {
                while(rs.next()){
                driverList.add(rs.getString(1));
               }
            } catch (SQLException ex) {
                Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        cDriver.setItems(driverList);
        
        
     rs = QueryDatabase.query("Select Name from EmployeeTable where Designation='Conductor';");
        if(rs!=null){
            try {
                while(rs.next()){
                    conductorList.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        cConductor.setItems(conductorList);
       
        
         rs = QueryDatabase.query("Select SCode from stationtable;");
        if(rs!=null){
            try {
                while(rs.next()){
                    stationList.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        cStation.setItems(stationList);
        cSrc.setItems(stationList);
        cDest.setItems(stationList);
         // fill the class combobox in ticket counter.
        ObservableList<String> gender = FXCollections.observableArrayList();  
        gender.add("M");
        gender.add("F");
        gender.add("O");
        pGender.setItems(gender);
        
        empTableData.buildData(empTableView, "Select * from employeeTable Order by(Eid)desc;");
        
       
    }    

String busName = "";  
String busNum = "";  
String busType = "";
String make = "";
String model = "";
String seats = "";
String routeId = "";
String driver = "";
String conductor = "";
	
String stCode = "";
String stName = "";
    
    @FXML
    private void addBus(ActionEvent event) {
        
        
       
      boolean val = getBusFields();
       if(!val){
       return;
       }
       
       
       try {
       if(updateBus){
           
           // Update Pending Need to work on this.
           
            Connection c;
            c = DBConnection.connect();
            String query = "update bus.bustable set "+
                "Name='"+busName+"',\n" +
                "type='"+busType+"',\n" +
                "Rid='"+routeId+"',\n" +
                "make='"+make+"',\n" +
                "Model='"+model+"',\n" +
                "Seats='"+seats+"',\n" +
                "DId='"+driver+"', \n" +
                "CId='"+conductor+"' \n" +
                " where Number='"+busNum+"';";                    
        
            c.createStatement().executeUpdate(query);
            
            c.close();
           updateBus=false;
            }else{
            Connection c;
            c = DBConnection.connect();
            String query = "INSERT INTO Bustable (Name,Number,type,RId,make,Model,Seats,DId,CId)VALUES("+
                "'"+busName+"',\n" +
                "'"+busNum+"',\n" +
                "'"+busType+"',\n" +
                "'"+routeId+"',\n" +
                "'"+make+"',\n" +
                "'"+model+"',\n" +
                "'"+seats+"',\n" +
                "'"+driver+"',\n" +
                "'"+conductor+"');";                    
                   
            
            c.createStatement().execute(query);
            

            
            c.close();
           
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
         resetBusFields();
        
      
        busTableData.buildData(bTableView, "Select * from bustable Order By(BId)desc;");
         
        
    }



    @FXML
    private void reqSrc(ActionEvent event) {
    }



    @FXML
    private void addRoute(ActionEvent event) {
        getRouteFields();
        try {          
          
            Connection c;
            c = DBConnection.connect();
            String query = "INSERT INTO ROUTETABLE (RId ,STATION_Code ,Dist_From_Src ,Arr_Time,Depart_Time )VALUES("+
                    "'"+routeId+"'," +
                    "'"+station+"'," +
                    "'"+distance+"'," +
                     "'"+aTime+"'," +
                    "'"+dTime+"');";
            c.createStatement().execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean addRouteList= false;
        for(String i:routeList){
        if(i.equalsIgnoreCase(routeId)){
          addRouteList = true;
        }
        }
        if(addRouteList){
        routeList.add(routeId);
        }
        
          refreshRouteIds();
            rRfields();
           
            rTableData.buildData(rTableView, "Select * from routetable where rId='"+routeId+"' Order by(Dist_From_Src) Asc;");
        
    }

    @FXML
    private void reqDrt(ActionEvent event) {
    }

    private boolean getBusFields() {
        busNum = bNum.getText();
        busName = bName.getText();
        busType = bType.getText();
        make = bMake.getText();
        model = bModel.getText();
        seats = seatNum.getText();
        routeId = cRoute.getValue();
        driver = cDriver.getValue();
        conductor = cConductor.getValue();
 
       if(busNum==null || busNum.isEmpty()){
       warnMsg.setText("Pls Enter Bus Number.");
       bNum.requestFocus();
       return false;
       }
       
       if(busName==null || busName.isEmpty()){
       warnMsg.setText("Pls Enter Bus Name.");
       bName.requestFocus();
       return false;
       }
      
       // Verify rest of the fields.
        
        return true;
        
        
    }

    private void resetBusFields() {
        bName.clear();
        bNum.clear();    
        bType.clear();   
        bMake.clear();   
        bModel.clear();   
        seatNum.clear(); 
        cRoute.setValue("");
        

        updateBus = false;


    }

    @FXML
    private void reqsName(ActionEvent event) {
    }

    @FXML
    private void aStation(ActionEvent event) {
        try {
            getSfields();
            Connection c;
            c = DBConnection.connect();
            String query = "INSERT INTO stationtable (Scode,STATION_NAME)VALUES("+
                    "'"+stCode+"'," +
                    "'"+stName+"');";
            c.createStatement().execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
            stationList.add(stCode);
            cStation.setItems(stationList);
        
            rSfields();
            stTableData.buildData(stTableView, "Select * from stationtable order By(Scode)Desc;");
    }

    private void getSfields() {
        stCode = sCode.getText();
        stName = sName.getText();
        
    }

    private void rSfields() {
       sName.clear();
sCode.clear(); 
    }

    private boolean getRouteFields() {
      
       routeId = rId.getText();
       station = cStation.getValue();
       distance= distSrc.getText();
       aTime = arT.getText();
       dTime = drT.getText();
       
       if(routeId==null || routeId.isEmpty()){
           rId.requestFocus();
           return false;
       }
       
       if(station==null || station.isEmpty()){
           cStation.requestFocus();
           return false;
       }
       if(distance==null || distance.isEmpty()){
           distSrc.requestFocus();
          return false;
       }
        if(aTime==null || aTime.isEmpty()){
           arT.requestFocus();
           return false;
       }
       
        if(dTime==null || dTime.isEmpty()){
           drT.requestFocus();
           return false;
       }
        
        return true;
       
       
    }

    private void rRfields() {
        
        distSrc.clear();
        cStation.setValue("");
        arT.clear();
        drT.clear();
        
        
    }

    String trainN="";
    private void rTrainFind(ActionEvent event) {
       trainN  = cRoute.getValue();
        if(trainN!=null && !trainN.isEmpty()){
        
        rTableData.buildData(rTableView, "Select * from routetable where Train_Number='"+trainN+"';");
        }
    }
boolean updateBus = false;
    @FXML
    private void updateB(ActionEvent event) {
        
       String busNumber = bNum.getText();
       if (busNumber!=null && !busNumber.isEmpty()){
           
           ResultSet rs = QueryDatabase.query("select * from bustable where number = '"+busNumber+"';");
           if (rs!=null){
               try {
                   while (rs.next()){
                       
                       updateBus=true;
                       bName.setText(rs.getString(2));
                       bType.setText(rs.getString(4));
                       bMake.setText(rs.getString(6));
                       bModel.setText(rs.getString(7));
                       seatNum.setText(rs.getString(8));
                         cRoute.setValue(rs.getString(5));
                         cDriver.setValue(rs.getString(9));
                        cConductor.setValue(rs.getString(10));

                           
                       updateBus=true;
                       
                       
                   }  } catch (SQLException ex) {
                   Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
               }
           
           
           }
           }
        
        
        
    }

    @FXML
    private void tMenuDelete(ActionEvent event) {
        
       int index = bTableView.getSelectionModel().getFocusedIndex();
       ObservableList<ObservableList> data = busTableData.getData();
       ObservableList<String> itemData = data.get(index);
       
       int id = Integer.parseInt(itemData.get(0));
       String bNum = itemData.get(2);
       System.out.println(bNum);
       DeleteDatabase.deleteRecord(id, "busTable");
       
       
       
      
       
       busTableData.buildData(bTableView, "Select * from busTable;");
       
    }

    @FXML
    private void addEmp(ActionEvent event) {
        
        String name = eName.getText();
        String role = empRole.getValue();
        
        if(name==null || name.isEmpty()){
            eName.requestFocus();
            return;
        }
        if(role==null || role.isEmpty()){
            empRole.requestFocus();
            return;
        }
        
        Connection c;
        
        try{
        
            c= DBConnection.connect();
               c = DBConnection.connect();
            String query = "INSERT INTO EmployeeTable (Name ,Designation )VALUES("+
                    "'"+name+"'," +
                    "'"+role+"');";
            c.createStatement().execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(role.equalsIgnoreCase("Driver")){
        driverList.add(name);
        }else{
        conductorList.add(name);
        }    
        eName.clear();
        empTableData.buildData(empTableView, "Select * from employeeTable Order by(Eid)desc;");
        
        
    }

    @FXML
    private void reqBName(ActionEvent event) {
    }

    @FXML
    private void viewRoute(ActionEvent event) {
       rTableData.buildData(rTableView, "Select RId,Station_Code from routeTable;");
    }
    
    boolean updateRoute = false;
    String station = "";
    String distance = "";
    String aTime = "";
    String dTime = "";
    
    @FXML
    private void getRoutes(ActionEvent event) {
         routeId = rId.getText();
        if(routeId!= null && !routeId.isEmpty()){
            ResultSet rs = QueryDatabase.query("Select * from routeTable where RId='"+routeId+"';");
            if(rs!=null){
            updateRoute = true;
            }else{
             updateRoute = false;
            }
           rTableData.buildData(rTableView, "Select * from routetable where rId='"+routeId+"' Order by(Dist_From_Src) Asc;");
            cStation.requestFocus();
        }else{
        rId.requestFocus();
        return;
        }
    }

    double fare = 0;
    @FXML
    private void getFare(ActionEvent event) {
        
        String bNum = cBNum.getValue();
        if(bNum==null || bNum.isEmpty()){
             cBNum.requestFocus();
            tWarnMsg.setText("Select Bus Number");
            return;
        }
        String src = cSrc.getValue();
        String dst = cDest.getValue();
        String query = "select (Select Dist_From_Src from routeTable where Rid=(Select Rid from busTable where number='"+bNum+"') AND Station_Code='"+dst+"')-(Select Dist_From_Src from routeTable where  Rid=(Select Rid from busTable where number='"+bNum+"') AND Station_Code='"+src+"');";
        ResultSet rs = QueryDatabase.query(query);
        System.out.println(query);
        if(rs!=null){
            try {
                if(rs.next()){
                    
                    fare = Double.parseDouble(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
        
    }

    @FXML
    private void createTicket(ActionEvent event) {
        
        if(!getTPFields()){
            return;
        }
        
        if(!passengerList.isEmpty()){
               
             UpdateBusDatabase.UpdateTicketTable(tCBus,tCSrc,tCDest,totalFare,passengerList);
                    
               
           }else{
            tWarnMsg.setText("Add atleast one passenger.");
             return;
           }
           
          resetCounterFields();  
          ticketData.buildData(sTicketTableView, "Select * from passengerTable;");
        
    }
    DisplayDatabase ticketData = new DisplayDatabase();
    @FXML
    private void getTickets(ActionEvent event) {
        String tId = cTicketNum.getText();
        String query="";
        if(tId==null || tId.isEmpty()){
         query="Select * from passengerTable;";
        
        }else{
        query="Select * from passengerTable where TicketId='"+tId+"';";
        }
        
       ticketData.buildData(sTicketTableView, query);
    }

    @FXML
    private void deleteTicket(ActionEvent event) {
          
       int index = sTicketTableView.getSelectionModel().getFocusedIndex();
       ObservableList<ObservableList> data = ticketData.getData();
       ObservableList<String> itemData = data.get(index);
       
       int id = Integer.parseInt(itemData.get(0));
      
       DeleteDatabase.deleteRecord(id, "tickettable");
       
       
      ticketData.buildData(sTicketTableView, "Select * from passengerTable;");
        }


     public void createAddPassengerTable(TableView rTableView){
        
            TableColumn<Passenger, String> col_id = new TableColumn("No.");
                col_id.setPrefWidth(30);
                rTableView.getColumns().add(col_id);
                col_id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Passenger, String>,
                        ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Passenger, String> t) {
                                // t.getValue() returns the Test instance for a particular TableView row
                                //    return t.getValue().testProperty();
                                // or
                                return new SimpleStringProperty(String.valueOf(t.getValue().getId()));
                            }
                        });

        
        
        
               TableColumn<Passenger, String>  col_name = new TableColumn("Name");
                //      col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
                col_name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Passenger, String>,
                        ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Passenger, String> t) {
                                // t.getValue() returns the Test instance for a particular TableView row
                                //    return t.getValue().testProperty();
                                // or
                                return new SimpleStringProperty(t.getValue().getName());
                            }
                        });
                col_name.setPrefWidth(100);
                rTableView.getColumns().add(col_name);
                col_name.setCellFactory(TextFieldTableCell.<Passenger>forTableColumn());



                TableColumn<Passenger, String>  col_value = new TableColumn("Age");
                col_value.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Passenger, String>,
                        ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Passenger, String> t) {
                                // t.getValue() returns the Test instance for a particular TableView row
                                //    return t.getValue().testProperty();
                                // or
                                return new SimpleStringProperty(t.getValue().getAge());
                            }
                        });

                col_value.setPrefWidth(60);
                rTableView.getColumns().add(col_value);
                col_value.setCellFactory(TextFieldTableCell.<Passenger>forTableColumn());


                TableColumn<Passenger, String>  col_gender = new TableColumn("Gender");
                col_gender.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Passenger, String>,
                        ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Passenger, String> t) {
                                // t.getValue() returns the Test instance for a particular TableView row
                                //    return t.getValue().testProperty();
                                // or
                                return new SimpleStringProperty(t.getValue().getGender());
                            }
                        });

                col_gender.setPrefWidth(60);
                rTableView.getColumns().add(col_gender);
                col_value.setCellFactory(TextFieldTableCell.<Passenger>forTableColumn());


                //Insert Button
                TableColumn<Passenger, Boolean> col_action = new TableColumn<>("Delete");
                col_action.setSortable(false);

                col_action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Passenger, Boolean>,
                                ObservableValue<Boolean>>() {

                                    @Override
                                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Passenger, Boolean> p) {
                                        return new SimpleBooleanProperty(p.getValue() != null);
                                    }
                                });

                col_action.setCellFactory(new Callback<TableColumn<Passenger, Boolean>, TableCell<Passenger, Boolean>>() {

                            @Override
                            public TableCell<Passenger, Boolean> call(TableColumn<Passenger, Boolean> p) {
                                return new ButtonCell(rTableView);
                            }

                        });

                 col_action.setPrefWidth(30);

                rTableView.getColumns().add(col_action);
    
    
    }

      int passengerId = 0;
   double totalFare = 0;
   @FXML
    private void addPassenger(ActionEvent event) {
        
        String name = pName.getText();
        String age = pAge.getText();
        String gender = pGender.getValue();
        
        if(name!=null && !name.isEmpty()){
            if(age!=null && !age.isEmpty()){
                if(gender!=null && !gender.isEmpty()){
                        passengerId++;
                        passengerList.add(new Passenger(passengerId,name,age,gender));
                        totalFare+= fare;
//                         System.out.println(fare);
//                          System.out.println(totalFare);
                        cFare.setText(String.format("%.2f",totalFare));
                    }else{
                        pGender.requestFocus();
                    }
            }else{
                pAge.requestFocus();
            }
                
        }else{
            pName.requestFocus();
        }
         
       pName.clear();
       pAge.clear();
       
        
        addPTableView.setItems(passengerList);
        addPTableView.refresh();
        
    }

    String tCSrc ="" ;
    String tCDest ="";
    String tCBus = "";
    private boolean getTPFields() {
            
            tCSrc = cSrc.getValue();
            tCDest = cDest.getValue();
            tCBus = cBNum.getValue();
          
            
            
              if(tCSrc==null || tCSrc.isEmpty()){
                  cSrc.requestFocus();
                  return false;
            }
              
               if(tCDest==null || tCDest.isEmpty()){
                  cDest.requestFocus();
                  return false;
            }
                 if(tCBus==null || tCBus.isEmpty()){
                  cBNum.requestFocus();
                  return false;
            }
                 
            return true;
    }

    @FXML
    private void getBuses(ActionEvent event) {
        
        String src = cSrc.getValue();
        String dst = cDest.getValue();
        
        if(src==null || src.isEmpty()){
            cSrc.requestFocus();
            return;
        }
        if(dst==null || dst.isEmpty()){
            cDest.requestFocus();
            return;
        }
        ObservableList<String> busNumber = FXCollections.observableArrayList();
        ResultSet rs = QueryDatabase.query(" Select Number from bustable where Rid IN (Select Distinct(Rid) from routeTable where station_Code='"+src+"' AND Rid IN (Select Distinct(Rid) from routeTable where station_Code='"+dst+"'));");
        if(rs!=null){
            try {
                while(rs.next()){
                   busNumber.add(rs.getString(1));
                  tWarnMsg.setText("");  
                }  
                
            } catch (SQLException ex) {
                Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
        tWarnMsg.setText("Buses not available between selected stops.");
        }
        if(busNumber==null || busNumber.isEmpty()){
        tWarnMsg.setText("Buses not available between selected stops.");
         
        cBNum.setItems(busNumber);
        }else{
        
        cBNum.setItems(busNumber);
        }
        
        
        
        
    }

    private void resetCounterFields() {
           cSrc.setValue("");
           cDest.setValue("");
           cBNum.setValue("");
           fare =0;
           totalFare=0;
           cFare.setText("0.0");
           passengerList.clear();
           addPTableView.refresh();
           tWarnMsg.setText("");
         
    }

    private void refreshRouteIds() {
        routeList.clear();
        ResultSet rs = QueryDatabase.query("Select Distinct(RID) from RouteTable;");
        if(rs!=null){
            try {
                while(rs.next()){
                    routeList.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        cRoute.setItems(routeList);
    }

     //Define the button cell
    private class ButtonCell extends TableCell<Passenger, Boolean> {
        final Button cellButton = new Button("x");
         
        ButtonCell(final TableView tblView){
             
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
 
                @Override
                public void handle(ActionEvent t) {
                    int selectdIndex = getTableRow().getIndex();
                      
                    deletePassenger(selectdIndex);
                       
                       
                    //delete the selected item in data
                    
                }
            });
        }
 
        
        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                cellButton.setPrefSize(28, 20);
                setGraphic(cellButton);
                
            } else {
                 setGraphic(null);
                }
        }
    } 
    ObservableList<Passenger> passengerList = FXCollections.observableArrayList();
    private void  deletePassenger(int index){
       
       
        passengerList.remove(index);
        addPTableView.refresh();
   
   }
    
    HashMap map = new HashMap();
    @FXML
    private void login(ActionEvent event) {
       map.clear();
        map.put("admin", "admin");
        
//        map.put("admin", "admin@123");
        
        String name = uName.getText();
        String pwd = pass.getText();
        
        if(map.containsKey(name)){
            if(map.get(name).toString().equals(pwd)){
                    mainPage.setVisible(true);
                    loginPage.setVisible(false);
                    loginMsg.setText("");
                    logOutBtn.setVisible(true);
            }else{
                loginMsg.setText("Wrong credentials");
            }
        
        }else{
                loginMsg.setText("Wrong credentials");
            }
        
    }

    @FXML
    private void logOut(ActionEvent event) {
        
                     mainPage.setVisible(false);
                    loginPage.setVisible(true);
                    loginMsg.setText("Enter Credentials");
                    logOutBtn.setVisible(false);
                    uName.clear();
                    pass.clear();
    }
    
    

}
    

