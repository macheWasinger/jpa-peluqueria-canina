package com.mycompany.jpapeluqueriacanina.persistencia;

import com.mycompany.jpapeluqueriacanina.logica.Duenio;
import com.mycompany.jpapeluqueriacanina.logica.Mascota;
import com.mycompany.jpapeluqueriacanina.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControladoraPersistencia {
    
    DuenioJpaController dueJpa = new DuenioJpaController();
    MascotaJpaController mascJpa = new MascotaJpaController();
    
    
    // Cada método creado se invoca en la clase "ControladoraLogica".
    // "dueJpa" invoca a cada método creado automáticamente en el "DuenioJpaController".
    // "mascJpa" invoca a cada método creado automáticamente en el "MascotaJpaController".
    //------------ DUENIO -------------
    public void crearDuenio(Duenio due) {
        dueJpa.create(due);
    }
    
    public void eliminarDuenio(int id) {
        try {
            dueJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editarDuenio(Duenio due) {
        try {
            dueJpa.edit(due);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Duenio traerDuenio(int id) {
        return dueJpa.findDuenio(id);
    }
    
    public ArrayList<Duenio> traerListaDuenios() {
        List<Duenio> lista = dueJpa.findDuenioEntities();
        ArrayList<Duenio> listaDuenios = new ArrayList<Duenio>(lista);
        return listaDuenios;
    }
    //---------------------------------
    
    //----------- MASCOTA -----------
    public void crearMascota(Mascota masc) {
        mascJpa.create(masc);
    }
    
    public void eliminarMascota(int id) {
        try {
            mascJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editarMascota(Mascota masc) {
        try {
            mascJpa.edit(masc);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Mascota traerMascota(int id) {
        return mascJpa.findMascota(id);
    }
    
    public ArrayList<Mascota> traerListaMascotas() {
        List<Mascota> listaMasc = mascJpa.findMascotaEntities();
        ArrayList<Mascota> listaMascotas = new ArrayList<Mascota>(listaMasc);
        return listaMascotas;
    }
    //---------------------------------
}
