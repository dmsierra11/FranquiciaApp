/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franquiciaapp;

import Sockets.Replicador;
import ventanas.MenuFranquicia;
import Sockets.Servidor;

/**
 *
 * @author daniel
 */
public class FranquiciaApp {

    /**
     * @param args the command line arguments
     * 
     */
    public static boolean sinConexion = false;
    
    public static void main(String[] args) {
        // TODO code application logic here
        MenuFranquicia menu = new MenuFranquicia();
        menu.setVisible(true);

        Servidor servidor = new Servidor(args[0]);
        new Thread(servidor).start();
         
        //new Thread(new Replicador("Estoy arriba")).start();
    }
    
        
}
