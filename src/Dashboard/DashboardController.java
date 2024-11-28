package Dashboard;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnBill;

    @FXML
    private Button btnBillStat;

    @FXML
    private Button btnBillColl;

    @FXML
    private Button btnCusStat;

    @FXML
    private Button btnNewsHis;

    @FXML
    void doBill(ActionEvent event) {

    	try{
        	
	    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("BillGeneration/BillGeneration.fxml"));
	    	Scene scene=new Scene(root);
	    	Stage stage=new Stage();
	    	stage.setScene(scene);
	    	stage.show();
	    	
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    }

    @FXML
    void doBillColl(ActionEvent event) {

    	try{
        	
	    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Collector/Collector.fxml"));
	    	Scene scene=new Scene(root);
	    	Stage stage=new Stage();
	    	stage.setScene(scene);
	    	stage.show();
	    	
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    }

    @FXML
    void doBillStat(ActionEvent event) {

    	try{
        	
	    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("billStatus/billStatus.fxml"));
	    	Scene scene=new Scene(root);
	    	Stage stage=new Stage();
	    	stage.setScene(scene);
	    	stage.show();
	    	
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

    @FXML
    void doCusStat(ActionEvent event) {

    	try{
        	
	    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customerDetails/customerDetails.fxml"));
	    	Scene scene=new Scene(root);
	    	Stage stage=new Stage();
	    	stage.setScene(scene);
	    	stage.show();
	    	
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }

    @FXML
    void doCustomers(ActionEvent event) {

    	try{
    	
	    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("CustomerManager/CustomerManager.fxml"));
	    	Scene scene=new Scene(root);
	    	Stage stage=new Stage();
	    	stage.setScene(scene);
	    	stage.show();
	    	
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    }

    @FXML
    void doNewsHis(ActionEvent event) {

    	try{
        	
	    	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("NewspaperHistory/NewspaperHistory.fxml"));
	    	Scene scene=new Scene(root);
	    	Stage stage=new Stage();
	    	stage.setScene(scene);
	    	stage.show();
	    	
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
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
        assert btnCustomers != null : "fx:id=\"btnCustomers\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert btnBill != null : "fx:id=\"btnBill\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert btnBillStat != null : "fx:id=\"btnBillStat\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert btnBillColl != null : "fx:id=\"btnBillColl\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert btnCusStat != null : "fx:id=\"btnCusStat\" was not injected: check your FXML file 'Dashboard.fxml'.";
        assert btnNewsHis != null : "fx:id=\"btnNewsHis\" was not injected: check your FXML file 'Dashboard.fxml'.";

    }
}
