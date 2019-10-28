/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.*;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author tux-carlos
 */
public class ControladorRegistro implements ActionListener {
    
     FromRegistro ViewRegistro = new FromRegistro();
     RegistroDAO ModelRegistro = new RegistroDAO();
     hash ModelHast = new hash();
     Validador_Registro ModelValidador = new Validador_Registro();
     
     public ControladorRegistro(FromRegistro ViewRegistro,RegistroDAO ModelRegistro){
         this.ModelRegistro=ModelRegistro;
         this.ViewRegistro =ViewRegistro;
         this.ViewRegistro.Btn_Nuevo.addActionListener(this);
         this.ViewRegistro.Btn_Editar.addActionListener(this);
         this.ViewRegistro.Btn_Consultar.addActionListener(this);
         this.ViewRegistro.Btn_Eliminar.addActionListener(this);
     }
    
    public void actionPerformed(ActionEvent e){
        if (e.getSource()== ViewRegistro.Btn_Nuevo) {
            String identificacion = ViewRegistro.Tb_identificacion.getText();
            String nombre = ViewRegistro.Tb_Nombre.getText();
            String apellido = ViewRegistro.Tb_Apellido.getText();
            String fecha_nac = ViewRegistro.Tb_FechaNacimiento.getText();
            String direccion = ViewRegistro.Tb_Direccion.getText();
            String telefono = ViewRegistro.Tb_Telefono.getText();
            //String tipo_user = ViewRegistro.Tb_TipoUser.getText();
            String correo = ViewRegistro.Tb_Correo.getText();
            char[] pass_word = ViewRegistro.Tb_Password.getPassword();
            char[] con_pass_word = ViewRegistro.Tb_Conpassword.getPassword();
            String password = new String (ViewRegistro.Tb_Password.getPassword());
            String conpassword = new String (ViewRegistro.Tb_Conpassword.getPassword());
            boolean vacio_identificacion = ModelValidador.Validar_Campos_Vacios(identificacion);
            boolean vacio_nombre = ModelValidador.Validar_Campos_Vacios(nombre);
            boolean vacio_apellido = ModelValidador.Validar_Campos_Vacios(apellido);
            boolean vacio_fecha_nac = ModelValidador.Validar_Campos_Vacios(fecha_nac);
            boolean vacio_direccion = ModelValidador.Validar_Campos_Vacios(direccion);
            boolean vacio_telefono = ModelValidador.Validar_Campos_Vacios(telefono);
            boolean vacio_correo = ModelValidador.Validar_Campos_Vacios(correo);
            boolean vacio_password = ModelValidador.Validar_Campos_Vacios(password);
            boolean vacio_conpassword = ModelValidador.Validar_Campos_Vacios(conpassword);
            String ContraseñaCifrada="";
            
            boolean Validacion_contraseña = true;
            boolean Validacion_Correo= true;
            boolean Validacion_Vacios= true;
            boolean Validacion_Exist_Usuario= true;
            
            int num_registros_identficacion = ModelRegistro.ExisteUsuario(identificacion);
            boolean Confirmar_Contraseña = ModelValidador.Validar_Contraseña(pass_word,con_pass_word);
            if (Confirmar_Contraseña==true) {
                 ContraseñaCifrada=ModelValidador.md5(password);
                 Validacion_contraseña = true;
            } else {
                Validacion_contraseña =false;
                String micampo2="Las contraseñas no coinciden";
                JOptionPane.showMessageDialog(null, micampo2 ); 
            }
            
            boolean ValidarCorreo=ModelValidador.ValidarEmail(correo);
            if (ValidarCorreo) {
                Validacion_Correo= true;
            } else {
                Validacion_Correo= false;
                String micampo2="Correo invalido";
                JOptionPane.showMessageDialog(null, micampo2 );        
            }
            
            if (vacio_identificacion==true  || vacio_nombre==true || vacio_apellido==true || vacio_fecha_nac==true || vacio_direccion==true || vacio_telefono==true || vacio_correo==true || vacio_password==true || vacio_conpassword==true) {
                Validacion_Vacios= false;
                String campos = "Existen campos vacios. Por favor diligenciar!";
                JOptionPane.showMessageDialog(null, campos );
                
            } else {
                Validacion_Vacios= true;
            }
            
            if (num_registros_identficacion==0) {
                Validacion_Exist_Usuario= true;
            } else {
                JOptionPane.showMessageDialog(null, "El usuario ya se encuentra registrado" );
                Validacion_Exist_Usuario= false;
            }
            
            if (Validacion_contraseña==true && Validacion_Correo== true && Validacion_Vacios==true & Validacion_Exist_Usuario==true) {
               // int foo = Integer.parseInt(tipo_user);
               int foo = 1;
                password= ContraseñaCifrada;
                String rtaRegistro = ModelRegistro.InsertRegistro(identificacion, nombre, apellido, fecha_nac, direccion, telefono, foo, correo, password);
                if (rtaRegistro!=null) {
                    JOptionPane.showMessageDialog(null, rtaRegistro );
                }else {
                    JOptionPane.showMessageDialog(null, "Error de registro" );
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede completar registro por errores de registro" );
            }
           
        }//Cierre evento
        
    }
}
