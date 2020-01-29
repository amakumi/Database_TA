package DB;

import javafx.scene.control.TextField;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB
{
    private Connection conn;
    private Statement stm;
    private ResultSet rs;

    private String url="jdbc:mysql://dbta.1ez.xyz/6_foodhall" + "?autoReconnect=true&useSSL=false";
    private String user = "MAR8351";
    private String pass = "4fmtl3p6";
    private String query;
    private boolean cond;

    /*public DB(String serverLink, String dbName, String user, String pass)
    {
        this.url = "jdbc:mysql://"+serverLink+"/"+dbName + "?autoReconnect=true&useSSL=false";
        this.user = user;
        this.pass = pass;
    }*/
    public Connection DBLogin()
    {
        try
        {
            //Connection conn;
            conn = DriverManager.getConnection(url, user, pass);
            return conn;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return null;
    }


    public Connection connectDB()
    {
        try
        {
            conn = DriverManager.getConnection(url, user, pass);
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            return conn;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return null;
    }

    public void close()
    {
        try
        {
            conn.close();
            System.out.println("DB: Disconnected!");
        }
        catch (SQLException e)
        {
            System.out.println("DB: "+e.getMessage());
        }
    }


    public void insert(String name) throws SQLException
    {
        this.query = "Insert Into product(product_name) values (\""+name+"\")";
        stm.execute(query);
    }

    public void insertAll(String id, String name, String qty, String price, String type, String notes) throws SQLException
    {
        this.query = "INSERT INTO product(product_id, product_name, product_quantity, price, type) VALUES (\""+id+"\",\""+name+"\",\""+qty+"\",\""+price+"\",\""+type+"\",\""+notes+"\")";
        stm.execute(query);
    }

    public void updateAll(String id, String name, String qty, String price) throws SQLException
    {
        String queryUpdate = "UPDATE product SET product_id=?, product_name=?, product_quantity=?, price=? WHERE product_id=?";
        try (PreparedStatement pst = conn.prepareStatement(queryUpdate);)
        {
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, qty);
            pst.setString(4, price);
            pst.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("DB: "+e.getMessage());
        }


    }

    public ResultSet selectAll() throws SQLException
    {
        this.query = "SELECT * from product ORDER BY product_id";
        stm.execute(query);
        return null;
    }

    public ResultSet refreshEmployee() throws SQLException
    {
        this.query = "SELECT * FROM employee ORDER BY employee_id";
        stm.execute(query);
        return null;
    }

    public void updateName (String name, TextField prev) throws SQLException
    {
        this.query = "Update product set product_name = \""+name+"\" Where product_name = \""+prev+"\"";
        stm.execute(query);
    }

    public void delete (String id) throws SQLException
    {
        this.query = "Delete from product where product_id = \""+id+"\"";
        stm.execute(query);
    }

    /////

    public void updateID(String id, String prev) throws SQLException
    {
        this.query = "Update product set product_id = \""+id+"\" Where product_id = \""+prev+"\"";
        stm.execute(query);
    }

    public void deleteID(String id) throws SQLException
    {
        this.query = "Delete from product where product_id = \""+id+"\"";
        stm.execute(query);
    }

    public void deleteName(String id) throws SQLException
    {
        this.query = "Delete from product where product_name = \""+id+"\"";
        stm.execute(query);
    }

    /////

    public void updateQTY(String qty, String prev) throws SQLException
    {
        this.query = "Update product set product_quantity = \""+qty+"\" Where product_quantity = \""+prev+"\"";
        stm.execute(query);
    }

    public void deleteQTY(String qty) throws SQLException
    {
        this.query = "Delete from product where product_quantity = \""+qty+"\"";
        stm.execute(query);
    }

    /////

    public void updateprice(String price, String prev) throws SQLException
    {
        this.query = "Update product set price = \""+price+"\" Where price = \""+prev+"\"";
        stm.execute(query);
    }

    public void deleteprice(String price) throws SQLException
    {
        this.query = "Delete from product where price = \""+price+"\"";
        stm.execute(query);
    }

    /////



    public String next()
    {
        try
        {
            rs.next();
            // set column index
            return rs.getString(1);
        }
        catch(Exception e)
        {
            return "NULL";
        }
    }

    public void SelectAll() throws SQLException
    {
        this.query = "SELECT * from bill";
        rs = stm.executeQuery(query);
    }
}
