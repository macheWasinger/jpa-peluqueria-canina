package com.mycompany.jpapeluqueriacanina.logica;

import com.mycompany.jpapeluqueriacanina.persistencia.ControladoraPersistencia;
import java.util.ArrayList;

public class ControladoraLogica {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    
    //--------- DUEÑO ---------
    public void crearDuenio(Duenio due) {
        /* Invoca al método "crearDuenio" creado en 
        la clase "ControladoraPersistencia" */
        controlPersis.crearDuenio(due);
    }
    
    public void eliminarDuenio(int id) {
        /* Invoca al método "eliminarDuenio" creado en 
        la clase "ControladoraPersistencia" */
        controlPersis.eliminarDuenio(id);
    }
    
    public void editarDuenio(Duenio due) {
        /* Invoca al método "editarDuenio" creado en 
        la clase "ControladoraPersistencia" */
        controlPersis.editarDuenio(due);
    }
    
    public Duenio traerDuenio(int id) {
        /* Invoca al método "traerDuenio" creado en 
        la clase "ControladoraPersistencia" */
        return controlPersis.traerDuenio(id);
    }
    
    public ArrayList<Duenio> traerListaDuenios() {
        /* Invoca al método "traerListaDuenios" creado en 
        la clase "ControladoraPersistencia" */
        return controlPersis.traerListaDuenios();
    }      
    //-------------------------
    
    
    //--------- MASCOTA ---------
    public void crearMascota(Mascota masc) {
        /* Invoca al método "crearMascota" creado en 
        la clase "ControladoraPersistencia" */
        controlPersis.crearMascota(masc);
    }
    
    public void eliminarMascota(int id) {
        /* Invoca al método "eliminarMascota" creado en 
        la clase "ControladoraPersistencia" */
        controlPersis.eliminarMascota(id);
    }
    
    public void editarMascota(Mascota masc) {
        /* Invoca al método "editarMascota" creado en 
        la clase "ControladoraPersistencia" */
        controlPersis.editarMascota(masc);
    }
    
    public Mascota traerMascota(int id) {
        /* Invoca al método "traerMascota" creado en 
        la clase "ControladoraPersistencia" */
        return controlPersis.traerMascota(id);
    }
    
    public ArrayList<Mascota> traerListaMascotas() {
        /* Invoca al método "traerListaMascotas" creado en 
        la clase "ControladoraPersistencia" */
        return controlPersis.traerListaMascotas();
    }
    //---------------------------
}
