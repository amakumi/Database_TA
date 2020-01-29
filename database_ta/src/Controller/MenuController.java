package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author m
 */

public class MenuController
{

    @FXML
    private Button btnStaff;
    @FXML
    private Button btnTransDetail;
    @FXML
    private Button btnProduct;
    @FXML
    private Button btnCompany;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnInfo;

    @FXML
    public void transactionButtonAction(MouseEvent event) throws IOException
    {
        if (event.getSource() == btnStaff)
        {
            try
            {
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
                System.err.println(ex.getMessage());
                System.out.println("Staff Function Not Opening");

            }
        }
    }

    @FXML
    public void transDetailButtonAction(MouseEvent event) throws IOException
    {
        if (event.getSource() == btnTransDetail)
        {
            try
            {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/TransactionDetail.fxml")));
                stage.setScene(scene);
                stage.show();

            }
            catch (IOException ex)
            {
                System.err.println(ex.getMessage());
                System.out.println("TransDetail Function Not Opening");

            }
        }
    }

    @FXML
    public void productButtonAction(MouseEvent event) throws IOException
    {
        if (event.getSource() == btnProduct)
        {
            try
            {
                //add you loading or delays - ;-)
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/View.fxml")));
                stage.setScene(scene);
                stage.show();

            }
            catch (IOException ex)
            {
                System.err.println(ex.getMessage());
                System.out.println("Product Function Not Opening");
            }
        }
    }

    @FXML
    public void companyButtonAction(MouseEvent event) throws IOException
    {
        if (event.getSource() == btnCompany)
        {
            try
            {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Company.fxml")));
                stage.setScene(scene);
                stage.show();

            }
            catch (IOException ex)
            {
                System.err.println(ex.getMessage());
                System.out.println("Company Function Not Opening");

            }
        }
    }

    @FXML
    public void exitButtonAction(MouseEvent event) throws IOException
    {
        if (event.getSource() == btnExit)
        {
            try
            {
                //add you loading or delays - ;-)
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Login.fxml")));
                stage.setScene(scene);
                stage.show();

            }
            catch (IOException ex)
            {
                System.err.println(ex.getMessage());
                System.out.println("Exit Function Not Opening");

            }
        }
    }

    @FXML
    public void infoButtonAction(MouseEvent event) throws IOException
    {
        if (event.getSource() == btnInfo)
        {
            try
            {
                //add you loading or delays - ;-)
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                //stage.setMaximized(true);
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/Info.fxml")));
                stage.setScene(scene);
                stage.show();

            }
            catch (IOException ex)
            {
                System.err.println(ex.getMessage());
                System.out.println("Info Function Not Opening");

            }
        }
    }
}
