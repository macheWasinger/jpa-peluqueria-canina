package com.mycompany.jpapeluqueriacanina.logica;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Duenio implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Basic
    private String nombre;
    private int telefono;
    private String direccion;
    
    /*  - cascade = CascadeType.ALL incluye persistir, borrar, actualizar, etc.
        - orphanRemoval = true elimina mascotas que ya no estén asociadas a ningún dueño.
        - orphanRemoval = true elimina mascotas que ya no estén asociadas a ningún dueño. 
        - mappedBy = "duenioAsociado", es para que desde la clase "Mascota", la variable de 
        instancia "duenioAsociado" se encargue de generar el @OneToMany. 
    */
    @OneToMany(mappedBy = "duenioAsociado", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<Mascota> listaMascota = new ArrayList<>();

    /* IMPORTANTE: La relación tiene que ser BIDIRECCIONAL. Por lo tanto,
    en el mapeo @OneToMany le agrego que está mapeado por "duenioAsociado" (es la 
    variable de instancia/atributo propio que está creado en el archivo de 
    la clase "Mascota") de la siguiente manera: (mappedBy="duenioAsociado") para que, 
    desde el archivo de la clase "Mascota", la variable de instancia "duenioAsociado"
    se encargue de generar el @OneToMany. 
    En el archivo de la clase "Mascota", a la variable de instancia "duenioAsociado"
    la mapeo de la siguiente forma: @ManyToOne (que es lo inverso a @OneToMany).
     */
    
    public Duenio() {}
    
    public Duenio(String nombre, int telefono, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.listaMascota = new ArrayList<>();
    }
    
    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getTelefono() { return telefono; }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public ArrayList<Mascota> getListaMascota() { return listaMascota; }
    public void setListaMascota(ArrayList<Mascota> listaMascota) {
        this.listaMascota = listaMascota;
    }
    
    @Override
    public String toString() {
        return "Duenio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                ", direccion='" + direccion + '\'' +
                // Evitamos imprimir listaMascota completa para no caer en recursión infinita
                ", cantidadMascotas=" + (listaMascota != null ? listaMascota.size() : 0) +
                '}';
    }

}
