/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
import java.sql.*;
/**
 *
 * @author Sebastian
 */
public class Conexion {
    
    private static String dbURL = "jdbc:mysql://localhost:3306/reto";
    private static String username = "root";
    private static String password = "12345";
    
    public static Connection getConnection(){
        try{
            Connection conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null){
                System.out.println("Conectado a reto");
                return conn;
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        
        return null;
    }
    
}
