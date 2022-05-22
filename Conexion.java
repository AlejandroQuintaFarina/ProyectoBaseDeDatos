/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobasededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex0
 */
public class Conexion {
    
    //creamos esta variable de tipo string que nos sirve para crar la tabla
    private static final String ALUMNO
            ="CREATE TABLE alumnoa(\n"
            +"PK_Alumno INT primary key,\n"
            +"nombre varchar(255),\n"
            +"PrimerApellido varchar(255),\n"
            +"SegundoApellido varchar(255)),";
    
    static String url = "";//variable url
    static String database = "BaseDeDatos";//nombre base de datos
    
    public static Connection conexion = null;//variable conexion
    
    public static void crearConexion(){ //metodo crear conexion
       
        url = "jdbc:sqlite:" + database + ".db";
        try{
            conexion = DriverManager.getConnection(url);
        } catch (SQLException e) {
        }
    }
    
    public static boolean crearTablas(String tabla){
     
        Statement stmt = null;
        
        try{
            crearConexion();//llamamos a crearConexion
            stmt = conexion.createStatement();
            stmt.executeUpdate(tabla);
            stmt.close();
            conexion.close();
            return true;
        } catch (SQLException e) {
        }
        return false;
    }
    
    public static boolean guardar(String sql){
        
        Statement stmt = null;//creamos variable
        
        try{
            crearConexion(); //llamamos a crearConexion
            conexion.setAutoCommit(false);
            stmt= conexion.createStatement();
            stmt.executeUpdate(sql);//ejecutamos lo que estamos recibiendo como parametro
            conexion.commit();
            conexion.close();//cerramos conexion
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }
    
    public static boolean modificar(String sql){
        
        Statement stmt = null;//creamos variable
        
        try{
            crearConexion(); //llamamos a crearConexion
            conexion.setAutoCommit(false);
            stmt= conexion.createStatement();
            stmt.executeUpdate(sql);//ejecutamos lo que estamos recibiendo como parametro
            conexion.commit();
            conexion.close();//cerramos conexion
            return true;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }
    
    public static boolean eliminar(String sql){
        Statement stmt = null;//creamos variable
        
        try{
            crearConexion(); //llamamos a crearConexion
            conexion.setAutoCommit(false);
            stmt= conexion.createStatement();
            stmt.executeUpdate(sql);//ejecutamos lo que estamos recibiendo como parametro
            conexion.commit();
            conexion.close();//cerramos conexion
            return true;
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }
            
    
    
    public static void main(String[] args){
        crearTablas(ALUMNO);
    }
    
}
