/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import modelo.Usuario;
import Conexion.Conexion;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Sebastian
 */
public class UsuarioDao {
    
    public static void save(Usuario user) throws SQLException{
        String sql = " INSERT INTO usuario "
                + "(usuario_alias, "
                + "usuario_nombre, "
                + "usuario_apellido, "
                + "usuario_email, "
                + "usuario_celular, "
                + "usuario_clave)"
                + " VALUES (?,?,?,?,?,?)";
    
    Connection conn = Conexion.getConnection();
    PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getAlias());
        statement.setString(2, user.getNombre());
        statement.setString(3, user.getApellido());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getCelular());
        statement.setString(6, user.getClave());
        statement.executeUpdate();
    }
    
    public static void update(Usuario user, String originalAlias) throws SQLException{
        String sql = " UPDATE usuario "
                + " SET usuario_alias = ? , "
                + " usuario_nombre = ? , "
                + " usuario_apellido = ? , "
                + " usuario_email = ? , "
                + " usuario_celular = ? , "
                + " usuario_clave = ?  "
                + " WHERE usuario_alias = ? ";
                
    
    Connection conn = Conexion.getConnection();
    PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getAlias());
        statement.setString(2, user.getNombre());
        statement.setString(3, user.getApellido());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getCelular());
        statement.setString(6, user.getClave());
        statement.setString(7, originalAlias);
        statement.executeUpdate();
    }
    
    public static Usuario readOne(String alias) throws SQLException {
        
        String sql = " SELECT usuario_alias, usuario_nombre, usuario_apellido, usuario_email, usuario_celular, usuario_clave " 
                + " FROM usuario WHERE usuario_alias = ? ";
        Connection conn = Conexion.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, alias);
        ResultSet result = statement.executeQuery();
        while (result.next()){
            Usuario user = new Usuario(
                result.getString(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5),
                result.getString(6));
            
        return user;
        }
    return null; 
    }
    
    public static void delete(Usuario user) throws SQLException{
        String sql = " DELETE FROM usuario WHERE usuario_alias = ? ";
                
    Connection conn = Conexion.getConnection();
    PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getAlias());
        statement.executeUpdate();
        
    }
    
    public static ArrayList<Usuario> getAll() throws SQLException{
        String sql = " SELECT * FROM usuario ";

        Connection conn = Conexion.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        ArrayList<Usuario> listaUsuarios = new ArrayList();
        while (result.next()){
            Usuario user = new Usuario(
                result.getString(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5),
                result.getString(6));

            listaUsuarios.add(user);
        }
        return listaUsuarios;   

    }

}
