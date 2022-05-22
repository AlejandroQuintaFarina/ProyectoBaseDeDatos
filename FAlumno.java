/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobasededatos;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author alex0
 */
public class FAlumno extends Conexion{
    
    public static List<Alumno> listaAlumno = new ArrayList<Alumno>();//declaramos la lista
    
    private static String buscarMaxPkAlumnos(String consulta){//creamos metodo privado y estatico que nos devuelva un string
        
        int pk_Alumno = 0;//declaramos variable
        
        try{
            crearConexion();//llamamos al metodo crear conexion
            Statement s = conexion.createStatement();//declaramos la variable 
            ResultSet rs = s.executeQuery(consulta);//declaramos otra variable
            while (rs.next()){//recorremos todos los datos de nuestra consulta
                pk_Alumno = rs.getInt("maxPk");
            }
            conexion.close();//cerramos la conexion
        } catch (Exception e) {
        }
        pk_Alumno++;//incrementamos de 1 en 1
        return pk_Alumno + "";//lo retornamos
    }
    
    private String consultaMaxPkAlumno(){
        
        String consulta = "select max(PK_Alumno) as maxPK\n"//declaramos una variable de tipo String 
                + "from alumnos";
        return consulta;//devuelve la consulta
    }
    
    public String insertarAlumnos(String maxPk, String nombre, String PrimerApellido, String SegundoApellido){
        String insertar = "insert into alumnos(PK_Alumno,Nombre,PrimerApellido,SegundoApellido)\n"//declaramos la variable y hacemos la consulta
                + "values(" + maxPk + ",'" + nombre + "','" + PrimerApellido + "','" + SegundoApellido + "');";
        return insertar;//devuelve la variable
    }
    
    public void principalInsertarAlumno(String nombre, String PrimerApellido, String SegundoApellido){
       
        String maxPk = buscarMaxPkAlumnos(consultaMaxPkAlumno());//declarmos la variable, sirve para obtener la ultima pk de nuestra consulta
        
        boolean resAlumno = guardar(insertarAlumnos(maxPk, nombre,//declaros una variable y llamamos al merodo guardar
                PrimerApellido,
                SegundoApellido));
        if(resAlumno){
            JOptionPane.showMessageDialog(null, "Los datos se guardaron correctamente");//si es correcto que nos muestre esto
            principalBuscarAlumnos();//usamos el metodo creado luego
            JFAlumno.txtNombre.setText("");
            JFAlumno.txtPrimerApellido.setText("");
            JFAlumno.txtSegundoApellido.setText("");
            
        }
    }

    public void principalBuscarAlumnos(){
        
        buscarAlumnos(consultaAlumnos());
    }
    
    private String consultaAlumnos(){
        String consulta = "select * from alumnos";//declaramos la variable, y la igualamos a la consulta
        return consulta;
    }
    
    private void buscarAlumnos(String consulta) {
        
        listaAlumno.clear();//limpiamos la lista
        int pK_Alumno = 0;//declarmos variables
        String nombre = "";
        String PrimerApellido = "";
        String SegundoApellido = "";
        
        try{
            crearConexion();//lamamos al metodo
            Statement s = conexion.createStatement();//crea variable
            ResultSet rs = s.executeQuery(consulta);//crea variable
            while(rs.next()){//recorremos con un while y en las variables guardamos los datos
                pK_Alumno = rs.getInt("PK_Alumno");
                nombre = rs.getString("Nombre");
                PrimerApellido = rs.getString("PrimerApellido");
                SegundoApellido = rs.getString("Segundo Apellido");
                Alumno alumno = new Alumno(//instanciamos la clase alumno
                        pK_Alumno,
                        nombre,
                        PrimerApellido,
                        SegundoApellido);
                listaAlumno.add(alumno);//le añadimos alumno a la lista
            }
            conexion.close();//cerramos conexion
        }catch(Exception e){
        }
        mostrarAlumnosTabla();
    }
    
    private void mostrarAlumnosTabla(){
        
        agregarFilasTabla(JFAlumno.tablaAlumno,0);//mandamos la tabla
        agregarFilasTabla(JFAlumno.tablaAlumno, listaAlumno.size());//le mandamos a la tabla los datos de la lista
        
        for (int posLA = 0; posLA < listaAlumno.size(); posLA++){
            JFAlumno.tablaAlumno.setValueAt(listaAlumno.get(posLA).getNombre(), posLA, 0);//pos LA posicion lista
            JFAlumno.tablaAlumno.setValueAt(listaAlumno.get(posLA).getPrimerApellido(), posLA, 1);
            JFAlumno.tablaAlumno.setValueAt(listaAlumno.get(posLA).getSegundoApellido(), posLA, 2);
        }
    }
    
    public void agregarFilasTabla(JTable jTable, int filas){
        
        DefaultTableModel modeloV = (DefaultTableModel) jTable.getModel();//se añaden filas a la tabla
        modeloV.setRowCount(filas);
    }
    
    private String modificarAlumno(String pkAlumno, String nombre, String PrimerApellido, String SegundoApellido){
        String modificar = "update alumnos set Nombre='" + nombre//declaramos la variable y hacemos la consulta
                +"', PrimerApellido='" + PrimerApellido + "' , SegundoApelllido='" + SegundoApellido + "' "
                + "where PK_Alumno=" + pkAlumno;
        return modificar;
    }
    
    public void principalModificarAlumno(String pkAlumno, String nombre, String PrimerApellido, String SegundoApellido) {
        
        boolean resModificarAlumno = modificar(modificarAlumno(pkAlumno,//declaramos la variable
                nombre,
                PrimerApellido,
                SegundoApellido));
        if (resModificarAlumno){
            JOptionPane.showInputDialog(null, "Los datos se modificaron con éxito");
            principalBuscarAlumnos();
            JFAlumno.txtNombre.setText("");
            JFAlumno.txtPrimerApellido.setText("");
            JFAlumno.txtSegundoApellido.setText("");
        }
    }
    
    private String eliminarAlumno(String pkAlumno){
        String eliminar = "delete from alumnos where PK_Alumno=" + pkAlumno;
        return eliminar;
    }
    
    public void principalEliminarAlumno(String pkAlumno) {
        boolean resEliminarAlumno = eliminar(eliminarAlumno(pkAlumno));
        if (resEliminarAlumno){
            JOptionPane.showMessageDialog(null, "Los datos se eliminaron con exito");
            principalBuscarAlumnos();
        }
    }
}
