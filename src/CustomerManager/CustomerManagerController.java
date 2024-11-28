package CustomerManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class CustomerManagerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtArea;

    @FXML
    private ImageView adminImg;

    @FXML
    private Button btnBrowse;

    @FXML
    private TextField txtCity;

    @FXML
    private DatePicker txtDate;

    @FXML
    private ListView<String> lst1;

    @FXML
    private ListView<String> lst2;

    @FXML
    private Button btnGo;

    @FXML
    private ListView<String> lst3;

    @FXML
    private ListView<String> lst4;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnStop;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnNew;
    
    
    String filePath;
    String newspaper="",price="";

    @FXML
    void doBrowse(ActionEvent event) {
    	
    	FileChooser chooser=new FileChooser();    	
    	chooser.setTitle("Select Profile Pic:");
    	chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
    	 File file=chooser.showOpenDialog(null);
    	 filePath=file.getAbsolutePath();
    	try {
    		FileInputStream fis=new FileInputStream(file);
    		adminImg.setImage(new Image(fis));
		} 
    	catch (FileNotFoundException e) {e.printStackTrace();}
    }

    
    @FXML
    void doGo(ActionEvent event) {
    	
    	lst3.getItems().clear();
    	ObservableList<String>selectname=lst1.getSelectionModel().getSelectedItems();
    	lst3.getItems().addAll(selectname);
    	lst4.getItems().clear();
    	ObservableList<String>selectrate=lst2.getSelectionModel().getSelectedItems();
    	lst4.getItems().addAll(selectrate);
    	  	
    	ObservableList<String> all1=lst3.getItems();
    	for(String s:all1){
    		newspaper=newspaper+s+",";
    	}
    	ObservableList<String> all2=lst4.getItems();
    	for(String s:all2){
    		price=price+s+",";
    	}
    }

    
    @FXML
    void doNew(ActionEvent event) throws FileNotFoundException {

    	txtMobile.setText("");
    	txtName.setText("");
    	txtAddress.setText("");
    	txtArea.setText("");
    	txtCity.setText("");
    	txtDate.setPromptText("");
        lst3.getItems().clear();
        lst4.getItems().clear();
        adminImg.setImage(new Image(new FileInputStream(new File("download.jpg"))));
        
    }


    @FXML
    void doSelectPrice(MouseEvent event) {
    	
    	//showAlert(event.getClickCount()+"");
    	lst2.getSelectionModel().clearSelection();
    	if(event.getClickCount()==1){
    		ObservableList<Integer> a=lst1.getSelectionModel().getSelectedIndices();
    		for(Integer x:a)
    		lst2.getSelectionModel().select(x);
    	}
    }
    
    
    @FXML
    void dodelete(MouseEvent event) {
    	
    }
    
    
    @FXML
    void doSave(ActionEvent event) {

    	String mobile=txtMobile.getText();
        String name=txtName.getText();
    	String address=txtAddress.getText();
    	String area=txtArea.getText();
    	String city=txtCity.getText();	
    	LocalDate local=txtDate.getValue();
    	
    	try {
    		 
    		stmt=con.prepareStatement("insert into customers(Mobile,CName,Address,Area,City,DOStart,SNewspapers,SPrice,Picture) values(?,?,?,?,?,?,?,?,?)");
    		stmt.setString(1,mobile);
    		stmt.setString(2, name);
    		stmt.setString(3, address);
    		stmt.setString(4, area);
    		stmt.setString(5, city);
    		java.sql.Date d=java.sql.Date.valueOf(local);
    		stmt.setDate(6, d);
    		stmt.setString(7, newspaper);
    		stmt.setString(8, price);
    		if(filePath==null)
    		stmt.setString(9, "No Picture");
    		else
    			stmt.setString(9, filePath);
    		stmt.executeUpdate();
    		stmt.close();
    		
    		/*stmt=con.prepareStatement("insert into bill_generation(Mobile,DOStart,SNewspapers,SPrice) values(?,?,?,?)");
    		stmt.setString(1,mobile);
    		java.sql.Date da=java.sql.Date.valueOf(local);
    		stmt.setDate(2, da);
    		stmt.setString(3, newspaper);
    		stmt.setString(4, price);
    		stmt.executeUpdate();
    		stmt.close();*/
    		
    		showAlert("Record Saved");
			} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
		} 
    }

    
    @FXML
    void doShow(ActionEvent event) {
    	
    }

    
    @FXML
    void doStop(ActionEvent event) {

    	String mobile=txtMobile.getText();
    		try {
    			stmt=con.prepareStatement("delete from customers where Mobile=?");
				stmt.setString(1,mobile);
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		showAlert("Record Delete");
    }

    
    @FXML
    void doUpdate(ActionEvent event) {

    	String mobile=txtMobile.getText();
        String name=txtName.getText();
    	String address=txtAddress.getText();
    	String area=txtArea.getText();
    	String city=txtCity.getText();
    	LocalDate local=txtDate.getValue();	
     	try {
    		stmt=con.prepareStatement("update customers set CName=?,Address=?,Area=?,City=?,DOStart=?,SNewspapers=?,SPrice=?,Picture=? where Mobile=?");
    		stmt.setString(9,mobile);
    		stmt.setString(1, name);
    		stmt.setString(2, address);
    		stmt.setString(3, area);
    		stmt.setString(4, city);
    		java.sql.Date d=java.sql.Date.valueOf(local);
    		stmt.setDate(6, d);
    		stmt.setString(6, newspaper);
    		stmt.setString(7, price);
    		if(filePath==null)
    		stmt.setString(8, "No Picture");
    		else
    			stmt.setString(8, filePath);
    		stmt.executeUpdate();
    		stmt.close();
    		
    		/*stmt=con.prepareStatement("update bill_generation set DOStart=?,SNewspapers=?,SPrice=? where Mobile=?");
    		stmt.setString(4,mobile);
    		java.sql.Date da=java.sql.Date.valueOf(local);
    		stmt.setDate(1, da);
    		stmt.setString(2, newspaper);
    		stmt.setString(3, price);
    		stmt.executeUpdate();
    		stmt.close();*/
    		
    		showAlert("Record Updated");
			} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }

    
    void showAlert(String item)
    {
    	Alert alert = new Alert(AlertType.INFORMATION);
    			//Alert alert = new Alert(AlertType.ERROR);
    			//Alert alert = new Alert(AlertType.WARNING);	
    			alert.setTitle("Message");
    			//alert.setTitle(null);
    			//alert.setHeaderText("Look, an Information Dialog");
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
    void initialize(){
    	
			doConnect();
			lst1.getItems().clear();
	    	ArrayList<String> list1=new ArrayList<String>(Arrays.asList("The Tribune","Hindustan Times","Ajit","Denik Bhaskar","DailyPost"));
			lst1.getItems().addAll(list1);
			ArrayList<String> list2=new ArrayList<String>(Arrays.asList("4.50","5","3.75","5.50","6"));
			lst2.getItems().addAll(list2);
			lst1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    	lst2.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    	lst3.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    	lst4.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
        assert txtMobile != null : "fx:id=\"txtMobile\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert txtAddress != null : "fx:id=\"txtAddress\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert adminImg != null : "fx:id=\"adminImg\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert btnBrowse != null : "fx:id=\"btnBrowse\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert txtCity != null : "fx:id=\"txtCity\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert txtDate != null : "fx:id=\"txtDate\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert lst1 != null : "fx:id=\"lst1\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert lst2 != null : "fx:id=\"lst2\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert btnGo != null : "fx:id=\"btnGo\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert lst3 != null : "fx:id=\"lst3\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert lst4 != null : "fx:id=\"lst4\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert btnStop != null : "fx:id=\"btnStop\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'CustomerManager.fxml'.";
        assert btnNew != null : "fx:id=\"btnNew\" was not injected: check your FXML file 'CustomerManager.fxml'.";

    }
}
