/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import View.VusuarioMain;
import View.VusuarioList;
import View.VusuarioDelete;
import View.VusuarioAdd;
import View.VusuarioEdit;
import java.awt.*;
import java.awt.event.ActionListener;
import dao.UsuarioDao;
import modelo.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Sebastian
 */
public class UsuarioController {
    
    VusuarioMain vUsuariosMain;
    VusuarioAdd vUsuariosAdd;
    VusuarioList vUsuariosList;
    VusuarioDelete vUsuariosDelete;
    VusuarioEdit vUsuariosEdit;

    public void mostrarUsuariosCrud() {
        vUsuariosMain = new VusuarioMain();

        vUsuariosMain.btnCrearUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                addUsuario();
            }
        }
        );

        vUsuariosMain.btnEliminarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                eliminarUsuario();
            }
        }
        );

        vUsuariosMain.btnVerUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                listarUsuarios();
            }
        }
        );

        vUsuariosMain.btnActualizarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                editUsuario();
            }
        }
        );

        vUsuariosMain.setVisible(true);
    }

    public void addUsuario() {
        vUsuariosAdd = new VusuarioAdd();
        vUsuariosAdd.btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if(vUsuariosAdd.txtAlias.getText().equals("")|| vUsuariosAdd.txtNombre.getText().equals("")||
                   vUsuariosAdd.txtApellidos.getText().equals("")||vUsuariosAdd.txtEmail.getText().equals("")||
                   vUsuariosAdd.txtCelular.getText().equals("")||vUsuariosAdd.psdClave.getPassword().equals("")){
                    
                    JOptionPane.showMessageDialog(vUsuariosAdd, "Todos los datos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                else{
                    
                if(vUsuariosAdd.txtEmail.getText().contains("@")&&vUsuariosAdd.txtEmail.getText().contains(".")){
                
                try {
                    Usuario user = new Usuario(
                            vUsuariosAdd.txtAlias.getText(),
                            vUsuariosAdd.txtNombre.getText(),
                            vUsuariosAdd.txtApellidos.getText(),
                            vUsuariosAdd.txtEmail.getText(),
                            vUsuariosAdd.txtCelular.getText(),
                            String.copyValueOf(vUsuariosAdd.psdClave.getPassword())
                    );
                    UsuarioDao.save(user);
                    vUsuariosAdd.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Usuario registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(vUsuariosAdd, "Error al registrar los datos", "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                    else {
                        JOptionPane.showMessageDialog(vUsuariosAdd, "Por favor ingrese un email valido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        vUsuariosAdd.setVisible(true);

    }

    public void eliminarUsuario() {

        vUsuariosDelete = new VusuarioDelete();
        vUsuariosDelete.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if(vUsuariosDelete.txtUsuario.getText().equals("")||vUsuariosDelete.txtClave.getText().equals("")){
                    
                    JOptionPane.showMessageDialog(null, "Por favor ingrese usuario y contraseña", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                else {
                
                try {
                    Usuario user = UsuarioDao.readOne(vUsuariosDelete.txtUsuario.getText());
                    
                    if( user.getClave().equals(vUsuariosDelete.txtClave.getText())){
                    
                    if (user != null) {
                        UsuarioDao.delete(user);
                        vUsuariosDelete.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Usuario eliminado", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "El usaurio no existe" , "No encotrado", JOptionPane.WARNING_MESSAGE);
                    }
                    
                    } else{
                        
                        JOptionPane.showMessageDialog(null, "La combinacion usuario - contraseña no existe" , "No encotrado", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            }
        });
        vUsuariosDelete.setVisible(true);

    }
   
        
    public void listarUsuarios() {
        try {
            ArrayList<Usuario> usuarios = UsuarioDao.getAll();
            String[] encabezados = {"Usuario", "Nombre", "Apellido", "Email", "Celular"};
            String[][] rows = new String[usuarios.size()][encabezados.length];

            for (int i = 0; i < usuarios.size(); i++) {
                rows[i] = usuarios.get(i).getAsRow();
            }
            vUsuariosList = new VusuarioList(encabezados, rows);
            vUsuariosList.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editUsuario() {
        vUsuariosEdit = new VusuarioEdit();
        vUsuariosEdit.btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if(vUsuariosEdit.txtUsuarioOriginal.getText().equals("")){
                    
                    JOptionPane.showMessageDialog(null, "Por favor ingrese el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                else{
                    
                try {
                    Usuario user = UsuarioDao.readOne(vUsuariosEdit.txtUsuarioOriginal.getText());
                    if (user == null) {
                        JOptionPane.showMessageDialog(null, "El usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        vUsuariosEdit.panelBusqueda.setEnabled(false);
                        vUsuariosEdit.txtUsername.setText(user.getAlias());
                        vUsuariosEdit.txtNombres.setText(user.getNombre());
                        vUsuariosEdit.txtApellidos.setText(user.getApellido());
                        vUsuariosEdit.txtCelular.setText(user.getCelular());
                        vUsuariosEdit.txtEmail.setText(user.getEmail());
                        vUsuariosEdit.passwdClave.setText(user.getClave());

                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al consultar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                }
            }
        });

        vUsuariosEdit.btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Usuario user = new Usuario(vUsuariosEdit.txtUsername.getText(),
                        vUsuariosEdit.txtNombres.getText(),
                        vUsuariosEdit.txtApellidos.getText(),
                        vUsuariosEdit.txtEmail.getText(),
                        vUsuariosEdit.txtCelular.getText(),
                        vUsuariosEdit.passwdClave.getText());
                
                if(vUsuariosEdit.txtUsername.getText().equals("")|| vUsuariosEdit.txtNombres.getText().equals("")||
                   vUsuariosEdit.txtApellidos.getText().equals("")||vUsuariosEdit.txtEmail.getText().equals("")||
                  vUsuariosEdit.passwdClave.getText().equals("")||vUsuariosEdit.txtCelular.getText().equals("")){
                    
                    JOptionPane.showMessageDialog(vUsuariosEdit, "Todos los datos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                else{
                    
                    if(vUsuariosEdit.txtEmail.getText().contains("@")&&vUsuariosEdit.txtEmail.getText().contains(".")){
                
                try {
                    UsuarioDao.update(user, vUsuariosEdit.txtUsuarioOriginal.getText());
                    JOptionPane.showMessageDialog(null, "Actualizado Correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    vUsuariosEdit.setVisible(false);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar la informacion", "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                    else {
                        JOptionPane.showMessageDialog(vUsuariosEdit, "Por favor ingrese un email valido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }   
        });

        vUsuariosEdit.setVisible(true);
    }

    
}
