
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;



public class Conexion {
    public static final String URL = "jdbc:mysql://localhost:3306/videotvsa?serverTimezone=UTC";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456";
    
    public static Connection getConection()
    {
        Connection con = null;
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
           //JOptionPane.showMessageDialog(null, "Conexion exitosa");
        } catch (Exception e) {
           System.out.println(e);
        }        
        return con;
    }

    
  }