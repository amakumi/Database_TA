package Class;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;


public class Transaction_Detail
{
    private final IntegerProperty transaction_details_id;
    private final IntegerProperty transaction_id;
    private final StringProperty product_name;
    private final IntegerProperty quantity;

    public Transaction_Detail(Integer transaction_details_id, Integer transaction_id, String product_name, Integer quantity)
    {
        this.transaction_details_id = new SimpleIntegerProperty(transaction_details_id);
        this.transaction_id = new SimpleIntegerProperty(transaction_id);
        this.product_name = new SimpleStringProperty(product_name);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public int getTransaction_details_id() {
        return transaction_details_id.get();
    }

    public void setTransaction_details_id(Integer value) {
        transaction_details_id.set(value);
    }

    public int getTransaction_id() {
        return transaction_id.get();
    }

    public void setTransaction_id(Integer value) {
        transaction_id.set(value);
    }

    public String getProduct_name() {
        return product_name.get();
    }

    public void setProduct_name(String value) {
        product_name.set(value);

    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(Integer value) {
        quantity.set(value);
    }
}










/*public class Transaction_Detail
{
    private final IntegerProperty bill_id;
    private final IntegerProperty bill_no;
    private final StringProperty product_name;
    private final IntegerProperty product_quantity;

    public Transaction_Detail(Integer bill_id, Integer bill_no, String item_name, Integer quantity)
    {
        this.bill_id = new SimpleIntegerProperty(bill_id);
        this.bill_no = new SimpleIntegerProperty(bill_no);
        this.product_name = new SimpleStringProperty(item_name);
        this.product_quantity = new SimpleIntegerProperty(quantity);
    }

    public int getBill_id() {
        return bill_id.get();
    }

    public void setBill_id(Integer value) {
        bill_id.set(value);
    }

    public int getBill_no() {
        return bill_no.get();
    }

    public void setBill_no(Integer value) {
        bill_no.set(value);
    }

    public String getItem_name() {
        return product_name.get();
    }

    public void setItem_name(String value) {
        product_name.set(value);

    }

    public int getQuantity() {
        return product_quantity.get();
    }

    public String getQtyStr() {
        return String.valueOf(product_quantity);
    }


    public void setQuantity(Integer value) {
        product_quantity.set(value);
    }
}*/
