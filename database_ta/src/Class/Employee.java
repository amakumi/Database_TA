package Class;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;


public class Employee
{
    @FXML
    private final IntegerProperty employee_id;
    private final StringProperty employee_name;
    private final StringProperty employee_gender;
    private final StringProperty employee_address;
    private final StringProperty employee_password;
    private final IntegerProperty employee_telp;

    public Employee(Integer employee_id, String employee_name, String employee_gender, String employee_address, Integer employee_telp, String employee_password) {
        this.employee_id = new SimpleIntegerProperty(employee_id);
        this.employee_name = new SimpleStringProperty(employee_name);
        this.employee_gender = new SimpleStringProperty(employee_gender);
        this.employee_address = new SimpleStringProperty(employee_address);
        this.employee_telp = new SimpleIntegerProperty(employee_telp);
        this.employee_password = new SimpleStringProperty(employee_password);
    }

    public int getEmployee_id() {
        return employee_id.get();
    }

    public void setEmployee_id(Integer value) {
        employee_id.set(value);
    }


    public String getEmployee_name() {
        return employee_name.get();
    }

    public void setEmployee_name(String value) {
        employee_name.set(value);
    }


    public String getEmployee_gender() {
        return employee_gender.get();
    }

    public void setEmployee_gender(String value) {
        employee_gender.set(value);
    }


    public String getEmployee_address() {
        return employee_address.get();
    }

    public void setEmployee_address(String value) {
        employee_address.set(value);
    }


    public int getEmployee_telp() {
        return employee_telp.get();
    }

    public void setEmployee_telp(int value) {
        employee_telp.set(value);
    }

    public String getEmployee_password() {
        return employee_password.get();
    }

    public void setEmployee_password(String value) {
        employee_password.set(value);
    }


}
