package Class;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product
{
    private final IntegerProperty product_id;
    private final StringProperty product_name;
    private final IntegerProperty product_quantity;
    private final IntegerProperty price;
    private final StringProperty type;
    private final StringProperty notes;

    public Product(int item_id, String item_name, int item_qty, int item_price, String type, String notes)
    {
        this.product_id = new SimpleIntegerProperty(item_id);
        this.product_name = new SimpleStringProperty(item_name);
        this.product_quantity = new SimpleIntegerProperty(item_qty);
        this.price = new SimpleIntegerProperty(item_price);
        this.type = new SimpleStringProperty(type);
        this.notes = new SimpleStringProperty(notes);

    }

    public int getItem_id()
    {
        return product_id.get();
    }

    public void setItem_id(Integer value)
    {
        product_id.set(value);
    }

    public String getProduct_name()
    {
        return product_name.get();
    }

    public void setProduct_name(String value)
    {
        product_name.set(value);
    }

    public int getProduct_quantity()
    {
        return product_quantity.get();
    }

    public void setProduct_quantity(int value)
    {
        product_quantity.set(value);
    }

    public int getPrice()
    {
        return price.get();
    }

    public void setPrice(int value)
    {
        price.set(value);
    }

    public String getType()
    {
        return type.get();
    }

    public void setType(String value)
    {
        type.set(value);
    }

    public String getNotes()
    {
        return notes.get();
    }

    public void setNotes(String value)
    {
        notes.set(value);
    }
}
