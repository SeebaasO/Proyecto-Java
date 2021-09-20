/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Sebastian
 */
public class Usuario {
    
    private String alias;
    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private String clave;

    public Usuario(String alias, String nombre, String apellido, String email, String celular, String clave) {
        this.alias = alias;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.celular = celular;
        this.clave = clave;
    }

    public Usuario(String alias) {
        this.alias = alias;
    }
    
    public String [] getAsRow(){    
           return new String [] {this.alias, this.nombre, this.apellido, this.email, this.celular, this.clave};
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
}
