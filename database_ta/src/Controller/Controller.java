package Controller;

import DB.DB;
import Class.Product;
import java.sql.Connection;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class Controller implements Initializable
{
    private DB db;
    private DB conn1;

    @FXML private TextField item_name;

    @FXML private Button insert;
    @FXML private Button update;
    @FXML private Button delete;
    @FXML private Button refresh;
    @FXML private Button btnBack;

    @FXML private TextField item_id;
    @FXML private Spinner<Integer> item_qty;
    @FXML private TextField item_price;
    @FXML private TextField item_total;
    @FXML private TextField item_notes;
    @FXML
    private ComboBox<String> types;

    @FXML private TableView<Product> itemTable;
    @FXML private TableColumn<Product, String> t_item_id;
    @FXML private TableColumn<Product, String> t_item_name;
    @FXML private TableColumn<Product, String> t_item_qty;
    @FXML private TableColumn<Product, String> t_item_price;
    @FXML private TableColumn<Product, String> t_item_type;
    @FXML private TableColumn<Product, String> t_item_notes;


    private ObservableList<Product> data;

    private Connection conn;
    ActionEvent evt;
    private PreparedStatement pst;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        db = new DB();
        refreshNew();
        //refresh_product();
        set_spinner();
        types.getItems().addAll("Food", "Frozen", "Beverages", "Amenities", "Accessories", "Dairies", "Vegetables", "Fruits","Medicines","Others");
        types.getSelectionModel().select("<Classify your Product>");
    }

//create an alert message
    private void alertMessage(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Hi there, "+message);
        alert.showAndWait();
    }

    public void insertClick()
    {
        String itemName = item_name.getText();
        String itemID = item_id.getText();
        String itemQTY = item_qty.getValue().toString();
        String itemPrice = item_price.getText();
        String itemType = types.getValue();
        String notes = item_notes.getText();


        try
        {
            item_qtyStateChange(evt);
            db.insertAll(itemID,itemName,itemQTY,itemPrice, itemType, notes);
            alertMessage("It's Done!");

        }
        catch (SQLException e)
        {
            alertMessage(e.getMessage());
        }
        finally
        {
            refreshNew();
            item_name.clear();
            item_id.clear();
            //item_qty.clear();
            item_price.clear();

        }
    }

    private void set_spinner()
    {
        item_qty.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0));
    }


    public void updateClick()
    {
        Product pd = itemTable.getSelectionModel().getSelectedItem();
        item_name.setText(pd.getProduct_name());
        //item_qty.setValueFactory(pd.getItem_qty());
        Product updateTable1 = itemTable.getSelectionModel().getSelectedItem();
        itemTable.getItems();
        String itemName = item_name.getText();
        String itemID = item_id.getText();
        String itemQTY = item_qty.getValue().toString();
        String itemPrice = item_price.getText();

        try
        {
            db.updateName(itemName,item_name);
            //db.updateID(updateTable1,itemID);
            //db.updateQTY(itemQTY,updateData);
            //db.updateprice(itemPrice,updateData);

            refreshNew();
            item_name.clear();
            item_id.clear();
            //item_qty.clear();
            item_price.clear();
        }
        catch(SQLException e)
        {
            alertMessage(e.getMessage());
        }
    }


    public void updateNew() throws SQLException
    {
        String itemName = item_name.getText();
        String itemID = item_id.getText();
        String itemQTY = item_qty.getValue().toString();
        String itemPrice = item_price.getText();

        String newName;
        String newID;
        String newQTY;
        String newPrice;

        try
        {
            db.updateAll(itemID, itemName, itemQTY, itemPrice);
        }
        catch(SQLException e)
        {
            alertMessage(e.getMessage());
        }

    }


    public void deleteClick()
    {
        Connection con = db.connectDB();
        Product pd = itemTable.getSelectionModel().getSelectedItem();
        item_name.setText(pd.getProduct_name());
        String id = item_name.getText();
        try
        {
            //db.delete(delete_id);
            //db.deleteName(id);
            String query = "DELETE FROM product WHERE product_name = \""+id+"\"";
            pst = con.prepareStatement(query);
            if (pst.executeUpdate() > 0)
            {
                alertMessage("Item removed!");
            }
        }
        catch (SQLException e)
        {
            alertMessage(e.getMessage());
        }
        finally
        {
            refreshNew();
            item_name.clear();
            item_id.clear();
            //item_qty.clear();
            item_price.clear();
            item_notes.clear();
        }

    }

    /////

    public void refreshNew()
    {
        try
        {
            Connection con = db.connectDB();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM product ORDER BY item_no");
            while (rs.next())
            {
                data.add(new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getInt("product_quantity"), rs.getInt("price"), rs.getString("type"),
                        rs.getString("notes")));
            }

            t_item_id.setCellValueFactory(new PropertyValueFactory<>("product_id"));
            t_item_name.setCellValueFactory(new PropertyValueFactory<>("product_name"));
            t_item_qty.setCellValueFactory(new PropertyValueFactory<>("product_quantity"));
            t_item_type.setCellValueFactory(new PropertyValueFactory<>("type"));
            t_item_price.setCellValueFactory(new PropertyValueFactory<>("price"));
            t_item_notes.setCellValueFactory(new PropertyValueFactory<>("notes"));

//        tableProduct.setItems(null);
            itemTable.setItems(data);


        }
        catch (SQLException ex)
        {
            System.err.println("Error" + ex);
        }
    }

    public void item_qtyStateChange(ActionEvent actionEvent)
    {
        int qty = Integer.parseInt(item_qty.getValue().toString());
        int price = Integer.parseInt(item_price.getText());

        int total = qty * price;

        item_total.setText(String.valueOf(total));
    }

    @FXML
    public void backButtonAction(MouseEvent event) throws IOException
    {
        if (event.getSource() == btnBack)
        {
            try
            {
                //add you loading or delays - ;-)
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml")));
                stage.setScene(scene);
                stage.show();

            }
            catch (IOException ex)
            {
                System.err.println(ex.getMessage());
                System.out.println("Back Function Not Opening");

            }
        }
    }

}
