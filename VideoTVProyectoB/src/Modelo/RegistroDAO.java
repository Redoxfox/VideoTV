/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author tux-carlos
 */
public class RegistroDAO {
    Conexion conexion;
    
    public RegistroDAO() {
        conexion = new Conexion();
    }
    
    public int ExisteUsuario (String usuario){
            Connection accesoBD = conexion.getConection();
            PreparedStatement ps = null;
            ResultSet res = null;
            ///accesoBD.prepareStatement("SELECT COUNT(identificacion) FROM usuarios WHERE identificacion = ?");
            String sql="SELECT COUNT(identificacion) FROM usuarios WHERE identificacion = ?";
            //ps.executeQuery();
            try {
                ps = accesoBD.prepareStatement(sql);
                ps.setString(1, usuario);
                res=ps.executeQuery();
                
                if (res.next()) {
                    return res.getInt(1);
                } 
                
                return 1;
                
            } catch (Exception e) {
                return 1;
            }
    }
    
    public String PasswordBD(String usuario){
            Connection accesoBD = conexion.getConection();
            PreparedStatement ps = null;
            ResultSet res = null;
            String PasswordBD = "";
            ///accesoBD.prepareStatement("SELECT COUNT(identificacion) FROM usuarios WHERE identificacion = ?");
            String sql="SELECT password FROM usuarios WHERE identificacion = ?";
            //ps.executeQuery();
            try {
                ps = accesoBD.prepareStatement(sql);
                ps.setString(1, usuario);
                res=ps.executeQuery();
                
                if (res.next()) {
                    PasswordBD = res.getString(1);
                } 
                
                return PasswordBD;
                
            } catch (Exception e) {
                return PasswordBD;
            }
    }
    
 /*       public boolean login(Registro usr){
            Connection accesoBD = conexion.getConection();
            PreparedStatement ps = null;
            ResultSet res = null;
            ///accesoBD.prepareStatement("SELECT COUNT(identificacion) FROM usuarios WHERE identificacion = ?");
            String sql="SELECT COUNT(identificacion) FROM usuarios WHERE identificacion = ?";
            //ps.executeQuery();
            try {
                ps = accesoBD.prepareStatement(sql);
                ps.setString(1, usr.getIdentificacion());
                res=ps.executeQuery();
                
                if (res.next()) {
                    return res.getInt(1);
                } 
                
                return 1;
                
            } catch (Exception e) {
                return 1;
            }
    }*/
    
    public String InsertRegistro(String identificacion,String nombre,String apellido,String fecha,String direccion,String telefono,int tipo_user,String correo,String password){
        String Rta_Registro = null;
        try {
            Connection accesoBD = conexion.getConection();
             PreparedStatement ps;
             ps = accesoBD.prepareStatement("INSERT INTO usuarios(identificacion, nombre, apellido, fechanmvto, direccion, telefono, tipo_usuario,correo,password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
             ps.setString(1,  identificacion);
             ps.setString(2, nombre);
             ps.setString(3, apellido);
             ps.setString(4, fecha);
             ps.setString(5, direccion);
             ps.setString(6,telefono);
             ps.setInt(7, tipo_user);
             ps.setString(8,correo);
             ps.setString(9,password);
             
             int num_filas = ps.executeUpdate();
             if (num_filas>0) {
                Rta_Registro="Registro exitoso. ";
             }
             
        } catch (Exception e) {
            
        }
        return Rta_Registro;
    }
    
    public ArrayList<Object[]> listRegistro(){
            ResultSetMetaData ResultadosMeta;
            ArrayList<Object[]> ListaRegistro = new ArrayList<Object[]>();
            Registro registro;
            try {
            Connection accesoBD = conexion.getConection();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT identificacion, nombre, apellido, fechanmvto, direccion, telefono, correo, tipo_usuario FROM usuarios");
            ResultSet res = ps.executeQuery();
            ResultadosMeta = res.getMetaData();
            
                while (res.next()) {
                    Object[] filas = new Object[ResultadosMeta.getColumnCount()];
                    for (int i = 0; i < ResultadosMeta.getColumnCount(); i++) {
                         filas[i]= res.getObject(i+1);
                    }
                    ListaRegistro.add(filas);
                }
            
        } catch (Exception e) {
        }
        return ListaRegistro;   
    }
    
     public ArrayList<String> listTiposUsuarios(){
            ArrayList ListaRegistro = new ArrayList();
            Registro registro;
            try {
            Connection accesoBD = conexion.getConection();
            PreparedStatement ps = accesoBD.prepareStatement("SELECT id, descripcion FROM tipo_usuario");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                  ListaRegistro.add(res.getString(2));
                }
        } catch (Exception e) {
           
        }
        return ListaRegistro;   
    }
     
    public int Tipo_Usuario(String Usuario){
        int num_user = 0;
        try {
            if (Usuario.equals("Administrador")) {
                num_user = 1;
            }
            if (Usuario.equals("Gerente")) {
                num_user = 2;
            }
            if (Usuario.equals("Jefe Tecnico")) {
                num_user = 3;
            }
            if (Usuario.equals("Tecnico")) {
                num_user = 4;
            }
            if (Usuario.equals("Recepcionista")) {
                num_user = 5;
            }
        } catch (Exception e) {
           
        }
        return num_user;   
    }
    
}
