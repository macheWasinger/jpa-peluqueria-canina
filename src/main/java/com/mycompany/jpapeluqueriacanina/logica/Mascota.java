package com.mycompany.jpapeluqueriacanina.logica;

import com.mycompany.jpapeluqueriacanina.logica.Duenio;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Mascota implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Basic
    private String nombre;
    private String raza;
    private int edad;
    private String observaciones;
    
    /* @JoinColumn, une al "ArrayList<Mascota> listaMascota" creada en la clase "Duenio" 
    con la columna "duenioAsociado_id" creada en la tabla "mascota" de la base de datos. 
    "duenioAsociado_id" contiene la clave primaria del dueño al que pertenece la mascota. */
    @ManyToOne
    @JoinColumn(name = "duenioAsociado_id")
    private Duenio duenioAsociado;
    
    public Mascota() {}
    
    public Mascota(String nombre, String raza, int edad, String observaciones, Duenio duenioAsociado) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.observaciones = observaciones;
        this.duenioAsociado = duenioAsociado;
    }
    
    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getRaza() { return raza; }
    public void setRaza(String raza) {
        this.raza = raza;
    }
    
    public int getEdad() { return edad; }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public Duenio getDuenioAsociado() { return duenioAsociado; }
    public void setDuenioAsociado(Duenio duenioAsociado) {
        this.duenioAsociado = duenioAsociado;
    }
    
    @Override
    public String toString() {
        return "Mascota{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", raza='" + raza + '\'' +
                ", edad=" + edad +
                ", observaciones='" + observaciones + '\'' +
                // Evitamos imprimir el duenio completo
                ", duenio=" + (duenioAsociado != null ? duenioAsociado.getNombre() : "sin dueño") +
                '}';
    }

}
