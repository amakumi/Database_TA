package Controller;

import Class.Company;
import DB.DB;
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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CompanyController implements Initializable
{

    @FXML
    private TextField companyId;
    @FXML
    private TextField companyName;
    @FXML
    private TextField tax_no;
    @FXML
    private TextField address;
    @FXML
    private TextField building;
    @FXML
    private TextField city;
    @FXML
    private TableView<Company> tableCompany;
    @FXML
    private TableColumn<Company, Integer> company_id;
    @FXML
    private TableColumn<Company, String> company_name;
    @FXML
    private TableColumn<Company, String> company_tax_number;
    @FXML
    private TableColumn<Company, String> company_address;
    @FXML
    private TableColumn<Company, String> company_building;
    @FXML
    private TableColumn<Company, String> company_city;
    @FXML
    private Button btnBack;

    private ObservableList<Company> data;
    private PreparedStatement preparedStatement;
    private DB db;
    private PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        db = new DB();
        refresh();
    }

    @FXML
    public void insertData() throws SQLException
    {
        String customer_name = company_name.getText();
        String company_taxno = tax_no.getText();
        String customer_address = address.getText();
        String customer_building = building.getText();
        String customer_city = city.getText();

        String query = "INSERT INTO company (company_name, company_tax_number, company_address, company_building, company_city) VALUES (?, ?, ?, ?, ?)";

        Connection con = db.connectDB();

        preparedStatement = null;

        try
        {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, customer_name);
            preparedStatement.setString(2, company_taxno);
            preparedStatement.setString(3, customer_address);
            preparedStatement.setString(4, customer_building);
            preparedStatement.setString(5, customer_city);


            if (preparedStatement.executeUpdate() > 0)
            {
                JOptionPane.showMessageDialog(null, "New Company Data Added!");
            }

        }
        catch (SQLException e)
        {
            System.out.println(e);

        }
        finally
        {
            companyName.clear();
            //gender.clear();
            address.clear();
            building.clear();
            city.clear();
            preparedStatement.execute();
            preparedStatement.close();
            refresh();
        }
    }

    @FXML
    private void deleteData() throws SQLException
    {
        String delete_id = companyId.getText();

        Connection con = db.connectDB();

        String query = "DELETE FROM company WHERE company_id=" + delete_id;

        try
        {
            preparedStatement = con.prepareStatement(query);

            if (preparedStatement.executeUpdate() > 0)
            {
                JOptionPane.showMessageDialog(null, "Company Data Deleted!");
            }

        }
        catch (SQLException e)
        {
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Something is missing!");
            alert.setContentText("Please insert the desired 'Company ID' to be deleted!");
            alert.showAndWait();
        }
        finally
        {
            companyId.clear();
            preparedStatement.execute();
            preparedStatement.close();
            refresh();
        }
    }

    private void alertMessage(String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Hi there, "+message);
        alert.showAndWait();
    }

    public void deleteClick()
    {
        Connection con = db.connectDB();
        Company pd = tableCompany.getSelectionModel().getSelectedItem();
        companyName.setText(pd.getCompany_name());
        String id = companyName.getText();
        try
        {
            //db.delete(delete_id);
            //db.deleteName(id);
            String query = "DELETE FROM company WHERE company_name = \""+id+"\"";
            pst = con.prepareStatement(query);
            if (pst.executeUpdate() > 0)
            {
                alertMessage("Company removed!");
            }
        }
        catch (SQLException e)
        {
            alertMessage("Please select the desired 'Company ID' to be deleted!");
        }
        finally
        {
            refresh();
            companyName.clear();
            companyId.clear();
            //item_qty.clear();
            //price.clear();
        }

    }

    public void refresh()
    {
        try
        {
            Connection con = db.connectDB();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM company ORDER BY company_id");
            while (rs.next())
            {
                data.add(new Company(rs.getInt("company_id"), rs.getString("company_name"), rs.getString("company_tax_number"), rs.getString("company_address"), rs.getString("company_building"), rs.getString("company_city")));
            }

            company_id.setCellValueFactory(new PropertyValueFactory<>("company_id"));
            company_name.setCellValueFactory(new PropertyValueFactory<>("company_name"));
            company_tax_number.setCellValueFactory(new PropertyValueFactory<>("company_tax_number"));
            company_address.setCellValueFactory(new PropertyValueFactory<>("company_address"));
            company_building.setCellValueFactory(new PropertyValueFactory<>("company_building"));
            company_city.setCellValueFactory(new PropertyValueFactory<>("company_city"));

            tableCompany.setItems(data);
        }
        catch (SQLException ex)
        {
            System.out.println("Invalid");
            System.err.println("Error" + ex);
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        refresh();
    }

    @FXML
    private void updateData() throws SQLException
    {
        try {
            String comp_id_ = companyId.getText();
            String comp_name_ = companyName.getText();
            String comp_taxno_ = tax_no.getText();
            String comp_address_ = address.getText();
            String comp_building_ = building.getText();
            String comp_city_ = city.getText();

            int company_id_int;
            String company_name_string;
            String company_taxno_string;
            String company_address_string;
            String company_building_string;
            String company_city_string;


            if (!comp_id_.equals("")) {
                Connection con = db.connectDB();
                if (comp_name_.equals("")) {
                    String query = "SELECT company_name FROM company WHERE company_id=" + comp_id_;
                    preparedStatement = con.prepareStatement(query);
                    ResultSet rs = preparedStatement.executeQuery();
                    rs.next();
                    company_name_string = rs.getString("company_name");
                    rs.close();
                } else {
                    company_name_string = comp_name_;
                }
                if (comp_taxno_.equals("")) {
                    String query = "SELECT company_tax_number FROM company WHERE company_id=" + comp_id_;
                    preparedStatement = con.prepareStatement(query);
                    ResultSet rs = preparedStatement.executeQuery();
                    rs.next();
                    company_taxno_string = rs.getString("company_tax_no");
                    rs.close();
                } else {
                    company_taxno_string = comp_taxno_;
                }
                if (comp_address_.equals(""))
                {
                    String query = "SELECT company_address FROM company WHERE company_id=" + comp_id_;
                    preparedStatement = con.prepareStatement(query);
                    ResultSet rs = preparedStatement.executeQuery();
                    rs.next();
                    company_address_string = rs.getString("company_address");
                    rs.close();
                }
                else
                    {
                    company_address_string = comp_address_;
                }
                if (comp_building_.equals(""))
                {
                    String query = "SELECT company_building FROM company WHERE company_id=" + comp_id_;
                    preparedStatement = con.prepareStatement(query);
                    ResultSet rs = preparedStatement.executeQuery();
                    rs.next();
                    company_building_string = rs.getString("company_building");
                    rs.close();
                } else {
                    company_building_string = comp_building_;
                }
                if (comp_city_.equals("")) {
                    String query = "SELECT company_city FROM company WHERE company_id=" + comp_id_;
                    preparedStatement = con.prepareStatement(query);
                    ResultSet rs = preparedStatement.executeQuery();
                    rs.next();
                    company_city_string = rs.getString("dob");
                    rs.close();
                }
                else
                    {
                    company_city_string = comp_city_;
                }

                String query = "UPDATE company SET company_name=?, company_tax_number=?, company_address=?, company_building=?, " +
                        "company_city=? " + "WHERE company_id=?";
                company_id_int = Integer.parseInt(comp_id_);

                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, company_name_string);
                preparedStatement.setString(2, company_taxno_string);
                preparedStatement.setString(3, company_address_string);
                preparedStatement.setString(4, company_building_string);
                preparedStatement.setString(5, company_city_string);
                preparedStatement.setInt(6, company_id_int);

                if (preparedStatement.executeUpdate() > 0)
                {
                    JOptionPane.showMessageDialog(null, "Company Data Updated!");
                }

            }
            else
                {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Whoops!");
                alert.setContentText("Something's wrong!");

                alert.showAndWait();
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        finally
        {
            companyId.clear();
            companyName.clear();
            //gender.clear();
            address.clear();
            building.clear();
            city.clear();
            preparedStatement.execute();
            preparedStatement.close();
            refresh();
        }
    }

    @FXML
    public void backButtonAction(MouseEvent event) throws SQLException
    {
        if (event.getSource() == btnBack) {
            try {
                //add you loading or delays - ;-)
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml")));
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                System.out.println("WTFFFF");

            }
        }
    }
}

