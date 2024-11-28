package BillGeneration;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class BillGenerationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtMobile;

    @FXML
    private Button btnFetch;

    @FXML
    private ListView<String> lst1;

    @FXML
    private ListView<String> lst2;

    @FXML
    private DatePicker txtDateStart;

    @FXML
    private DatePicker txtDateEnd;

    @FXML
    private Button btnBill;

    @FXML
    private TextField txtBill;

    @FXML
    private Button btnSave;

    @FXML
    void doBill(ActionEvent event) {
    	float sum=0;
    	ObservableList<String> allt=lst2.getItems();
    	for(String stn:allt){
    		sum=sum+Float.parseFloat(stn);
    	}
    	LocalDate start = txtDateStart.getValue();
    	LocalDate end = txtDateEnd.getValue();
    	DaysBetweenDates(start,end,sum);
    	}
    
    //calculating days b/w two dates
    void DaysBetweenDates(LocalDate s, LocalDate e ,float sum) {
		int startDay = s.getDayOfYear();
		int endDay = e.getDayOfYear();
    	int sYear = s.getYear();
    	int eYear = e.getYear();
    	
    	int diff = (eYear*365+endDay)-(sYear*365+startDay);
    	
    	txtBill.setText(Float.toString(diff*sum));
	}
    
	@FXML
    void doFetch(ActionEvent event) {
    	try {
			stmt=con.prepareStatement("select * from customers where mobile=?");
			stmt.setString(1,txtMobile.getText());
			
			ResultSet rs=stmt.executeQuery();
			boolean jasus=true;
			if(rs.next())
			{
				jasus=false;
				 java.sql.Date dbSqlDate=rs.getDate("DOStart");
				String newspapers=rs.getString("SNewspapers");
				String prices=rs.getString("SPrice");
				String[] ary= newspapers.split(",");
				lst1.getItems().addAll(ary);
				String[] aryprice= prices.split(",");
				lst2.getItems().addAll(aryprice);
				txtDateStart.setValue(dbSqlDate.toLocalDate());
			}
			
			if(jasus==true)
				txtMobile.setText("Invalid Number");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	
	String newspaper="";
	String price="";
    @FXML
    void doSave(ActionEvent event) {
    	String mobile=txtMobile.getText();
    	String bill=txtBill.getText();
    	LocalDate start=txtDateStart.getValue();
    	LocalDate end=txtDateEnd.getValue();
    	ObservableList<String> all1=lst1.getItems();
    	for(String s:all1){
    		newspaper=newspaper+s+",";
    	}
    	ObservableList<String> all2=lst2.getItems();
    	for(String s:all2){
    		price=price+s+",";
    	}
    	
    	try {
			stmt=con.prepareStatement("insert into bill_generation (Mobile,DOStart,SNewspapers,SPrice,DOEnd,Bill) values(?,?,?,?,?,?)");
			stmt.setString(1,mobile);
			java.sql.Date da=java.sql.Date.valueOf(start);
			stmt.setDate(2, da);
			stmt.setString(3, newspaper);
			stmt.setString(4, price);
			java.sql.Date d=java.sql.Date.valueOf(end);
    		stmt.setDate(5, d);
    		stmt.setString(6, bill);
    		stmt.executeUpdate();
    		stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	try {
    
			stmt=con.prepareStatement("update customers set DOStart=? where Mobile=?");
			java.sql.Date dat=java.sql.Date.valueOf(end);
			stmt.setDate(1, dat);
			stmt.setString(2, mobile);
			stmt.executeUpdate();
			stmt.close();
		
    		showAlert("Record Saved");
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
    }
    
    void showAlert(String item)
    {
    	Alert alert = new Alert(AlertType.INFORMATION);	
    			alert.setTitle("Message");
    			alert.setContentText(item);
    			alert.showAndWait();
    }
    
    Connection con;
    PreparedStatement stmt;
    void doConnect() 
    {	
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost/javafx","root","bce");
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			System.out.println("Connected");
    }

    @FXML
    void initialize() {
    	doConnect();
        assert txtMobile != null : "fx:id=\"txtMobile\" was not injected: check your FXML file 'BillGeneration.fxml'.";
        assert btnFetch != null : "fx:id=\"btnFetch\" was not injected: check your FXML file 'BillGeneration.fxml'.";
        assert lst1 != null : "fx:id=\"lst1\" was not injected: check your FXML file 'BillGeneration.fxml'.";
        assert lst2 != null : "fx:id=\"lst2\" was not injected: check your FXML file 'BillGeneration.fxml'.";
        assert txtDateStart != null : "fx:id=\"txtDateStart\" was not injected: check your FXML file 'BillGeneration.fxml'.";
        assert txtDateEnd != null : "fx:id=\"txtDateEnd\" was not injected: check your FXML file 'BillGeneration.fxml'.";
        assert btnBill != null : "fx:id=\"btnBill\" was not injected: check your FXML file 'BillGeneration.fxml'.";
        assert txtBill != null : "fx:id=\"txtBill\" was not injected: check your FXML file 'BillGeneration.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'BillGeneration.fxml'.";

    }
}
