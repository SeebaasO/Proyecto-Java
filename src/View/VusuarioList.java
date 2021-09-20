/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Sebastian
 */
public class VusuarioList extends JFrame{

    
    String [] encabezados;
    String [][] valores;
      
      public VusuarioList(String [] encabezados,String [][] valores) {
            setTitle("Lista de Usuarios");
            this.encabezados = encabezados;
            this.valores = valores;
            JTable table = new JTable(valores, encabezados);
            JScrollPane jsp = new JScrollPane(table);
            this.add(jsp);
            pack();
            setVisible(true);
       }
    
}
