package billGrid;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class billGridController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLoad;

    @FXML
    private TableView<billBean> tableView;

    ObservableList<billBean> list;
    
    @SuppressWarnings("unchecked")
	@FXML
    void doLoadAll(ActionEvent event) {
    	
    	TableColumn<billBean, String> mobile=new TableColumn<billBean, String>("Mobile No.");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	
    	TableColumn<billBean, String> dos=new TableColumn<billBean, String>("Date Of Start");
    	dos.setCellValueFactory(new PropertyValueFactory<>("dOStart"));
    	
    	TableColumn<billBean, String> doe=new TableColumn<billBean, String>("Date Of End");
    	doe.setCellValueFactory(new PropertyValueFactory<>("dOEnd"));
    	
    	TableColumn<billBean, Float> bill=new TableColumn<billBean, Float>("Bill Amount");
    	bill.setCellValueFactory(new PropertyValueFactory<>("bill"));

    	tableView.getColumns().clear();
    	tableView.getColumns().addAll(mobile,dos,doe,bill);
    	
    	list=FXCollections.observableArrayList();
    	
    	try {
			stmt=con.prepareStatement("select * from bill_generation");
			
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				String mob=rs.getString("Mobile");
				Date doos=rs.getDate("DOStart");
				Date dooe=rs.getDate("DOEnd");
				float billl=rs.getFloat("Bill");
				
				String doss=doos.toString();
				String doee=dooe.toString();
				
				billBean bean=new billBean(mob,doss,doee,billl);
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
        assert btnLoad != null : "fx:id=\"btnLoad\" was not injected: check your FXML file 'billGrid.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'billGrid.fxml'.";

    }
}
