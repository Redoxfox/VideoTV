/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tux-carlos
 */
public class Validador_Registro {
    
     public boolean Validar_Campos_Vacios (String Cadena){
            Cadena = Cadena;
            boolean result = true;
            try {
                if (Cadena.equals("")) {
                    result = true;
                } else {
                    result = false;
                }    
            } catch (Exception e) {
                result = false;
            }
         return result;
    }
     
     public boolean Validar_Contraseña (char[] Password, char[] ConPassword){
            Password = Password;
            ConPassword = ConPassword;
            boolean result_comp = true;
            try {
            int puntero = 0;
            if (Password.length != ConPassword.length){
                result_comp = false;
            }
            else{
                while((result_comp)&&(puntero < Password.length)){
                if (Password[puntero] != ConPassword[puntero]){
                    result_comp = false;
                }
                    puntero++;
                }
            }

            } catch (Exception e) {
                
            }
         return result_comp;
    }
     
    
     
    public int Validar (char[] Password, char[] ConPassword){
            Password = Password;
            ConPassword = ConPassword;
            int s = 0;
            try {
            int puntero = 0;
            s = Password.length;
            } catch (Exception e) {
                
            }
         return s;
    }
    
       public int Validar2 (char[] Password, char[] ConPassword){
            Password = Password;
            ConPassword = ConPassword;
            int s = 0;
            try {
            int puntero = 0;
            s = ConPassword.length;
            } catch (Exception e) {
                
            }
         return s;
    }
       
   public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
 
    /* Retorna un hash MD5 a partir de un texto */
    public static String md5(String txt) {
        return hash.getHash(txt, "MD5");
    }
 
    /* Retorna un hash SHA1 a partir de un texto */
    public static String sha1(String txt) {
        return hash.getHash(txt, "SHA1");
    }
    
    public boolean Contraseña_Valida (String Password, String PasswordBD){
            Password = Password;
            PasswordBD = PasswordBD;
            boolean result_comp = true;
            String ContraseñaCifrada = "";
            try {
              ContraseñaCifrada=md5(Password);
                if (ContraseñaCifrada.equals(PasswordBD)) {
                    result_comp = true;
                } else {
                    result_comp = false;
                }
            } catch (Exception e) {
                
            }
         return result_comp;
    }
    
    public boolean ValidarEmail(String correo){
        boolean result=true;
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
    
        Matcher mather = pattern.matcher(correo);

        if (mather.find() == true) {
            result=true;
        } else {
            result=false;
        }
        return result;
      }
    
} //Cierre clase Validador_Registro
