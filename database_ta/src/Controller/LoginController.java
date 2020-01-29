package Controller;

/*

    author m
 */

import DB.DB;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @author m
 */
public class LoginController implements Initializable
{
    @FXML
    private Label lblErrors;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnSignin;
    @FXML
    private Button btnSignup;
    @FXML
    private Button logOff;

    public String txt_id;
    /// --

    private DB db;
    private Connection con1 = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //Connection con = null;
        Connection con = db.connectDB();
        System.out.println("initializing..");
        // TODO
        if (con == null)
        {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Server Error : Check Your Connection");
        }
        else
        {
            lblErrors.setTextFill(Color.GREEN);
            lblErrors.setText("Server is up : Good to go");
        }


    }

    @FXML
    public void close(MouseEvent event) throws SQLException
    {
        Connection con = db.connectDB();
        if (event.getSource() == logOff)
        {
            if(con != null)
            {
                try
                {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    con.close();
                    System.out.println(".\n.\n.\nConnection Terminated!");
                }
                catch (SQLException ex)
                {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    @FXML
    public void signinButtonAction(MouseEvent event) throws IOException
    {
        if (event.getSource() == btnSignin)
        {
            //login here
            if (logIn().equals("Success"))
            {
                try
                {
                    String staffId = txtID.getText();
                    if (!staffId.equals("1"))
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
                    else if (staffId.equals("1"))
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
                }
                catch (IOException ex)
                {
                    System.err.println(ex.getMessage());
                }

            }
        }
    }

    @FXML
    public void signUpButtonAction(MouseEvent event)
    {
        if (event.getSource() == btnSignup)
        {
            try
            {
                //add you loading or delays - ;-)
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/SignUp.fxml")));
                stage.setScene(scene);
                stage.show();

            }
            catch (IOException ex)
            {
                System.out.println("test");
                System.err.println(ex.getMessage());
                System.out.println("LOGIN CTRLLER???");
                System.out.println("WHY NOT WORKING LAA");
            }
        }
    }

    public LoginController()
    {
        db = new DB();
    }

    private String logIn()
    {
        String status = "Success";
        String staff_id = txtID.getText();
        String password = txtPassword.getText();
        if(staff_id.isEmpty() || password.isEmpty())
        {
            setLblError(Color.TOMATO, "Empty credentials");
            status = "Error";
        }
        else
            {
            //query
            String sql = "SELECT * FROM employee Where employee_id = ? and employee_password = ?";
            try
            {
                Connection con = db.connectDB();
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, staff_id);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.next())
                {
                    setLblError(Color.TOMATO, "Enter Correct ID/Password");
                    status = "Error";
                }
                else
                    {
                    setLblError(Color.GREEN, "Login Successful.. Redirecting..");
                }
            }
            catch (SQLException ex)
            {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        return status;
    }



    private void setLblError(Color color, String text)
    {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }
}
