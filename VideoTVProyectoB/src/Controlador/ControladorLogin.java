/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.RegistroDAO;
import Modelo.Validador_Registro;
import Modelo.hash;
import Vista.FromLogin;
import Vista.FromRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author tux-carlos
 */
public class ControladorLogin implements ActionListener {
    
     FromLogin ViewLogin = new FromLogin();
     RegistroDAO ModelRegistro = new RegistroDAO();
     hash ModelHast = new hash();
     Validador_Registro ModelValidador = new Validador_Registro();
     
     public ControladorLogin(FromLogin ViewLogin,RegistroDAO ModelRegistro){
         this.ModelRegistro=ModelRegistro;
         this.ViewLogin =ViewLogin;
         this.ViewLogin.Btn_Iniciar.addActionListener(this);
     }
    
    public void actionPerformed(ActionEvent e){
         if (e.getSource()== ViewLogin.Btn_Iniciar) {
            String identificacion = ViewLogin.Txt_Usuario.getText();
            char[] pass_word = ViewLogin.Txt_Password.getPassword();
            String password = new String (ViewLogin.Txt_Password.getPassword());
            boolean vacio_identificacion = ModelValidador.Validar_Campos_Vacios(identificacion);
            boolean vacio_password = ModelValidador.Validar_Campos_Vacios(password);
           
            int num_registros_identficacion = ModelRegistro.ExisteUsuario(identificacion);
            String PassworBD = ModelRegistro.PasswordBD(identificacion);
            boolean Validar_Contraseña = ModelValidador.Contraseña_Valida(password,PassworBD);
            
            boolean Validacion_Vacios= true;
            boolean Validacion_contraseña = true;
            boolean Validacion_Exist_Usuario= true;
            
            if (num_registros_identficacion==0) {
                Validacion_Exist_Usuario= false;
                JOptionPane.showMessageDialog(null, "El usuario no se encuentra registrado en la base de datos" );
            } else {
                Validacion_Exist_Usuario= true;
            }
            
            if (Validar_Contraseña==true) {
                 Validacion_contraseña = true;
            } else {
                Validacion_contraseña =false;
                String micampo2="La contraseña es incorrecta";
                JOptionPane.showMessageDialog(null, micampo2 ); 
            }
            
            if (vacio_identificacion==true  || vacio_password==true) {
                Validacion_Vacios= false;
                String campos = "Existen campos vacios. Por favor diligenciar!";
                JOptionPane.showMessageDialog(null, campos );
            } else {
                Validacion_Vacios= true;
            }
        
            
            if (Validacion_contraseña==true  && Validacion_Vacios==true & Validacion_Exist_Usuario==true) {
                ViewLogin.setVisible(false);
                FromRegistro ViewRegistro = new FromRegistro();
                RegistroDAO ModelRegistro = new RegistroDAO();
                ControladorRegistro ControllerRegistro = new ControladorRegistro(ViewRegistro,ModelRegistro);
                ViewRegistro.setVisible(true);
                ViewRegistro.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(null, "No se puede completar registro por errores de registro" );
            }
           
        }
        
    }
}
