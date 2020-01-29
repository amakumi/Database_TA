/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author m
 */
public class Connector
{
    private String url ="jdbc:mysql://dbta.1ez.xyz/6_foodhall" + "?autoReconnect=true&useSSL=false";
    private String user = "MAR8351";
    private String pass = "4fmtl3p6";

    public Connection Connect()
    {
        try
        {
            Connection conn;
            conn = DriverManager.getConnection(url, user, pass);

            if (conn != null)
            {
                try
                {
                    conn.close();
                    System.out.println("Connection Terminated!");
                }
                catch (SQLException e)
                {
                    System.err.println(e.getMessage());
                }
            }

            return conn;
        }

        catch (SQLException ex)
        {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }

        return null;
    }
}

