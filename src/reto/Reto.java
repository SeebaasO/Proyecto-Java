/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto;
import controlador.UsuarioController;
/**
 *
 * @author Sebastian
 */
public class Reto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        UsuarioController controlador = new UsuarioController();
       controlador.mostrarUsuariosCrud();
    }
    
}
