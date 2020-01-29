package Controller;

import Class.Employee;
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

public class EmployeeController implements Initializable
{

    @FXML
    private TextField staffId;
    @FXML
    private TextField staffName;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private TextField address;
    @FXML
    private TextField telephone;
    @FXML
    private TextField pass;
    @FXML
    private Button btnBack;
    @FXML
    private TableView <Employee> tblEmployee;
    @FXML
    private TableColumn<Employee, Integer> employee_id;
    @FXML
    private TableColumn<Employee, String> employee_name;
    @FXML
    private TableColumn<Employee, String> employee_gender;
    @FXML
    private TableColumn<Employee, String> employee_address;
    @FXML
    private TableColumn<Employee, Integer> employee_telp_no;
    @FXML
    private TableColumn<Employee, String> employee_password;

    private ObservableList<Employee> data;
    private PreparedStatement preparedStatement;
    private DB db;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        db = new DB();
        refresh();
    }

    @FXML
    public void insertData() throws SQLException
    {
        String staff_id = staffId.getId();
        String staff_Name = staffName.getText();
        String staff_gender = gender.getValue();
        String staff_address = address.getText();
        String staff_telp = telephone.getText();
        String staff_pass = pass.getText();

        String query = "INSERT INTO employee (employee_id, employee_name, employee_gender, employee_address, employee_telp_no, employee_password) VALUES (?,?,?,?,?,?)";

        Connection con = db.connectDB();

        preparedStatement = null;

        try
        {
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, staffId.getText());
            preparedStatement.setString(2, staffName.getText());
            preparedStatement.setString(3, gender.getValue());
            preparedStatement.setString(4, address.getText());
            preparedStatement.setString(5, telephone.getText());
            preparedStatement.setString(6, pass.getText());

            if (preparedStatement.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New Staff Added");
            }

        } catch (SQLException e)
        {
            System.out.println(e);

        }
        finally
        {
            staffName.clear();
            //gender.clear();
            address.clear();
            telephone.clear();
            preparedStatement.execute();
            preparedStatement.close();
            refresh();
        }
    }

    @FXML
    private void deleteData() throws SQLException {
        String delete_id = staffId.getText();

        Connection con = db.connectDB();

        String query = "DELETE FROM employee WHERE employee_id=" + delete_id;

        try {
            preparedStatement = con.prepareStatement(query);

            if (preparedStatement.executeUpdate() > 0)
            {
                JOptionPane.showMessageDialog(null, "Staff Data Deleted");
            }

        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        finally
        {
            staffId.clear();
            preparedStatement.execute();
            preparedStatement.close();
            refresh();
        }
    }

    public void refresh()
    {
        try
        {
            Connection con = db.connectDB();
            data = FXCollections.observableArrayList();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM employee");
            while (rs.next())
            {
                data.add(new Employee(rs.getInt("employee_id"), rs.getString("employee_name"), rs.getString("employee_gender"), rs.getString("employee_address"), rs.getInt("employee_telp"), rs.getString("employee_password")));
            }

            employee_id.setCellValueFactory(new PropertyValueFactory<>("staff_id"));
            employee_name.setCellValueFactory(new PropertyValueFactory<>("staff_name"));
            employee_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            employee_address.setCellValueFactory(new PropertyValueFactory<>("address"));
            employee_telp_no.setCellValueFactory(new PropertyValueFactory<>("telp_no"));
            employee_password.setCellValueFactory(new PropertyValueFactory<>("password"));

            tblEmployee.setItems(data);
        }
        catch (SQLException ex)
        {
            System.out.println("Invalid");
            System.err.println("Error" + ex);
        }
    }


    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        refresh();
    }

    @FXML
    private void updateData() throws SQLException
    {
        try
        {
            String staff_id_ = staffId.getText();
            String staff_name_ = staffName.getText();
            String staff_gender_ = gender.getValue();
            String staff_address_ = address.getText();
            String staff_telp_ = telephone.getText();

            int staff_id_int;
            String staff_name_string;
            String staff_gender_string;
            String staff_address_string;
            String staff_telp_string;


            if (!staff_id_.equals("")) {
                Connection con = db.connectDB();
                if (staff_name_.equals("")) {
                    String query = "SELECT employee_name FROM employee WHERE employee_id=" + staff_id_;
                    preparedStatement = con.prepareStatement(query);
                    ResultSet rs = preparedStatement.executeQuery();
                    rs.next();
                    staff_name_string = rs.getString("staff_name");
                    rs.close();
                } else {
                    staff_name_string = staff_name_;
                }
                if (staff_gender_.equals("")) {
                    String query = "SELECT employee_gender FROM employee WHERE employee_id=" + staff_id_;
                    preparedStatement = con.prepareStatement(query);
                    ResultSet rs = preparedStatement.executeQuery();
                    rs.next();
                    staff_gender_string = rs.getString("gender");
                    rs.close();
                } else {
                    staff_gender_string = staff_gender_;
                }
                if (staff_address_.equals("")) {
                    String query = "SELECT employee_address FROM employee WHERE employee_id=" + staff_id_;
                    preparedStatement = con.prepareStatement(query);
                    ResultSet rs = preparedStatement.executeQuery();
                    rs.next();
                    staff_address_string = rs.getString("address");
                    rs.close();
                } else {
                    staff_address_string = staff_address_;
                }
                if (staff_telp_.equals("")) {
                    String query = "SELECT employee_telp_no FROM employee WHERE employee_id=" + staff_id_;
                    preparedStatement = con.prepareStatement(query);
                    ResultSet rs = preparedStatement.executeQuery();
                    rs.next();
                    staff_telp_string = rs.getString("telp_no");
                    rs.close();
                } else {
                    staff_telp_string = staff_telp_;
                }
                String query = "UPDATE employee SET employee_name=?, employeeg_gender=?, employee_address=?, employee_telp_no=? " + "WHERE employee_id=?";
                staff_id_int = Integer.parseInt(staff_id_);

                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, staff_name_string);
                preparedStatement.setString(2, staff_gender_string);
                preparedStatement.setString(3, staff_address_string);
                preparedStatement.setString(4, staff_telp_string);
                preparedStatement.setInt(5, staff_id_int);

                if (preparedStatement.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Staff Data Updated");
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Something is missing!");
                alert.setContentText("Check your syntax!");

                alert.showAndWait();
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        finally
        {
            staffId.clear();
            staffName.clear();
            //gender.clear();
            address.clear();
            telephone.clear();
            preparedStatement.execute();
            preparedStatement.close();
            refresh();
        }
    }

    @FXML
    public void backButtonAction(MouseEvent event) throws SQLException {
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
