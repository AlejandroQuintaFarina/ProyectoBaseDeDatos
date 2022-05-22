/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobasededatos;

/**
 *
 * @author alex0
 */
public class Alumno {
    
    private int pkAlumno;
    private String nombre;
    private String PrimerApellido;
    private String SegundoApellido;

    public Alumno(int pkAlumno, String nombre, String PrimerApellido, String SegundoApellido) {
        this.pkAlumno = pkAlumno;
        this.nombre = nombre;
        this.PrimerApellido = PrimerApellido;
        this.SegundoApellido = SegundoApellido;
    }

    public int getPkAlumno() {
        return pkAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return PrimerApellido;
    }

    public String getSegundoApellido() {
        return SegundoApellido;
    }
 
    
}
