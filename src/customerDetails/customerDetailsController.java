package customerDetails;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class customerDetailsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboo;

    @FXML
    private Button btnFetch;

    @FXML
    private TableView<customerDetailsBean> tableView;
    ObservableList<customerDetailsBean> list;
    
    @SuppressWarnings("unchecked")
	@FXML
    void doFetch(ActionEvent event) {
    	
    	TableColumn<customerDetailsBean, String> mobile=new TableColumn<customerDetailsBean, String>("Mobile No.");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	
    	TableColumn<customerDetailsBean, String> name=new TableColumn<customerDetailsBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("cName"));
    	
    	TableColumn<customerDetailsBean, String> address=new TableColumn<customerDetailsBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	
    	TableColumn<customerDetailsBean, String> city=new TableColumn<customerDetailsBean, String>("City");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));
    	
    	TableColumn<customerDetailsBean, String> dos=new TableColumn<customerDetailsBean, String>("DoS");
    	dos.setCellValueFactory(new PropertyValueFactory<>("dOStart"));
    	
    	TableColumn<customerDetailsBean, String> news=new TableColumn<customerDetailsBean, String>("Newspapers");
    	news.setCellValueFactory(new PropertyValueFactory<>("sNewspapers"));
    	
    	TableColumn<customerDetailsBean, String> price=new TableColumn<customerDetailsBean, String>("Prices");
    	price.setCellValueFactory(new PropertyValueFactory<>("sPrice"));  	
    	
    	tableView.getColumns().clear();
    	tableView.getColumns().addAll(mobile,name,address,city,dos,news,price);
    	
    	list=FXCollections.observableArrayList();
    	
    	try {
    		String selected=comboo.getSelectionModel().getSelectedItem();
			stmt=con.prepareStatement("select * from customers where Area=?");
			stmt.setString(1, selected);
			
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				String mob=rs.getString("Mobile");
				String nam=rs.getString("CName");
				String add=rs.getString("Address");
				String cit=rs.getString("City");
				Date doos=rs.getDate("DOStart");
				String newsp=rs.getString("SNewspapers");
				String pric=rs.getString("SPrice");
				
				String doss=doos.toString();
				
				customerDetailsBean bean=new customerDetailsBean(mob,nam,add,cit,doss,newsp,pric);
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
    
    void doFetchAreas(){
    	try {
			stmt=con.prepareStatement("select distinct Area from customers");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				//System.out.println(rs.getString("Area"));
				comboo.getItems().add(rs.getString("Area"));
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void initialize() {
        	doConnect();
        	doFetchAreas();
        	
        assert comboo != null : "fx:id=\"comboo\" was not injected: check your FXML file 'customerDetails.fxml'.";
        assert btnFetch != null : "fx:id=\"btnFetch\" was not injected: check your FXML file 'customerDetails.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'customerDetails.fxml'.";

    }
}
