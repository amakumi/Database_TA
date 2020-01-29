package Class;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;


public class Company
{
    @FXML
    private final IntegerProperty company_id;
    private final StringProperty company_name;
    private final StringProperty company_tax_number;
    private final StringProperty company_address;
    private final StringProperty company_building;
    private final StringProperty company_city;

    public Company(Integer company_id, String companyName, String company_tax_number, String companyAddress, String company_building, String company_city)
    {
        this.company_id = new SimpleIntegerProperty(company_id);
        this.company_name = new SimpleStringProperty(companyName);
        this.company_tax_number = new SimpleStringProperty(company_tax_number);
        this.company_address = new SimpleStringProperty(companyAddress);
        this.company_building = new SimpleStringProperty(company_building);
        this.company_city = new SimpleStringProperty(company_city);
    }

    public int getCompany_id() {
        return company_id.get();
    }

    public void setCompany_id(Integer value) {
        company_id.set(value);
    }

    public String getCompany_name() {
        return company_name.get();
    }

    public void setCompany_name(String value) {
        company_name.set(value);
    }

    public String getCompany_tax_number() {
        return company_tax_number.get();
    }

    public void setCompany_tax_number(String value) {
        company_tax_number.set(value);
    }

    public String getCompany_address() {
        return company_address.get();
    }

    public void setCompany_address(String value) {
        company_address.set(value);
    }

    public String getCompany_building() {
        return company_building.get();
    }

    public void setCompany_building(String value) {
        company_building.set(value);
    }

    public String getCompany_city() {
        return company_city.get();
    }

    public void setCompany_city(String value) {
        company_city.set(value);
    }
}
