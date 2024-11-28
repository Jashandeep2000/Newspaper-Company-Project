package Collector;

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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CollectorController {

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
    private ListView<String> lst3;

    @FXML
    private Button btnCollect;

    @FXML
    void doCollect(ActionEvent event) {

    	mob=txtMobile.getText();
    	String ados=lst1.getSelectionModel().getSelectedItem();
    	String adoe=lst2.getSelectionModel().getSelectedItem();
    	String bill=lst3.getSelectionModel().getSelectedItem();
    	LocalDate b=LocalDate.parse(ados);
    	java.sql.Date c=java.sql.Date.valueOf(b);
    	LocalDate d=LocalDate.parse(adoe);
    	java.sql.Date e=java.sql.Date.valueOf(d);
    	
    	try {
			stmt=con.prepareStatement("update bill_generation set Status=1 where Mobile=? and DOStart=? and DOEnd=?");
			stmt.setString(1, mob);
			stmt.setDate(2, c);
			stmt.setDate(3, e);
			stmt.executeUpdate();
			stmt.close();
			
			lst1.getItems().remove(ados);
			lst2.getItems().remove(adoe);
			lst3.getItems().remove(bill);
			
		} catch (SQLException f) {
			f.printStackTrace();
		}
    	
    }

    String mob;
    @FXML
    void doFetch(ActionEvent event) {
    	try {
    		mob=txtMobile.getText();
			stmt=con.prepareStatement("select * from bill_generation where mobile='"+mob+"' and Status='0'");
			ResultSet rs=stmt.executeQuery();
			lst1.getItems().clear();
			lst2.getItems().clear();
			lst3.getItems().clear();
			
			while(rs.next()){
				lst1.getItems().addAll(rs.getString("DOStart"));
				lst2.getItems().addAll(rs.getString("DOEnd"));
				lst3.getItems().addAll(rs.getString("Bill"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void doSelect(MouseEvent event) {
    	lst2.getSelectionModel().clearSelection();
    	lst3.getSelectionModel().clearSelection();
    	if(event.getClickCount()==1)
    	{
    		ObservableList<Integer> a=lst1.getSelectionModel().getSelectedIndices();
    		for(Integer x:a)
    		{
	    		lst2.getSelectionModel().select(x);
	    		lst3.getSelectionModel().select(x);
    		}
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
        assert txtMobile != null : "fx:id=\"txtMobile\" was not injected: check your FXML file 'Collector.fxml'.";
        assert btnFetch != null : "fx:id=\"btnFetch\" was not injected: check your FXML file 'Collector.fxml'.";
        assert lst1 != null : "fx:id=\"lst1\" was not injected: check your FXML file 'Collector.fxml'.";
        assert lst2 != null : "fx:id=\"lst2\" was not injected: check your FXML file 'Collector.fxml'.";
        assert lst3 != null : "fx:id=\"lst3\" was not injected: check your FXML file 'Collector.fxml'.";
        assert btnCollect != null : "fx:id=\"btnCollect\" was not injected: check your FXML file 'Collector.fxml'.";

    }
}
