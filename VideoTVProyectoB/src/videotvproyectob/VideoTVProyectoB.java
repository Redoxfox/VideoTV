/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videotvproyectob;
import Modelo.*;
import Controlador.*;
import Vista.*;
public class VideoTVProyectoB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         FromLogin ViewLogin = new FromLogin();
         //RegistroDAO ModelRegistro = new RegistroDAO();
         //ControladorRegistro ControllerRegistro = new ControladorRegistro(ViewRegistro,ModelRegistro);
         ViewLogin.setVisible(true);
         ViewLogin.setLocationRelativeTo(null);
    }
    
}
