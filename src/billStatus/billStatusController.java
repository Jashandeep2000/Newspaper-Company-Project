package billStatus;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class billStatusController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton radPending;

    @FXML
    private RadioButton radReceived;

    @FXML
    private TableView<billStatusBean> tableView;
    ObservableList<billStatusBean> list;

    @SuppressWarnings("unchecked")
	@FXML
    void doPending(ActionEvent event) {

    	TableColumn<billStatusBean, String> mobile=new TableColumn<billStatusBean, String>("Mobile No.");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	
    	TableColumn<billStatusBean, String> dos=new TableColumn<billStatusBean, String>("Date Of Start");
    	dos.setCellValueFactory(new PropertyValueFactory<>("dOStart"));
    	
    	TableColumn<billStatusBean, String> doe=new TableColumn<billStatusBean, String>("Date Of End");
    	doe.setCellValueFactory(new PropertyValueFactory<>("dOEnd"));
    	
    	TableColumn<billStatusBean, Float> bill=new TableColumn<billStatusBean, Float>("Bill Amount");
    	bill.setCellValueFactory(new PropertyValueFactory<>("bill"));

    	tableView.getColumns().clear();
    	tableView.getColumns().addAll(mobile,dos,doe,bill);
    	
    	list=FXCollections.observableArrayList();
    	
    	try {
			stmt=con.prepareStatement("select * from bill_generation where Status=0");
			
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				String mob=rs.getString("Mobile");
				Date doos=rs.getDate("DOStart");
				Date dooe=rs.getDate("DOEnd");
				float billl=rs.getFloat("Bill");
				
				String doss=doos.toString();
				String doee=dooe.toString();
				
				billStatusBean bean=new billStatusBean(mob,doss,doee,billl);
				list.add(bean);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	tableView.setItems(list);
    	
    }

    @SuppressWarnings("unchecked")
	@FXML
    void doReceived(ActionEvent event) {

    	TableColumn<billStatusBean, String> mobile=new TableColumn<billStatusBean, String>("Mobile No.");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	
    	TableColumn<billStatusBean, String> dos=new TableColumn<billStatusBean, String>("Date Of Start");
    	dos.setCellValueFactory(new PropertyValueFactory<>("dOStart"));
    	
    	TableColumn<billStatusBean, String> doe=new TableColumn<billStatusBean, String>("Date Of End");
    	doe.setCellValueFactory(new PropertyValueFactory<>("dOEnd"));
    	
    	TableColumn<billStatusBean, Float> bill=new TableColumn<billStatusBean, Float>("Bill Amount");
    	bill.setCellValueFactory(new PropertyValueFactory<>("bill"));

    	tableView.getColumns().clear();
    	tableView.getColumns().addAll(mobile,dos,doe,bill);
    	
    	list=FXCollections.observableArrayList();
    	
    	try {
			stmt=con.prepareStatement("select * from bill_generation where Status=1");
			
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				String mob=rs.getString("Mobile");
				Date doos=rs.getDate("DOStart");
				Date dooe=rs.getDate("DOEnd");
				float billl=rs.getFloat("Bill");
				
				String doss=doos.toString();
				String doee=dooe.toString();
				
				billStatusBean bean=new billStatusBean(mob,doss,doee,billl);
				list.add(bean);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	tableView.setItems(list);
    	
    	
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
        assert radPending != null : "fx:id=\"radPending\" was not injected: check your FXML file 'billStatus.fxml'.";
        assert radReceived != null : "fx:id=\"radReceived\" was not injected: check your FXML file 'billStatus.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'billStatus.fxml'.";

    }
}
