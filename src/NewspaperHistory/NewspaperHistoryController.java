package NewspaperHistory;

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

public class NewspaperHistoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboo;

    @FXML
    private Button btnFetch;

    @FXML
    private TableView<NewspaperHistoryBean> tableView;
    ObservableList<NewspaperHistoryBean> list;
    
    @SuppressWarnings("unchecked")
	@FXML
    void doFetch(ActionEvent event) {
    	TableColumn<NewspaperHistoryBean, String> mobile=new TableColumn<NewspaperHistoryBean, String>("Mobile No.");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	
    	TableColumn<NewspaperHistoryBean, String> name=new TableColumn<NewspaperHistoryBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("cName"));
    	
    	TableColumn<NewspaperHistoryBean, String> address=new TableColumn<NewspaperHistoryBean, String>("Address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    	
    	TableColumn<NewspaperHistoryBean, String> are=new TableColumn<NewspaperHistoryBean, String>("Area");
    	are.setCellValueFactory(new PropertyValueFactory<>("area"));
    	
    	TableColumn<NewspaperHistoryBean, String> city=new TableColumn<NewspaperHistoryBean, String>("City");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));
    	
    	TableColumn<NewspaperHistoryBean, String> dos=new TableColumn<NewspaperHistoryBean, String>("DoS");
    	dos.setCellValueFactory(new PropertyValueFactory<>("dOStart"));
    	
    	TableColumn<NewspaperHistoryBean, String> news=new TableColumn<NewspaperHistoryBean, String>("Newspapers");
    	news.setCellValueFactory(new PropertyValueFactory<>("sNewspapers"));
    	
    	TableColumn<NewspaperHistoryBean, String> price=new TableColumn<NewspaperHistoryBean, String>("Prices");
    	price.setCellValueFactory(new PropertyValueFactory<>("sPrice"));  	
    	
    	tableView.getColumns().clear();
    	tableView.getColumns().addAll(mobile,name,address,are,city,dos,news,price);
    	
    	list=FXCollections.observableArrayList();
    	
    	try {
    		String selected=comboo.getSelectionModel().getSelectedItem();
			stmt=con.prepareStatement("select * from customers where SNewspapers like '%"+selected+"%'");
			
			
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				String mob=rs.getString("Mobile");
				String nam=rs.getString("CName");
				String add=rs.getString("Address");
				String areas=rs.getString("Area");
				String cit=rs.getString("City");
				Date doos=rs.getDate("DOStart");
				String newsp=rs.getString("SNewspapers");
				String pric=rs.getString("SPrice");
				
				String doss=doos.toString();
				
				NewspaperHistoryBean bean=new NewspaperHistoryBean(mob,nam,add,areas,cit,doss,newsp,pric);
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
    	String[] items={"Select","The Tribune","Hindustan Times","Ajit","Denik Bhaskar","DailyPost"};
    	comboo.getItems().addAll(items);
    	comboo.getSelectionModel().select(0);
        assert comboo != null : "fx:id=\"comboo\" was not injected: check your FXML file 'NewspaperHistory.fxml'.";
        assert btnFetch != null : "fx:id=\"btnFetch\" was not injected: check your FXML file 'NewspaperHistory.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'NewspaperHistory.fxml'.";

    }
}
