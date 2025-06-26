package com.mycompany.jpapeluqueriacanina;

import com.mycompany.jpapeluqueriacanina.logica.ControladoraLogica;
import com.mycompany.jpapeluqueriacanina.logica.Duenio;
import com.mycompany.jpapeluqueriacanina.logica.Mascota;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

public class JpaPeluqueriaCanina {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        ControladoraLogica controlLogico = new ControladoraLogica();
        
        boolean salirDeLaApp = false;
        boolean volverAlMenu = false;
        int opcionMenu;
        int opcionSubmenu;
        
        System.out.println("Peluquería Canina - Registro de Clientes y Mascotas");
        
        while(!salirDeLaApp) {
            System.out.println("1. Gestionar Dueños");
            System.out.println("2. Gestionar Mascotas");
            System.out.println("3. Salir");
            imprimirIndicador_elijaUnaOpcion();
            
            opcionMenu = entradaInt(entrada);
            entrada.nextLine(); // Limpiar buffer
            volverAlMenu= false;
                
            while(!volverAlMenu) {
                imprimirSeparador();
                            
                switch(opcionMenu) {
                    case 1 -> {
                        System.out.println("1. Agregar nuevo dueño");
                        System.out.println("2. Editar datos de un dueño");
                        System.out.println("3. Eliminar dueño");
                        System.out.println("4. Ver todos los dueños");
                        System.out.println("5. Ver detalles de un dueño (con sus mascotas)");     
                        System.out.println("6. Volver al menú principal");
                        imprimirIndicador_elijaUnaOpcion();

                        opcionSubmenu = entradaInt(entrada);
                        entrada.nextLine(); // Limpiar buffer
                            
                        switch(opcionSubmenu) {
                            case 1 -> agregarDuenio(controlLogico, entrada);
                            case 2 -> editarDuenio(controlLogico, entrada, volverAlMenu);
                            case 3 -> eliminarDuenio(controlLogico, entrada, volverAlMenu);
                            case 4 -> verListaDeDuenios(controlLogico, entrada, volverAlMenu);
                            case 5 -> verDetallesDeUnDuenioConNombreMascota(controlLogico, entrada);
                            case 6 -> volverAlMenu = true;
                            default -> validacionOpcionIncorrecta_listaDuenios(opcionSubmenu, controlLogico.traerListaDuenios());
                        }
                    }

                    case 2 -> {
                        System.out.println("1. Agregar mascota a un dueño existente");
                        System.out.println("2. Editar datos de una mascota");
                        System.out.println("3. Eliminar mascota");
                        System.out.println("4. Ver todas las mascotas (con nombre de dueño)");
                        System.out.println("5. Filtrar mascotas por raza");
                        System.out.println("6. Filtrar mascotas por dueño");
                        System.out.println("7. Volver al menú principal");
                        imprimirIndicador_elijaUnaOpcion();

                        opcionSubmenu = entradaInt(entrada);
                        entrada.nextLine(); // Limpiar buffer

                        switch(opcionSubmenu) {
                            case 1 -> agregarMascota(controlLogico, entrada);
                            case 2 -> editarMascota(controlLogico, entrada, volverAlMenu);
                            case 3 -> eliminarMascota(controlLogico, entrada, volverAlMenu);
                            case 4 -> verListaDeMascotasConNombreDuenio(controlLogico, entrada, volverAlMenu);
                            case 5 -> filtrarMascotaPorRaza(controlLogico, entrada, volverAlMenu);
                            case 6 -> filtrarMascotaPorDuenio(controlLogico, entrada, volverAlMenu);
                            case 7 -> volverAlMenu= true;
                            default -> validacionOpcionIncorrecta_listaMascotas(opcionSubmenu, controlLogico.traerListaMascotas());
                        }
                    }

                    case 3 -> {
                        // Cierro menú y submenú
                        salirDeLaApp = true;
                        volverAlMenu = true;
                    }
                    default -> {
                        mensajeOpcionInvalida();
                        imprimirSeparador();
                        volverAlMenu= true;
                    }
                }
            }
        }
    }
    
  
    //--------- MÉTODOS Y FUNCIONES AUXILIARES ---------
    
    public static String entradaStringNombre(Scanner entrada, Predicate<String> validador, String mensajeError) {
        String input;
        while (true) {
            input = entrada.nextLine().trim();
            if (!input.isEmpty() && validador.test(input)) {
                return input;
            }
            System.out.println(mensajeError + " Por favor, intenta nuevamente: ");
        }
    }
    
    public static String entradaString(Scanner entrada) {
        String input;
        while (true) {
            input = entrada.nextLine().trim(); // Elimina espacios al principio y al final
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Entrada vacía. Por favor, escriba algo:");
        }
    }
    
    public static int entradaInt(Scanner entrada) {
        while (true) {
            try {
                return entrada.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Ingresá un número entero. Por favor, intentá nuevamente: ");
                entrada.nextLine(); // Limpiar buffer para evitar bucle infinito
            }
        }
    }
    
    public static String capitalizarPalabra(String frase) {
        String[] palabras = frase.trim().split("\\s+");
        /*
            - frase.trim() elimina espacios en blanco al principio y al final.
            - .split("\\s+") divide la frase en palabras usando cualquier cantidad de espacios como separador (\\s+ = uno o más espacios).
            - Resultado: un array palabras que contiene cada palabra separada de la frase.
        */

        StringBuilder capitalizado = new StringBuilder();
        // Se usa un StringBuilder para ir armando la frase capitalizada, que es más eficiente que concatenar strings comunes dentro de un bucle.

        for (String palabra : palabras) {
            if (palabra.length() > 0) { // Chequea que la palabra no esté vacía (por si había espacios de más).

                capitalizado.append(palabra.substring(0, 1).toUpperCase())
                        .append(palabra.substring(1).toLowerCase())
                        .append(" ");
                /*
                Este bloque construye una palabra capitalizada:
                     - palabra.substring(0, 1).toUpperCase() → convierte la primera letra a mayúscula.
                     - palabra.substring(1).toLowerCase() → convierte el resto de la palabra a minúscula.
                     - .append(" ") → agrega un espacio al final para separar palabras.
                */
            }
        }
        return capitalizado.toString().trim(); // Convierte el StringBuilder a un String y le quita el espacio extra al final con .trim().
    }
    
    public static void verListaDeDuenios(ArrayList<Duenio> listaDuenios) {
        for (int i = 0; i < listaDuenios.size(); i++) {
            System.out.println((i + 1) + ". " + listaDuenios.get(i).getNombre());
        }
    }
    
    public static void listaDeDuenios_ordenarAlfabeticamente(ArrayList<Duenio> listaDuenios) {
        // Creo una copia para no modificar la original
        ArrayList<Duenio> listaOrdenada = new ArrayList<>(listaDuenios);
        
        // Ordeno por nombre
        Collections.sort(listaOrdenada, Comparator.comparing(Duenio::getNombre));
        
        // Muestro los dueños ordenados alfabéticamente
        for (int i = 0; i < listaOrdenada.size(); i++) {
            System.out.println((i + 1) + ". " + listaOrdenada.get(i).getNombre());
        }
    }
    
    public static void verListaDeMascotas(ArrayList<Mascota> listaMascotas) {
        for (int i = 0; i < listaMascotas.size(); i++) {
            System.out.println((i+1) + ". " + listaMascotas.get(i).getNombre());
        }
    }

    public static int obtenerIDduenio(ArrayList<Duenio> listaDuenios, int opcion) {
        for (int i = 0; i < listaDuenios.size(); i++) {
            
            /* Consulta si la opción elegida es igual al nombre del dueño de la lista de 
            dueños registrados en la base de datos */
            if ((i+1) == opcion) { 
               return listaDuenios.get(i).getId();
            }
        }
        return 0;
    }
    
    public static int obtenerIDmascota(ArrayList<Mascota> listaMascotas, int opcion) {
        for (int i = 0; i < listaMascotas.size(); i++) {
            
            /* Consulta si la opción elegida es igual al nombre del mascota de la lista de 
            mascotas registrados en la base de datos */
            if ((i+1) == opcion) { 
               return listaMascotas.get(i).getId();
            }
        }
        return 0;
    }
    
    public static void obtenerListaMascotas(ArrayList<Duenio> listaDuenios, int opcion) {
        for (int i = 0; i < listaDuenios.size(); i++) {
            
            /* Consulta si la opción elegida es igual al nombre del dueño de la lista de 
            dueños registrados en la base de datos */
            if ((i+1) == opcion) { 
                ArrayList<Mascota> listaMascotas_DuenioElegido = listaDuenios.get(i).getListaMascota();
                
                verListaDeMascotas(listaMascotas_DuenioElegido);
            }
        }
    }
    
    public static ArrayList<String> verNombresDeMascotas(ArrayList<Mascota> listaMascotas) {
        ArrayList<String> listaDeNombresMascotas = new ArrayList<>();
        for (Mascota masc : listaMascotas) {
            listaDeNombresMascotas.add(masc.getNombre());
        }
        return listaDeNombresMascotas;
    }
    
    
    //-------------- DUEÑOS - MÉTODOS AUXILIARES --------------
    // AGREGAR NUEVO DUEÑO
    public static void agregarDuenio(ControladoraLogica controladorLogico, Scanner entradaTeclado) {
        imprimirSeparador();
        System.out.println("AGREGAR NUEVO DUEÑO");
        
        System.out.println("Ingrese el nombre del dueño: ");
        String nombreDuenio = entradaStringNombre(entradaTeclado, s -> s.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+"),"Entrada inválida. Solo se permiten letras y espacios.");
        String nombreDuenioCapitalizado = capitalizarPalabra(nombreDuenio);

        System.out.println("Ingrese el teléfono del dueño: ");
        int telefono = entradaInt(entradaTeclado);

        entradaTeclado.nextLine(); // <-- limpia el ENTER restante
        /* Cuando usás entradaInt(entrada) (que internamente hace entrada.nextInt()), el número se 
        lee correctamente, pero el ENTER que apretás después del número queda en el buffer. Entonces, 
        la siguiente llamada a entrada.nextLine() (usada para leer la dirección) no espera a que 
        escriba algo, sino que consume directamente ese ENTER sobrante. Por eso se "saltea" 
        la entrada de la dirección. */

        System.out.println("Ingrese la dirección del domicilio del dueño: ");
        String direccionCapitalizada = capitalizarPalabra(entradaString(entradaTeclado));

        Duenio duenio = new Duenio(nombreDuenioCapitalizado, telefono, direccionCapitalizada);

        // Guardar dueño en la base de datos
        controladorLogico.crearDuenio(duenio);
        
        imprimirSeparador();
        System.out.println("Datos guardados correctamente.");
    }
    
    
    // EDITAR DATOS DE UN DUEÑO
    public static void editarDuenio(ControladoraLogica controladorLogico, Scanner entradaTeclado, boolean volverAlMenu) {
        imprimirSeparador();
        System.out.println("EDITAR DATOS DE UN DUEÑO");
        
        ArrayList<Duenio> listaDuenios = controladorLogico.traerListaDuenios();
        verListaDeDuenios(listaDuenios);
        
        imprimirSeparador();                 
        System.out.println("Elija una opción (o escriba 'salir' para cancelar y volver al menú principal)");
        
        int opcion = entradaIntConOpcionSalir(entradaTeclado, mensajeError_tipearNuevamenteNumeroOsalir());
        if (opcion == -1) return;

        if (validacionOpcionIncorrecta_listaDuenios(opcion, listaDuenios)) return; // Frena el flujo del método
        
        Duenio duenio = listaDuenios.get(opcion - 1);
        
        System.out.println("Escriba nuevamente el nombre: ");
        String tipeoNombre = entradaString(entradaTeclado);
        
        System.out.println("Escriba nuevamente el teléfono: ");
        int tipeoTelefono = entradaInt(entradaTeclado);
        entradaTeclado.nextLine(); // Limpiar buffer
        
        System.out.println("Escriba nuevamente la dirección: ");
        String tipeoDireccion = entradaString(entradaTeclado);
        
        // Modifico el dueño existente
        duenio.setNombre(capitalizarPalabra(tipeoNombre));
        duenio.setTelefono(tipeoTelefono);
        duenio.setDireccion(capitalizarPalabra(tipeoDireccion));
        
        // Lo actualizo
        controladorLogico.editarDuenio(duenio);
        
        imprimirSeparador();
        System.out.println("Dueño modificado correctamente.");
    }
    
    
    // ELIMINAR DUEÑO
    public static void eliminarDuenio(ControladoraLogica controladorLogico, Scanner entradaTeclado, boolean volverAlMenu) {
        imprimirSeparador();
        System.out.println("ELIMINAR DUEÑO");

        ArrayList<Duenio> listaDeDuenios = controladorLogico.traerListaDuenios();
        verListaDeDuenios(listaDeDuenios); 
        /* "verListaDeDuenios" Muestra por consola la lista de dueños registrados en la base de datos. 
        Se usa para mostrar los nombres de los dueños con su número de orden, de modo que el usuario 
        pueda elegir cuál eliminar. */
        
        imprimirSeparador();                           
        System.out.println("Elija una opción (o escriba 'salir' para cancelar y volver al manú principal)");
                                    
        int opcion = entradaIntConOpcionSalir(entradaTeclado, mensajeError_tipearNuevamenteNumeroOsalir());
        if (opcion == -1) return;
        
        
        if (validacionOpcionIncorrecta_listaDuenios(opcion, listaDeDuenios)) return; // Frena el flujo del método
                                    
        int IDduenioAeliminar = obtenerIDduenio(listaDeDuenios, opcion);
        controladorLogico.eliminarDuenio(IDduenioAeliminar);

        imprimirSeparador();
        System.out.println("Dueño eliminado con éxito!");
    }
    
    
    // VER LISTA DE DUEÑOS
    public static void verListaDeDuenios(ControladoraLogica controladorLogico, Scanner entradaTeclado, boolean volverAlMenu) {
        imprimirSeparador();
        System.out.println("DUEÑOS REGISTRADOS");
        
        ArrayList<Duenio> listaDeDuenios = controladorLogico.traerListaDuenios();
        
        if (listaDeDuenios.size() < 0) {
            imprimirSeparador();
            System.out.println("No tiene dueños registrados. ");
            return; // Para cortar el flujo del método
        }
        
        listaDeDuenios_ordenarAlfabeticamente(listaDeDuenios);
        
        imprimirSeparador();                           
        System.out.println("Escriba 'salir' para cancelar y volver al menú principal");
        
        int opcion = entradaIntConOpcionSalir(entradaTeclado, "Entrada inválida. Por favor, escriba 'salir' para volver al menú.");
        if (opcion == -1) return;
        
        if (validacionOpcionLista(opcion)) return; // Frena el flujo del método
    }
    
    
    // VER DETALLES DE UN DUEÑO CON NOMBRE DE SUS MASCOTAS
    public static void verDetallesDeUnDuenioConNombreMascota(ControladoraLogica controladorLogico, Scanner entrada) {
        imprimirSeparador();
        System.out.println("DETALLES DE UN DUEÑO REGISTRADO");
                                    
        boolean seEncontro = false;
        
        ArrayList<Duenio> listaDuenios = controladorLogico.traerListaDuenios();
                                    
        System.out.println("Escriba el nombre del dueño: ");
        String duenioBuscado = entradaString(entrada);
                                    
        imprimirSeparador();
        
        if (listaDuenios.size() <= 0) {
            System.out.println("No hay dueños registrados.");
            return; // Para cortar el flujo del método
        }
        
        for (int i = 0; i < listaDuenios.size(); i++) {
            
            ArrayList<String> listaNombresMascotas = verNombresDeMascotas(listaDuenios.get(i).getListaMascota());
            
            if (listaDuenios.get(i).getNombre().equalsIgnoreCase(duenioBuscado)) {
                System.out.println("NOMBRE: " + listaDuenios.get(i).getNombre());
                System.out.println("TELÉFONO: " + listaDuenios.get(i).getTelefono());
                System.out.println("DIRECCIÓN: " + listaDuenios.get(i).getDireccion());
                System.out.println((listaNombresMascotas.size() > 1 ? "MASCOTAS" : "MASCOTA") + ": " + (listaNombresMascotas.size() == 0 ? "Aún no tiene mascotas registradas. " : listaNombresMascotas));
                
                seEncontro = true;
                break;
                /* En Java, el break solo se puede usar dentro de:
                    - Un bucle (for, while, do-while)
                    - Un switch 
                */
            }
        }
        
        if (!seEncontro) {
                System.out.println("Dueño no encontrado.");
            }
    }
    //-------------------------------------------------------------
    
    
    //-------------- MASCOTAS - MÉTODOS AUXILIARES --------------
    // AGREGAR NUEVA MASCOTA
    public static void agregarMascota(ControladoraLogica controladorLogico, Scanner entradaTeclado) {
        imprimirSeparador();
        System.out.println("LISTA DE DUEÑOS / AGREGAR NUEVA MASCOTA");
        
        ArrayList<Duenio> listaDeDuenios = controladorLogico.traerListaDuenios();
        verListaDeDuenios(listaDeDuenios);
                               
        imprimirSeparador();
        System.out.println("Ingrese el número del dueño al que desea registrar una mascota: ");
        int opcion = entradaInt(entradaTeclado);
        entradaTeclado.nextLine(); // Limpiar Buffer
        
        if (validacionOpcionIncorrecta_listaDuenios(opcion, listaDeDuenios)) return; // Frena el flujo del método
                       
        int idDuenioElegido = obtenerIDduenio(listaDeDuenios, opcion);
                                    
        Duenio duen = controladorLogico.traerDuenio(idDuenioElegido);                            
                           
        System.out.println("Ingrese el nombre de la mascota: ");
        String nombreMascota = entradaStringNombre(entradaTeclado, s -> s.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+"),"Entrada inválida. Solo se permiten letras y espacios.");
        String nombreMascotaCapitalizada = capitalizarPalabra(nombreMascota);

        System.out.println("Ingrese la raza de la mascota: ");
        String raza = entradaStringNombre(entradaTeclado, s -> s.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+"),"Entrada inválida. Solo se permiten letras y espacios.");
        String razaCapitalizada = capitalizarPalabra(raza);

        System.out.println("Ingrese la edad de la mascota: ");
        int edad = entradaInt(entradaTeclado);
        entradaTeclado.nextLine(); // Limpia el Buffer

        System.out.println("Ingrese observaciones de la mascota: ");
        String observaciones = capitalizarPalabra(entradaString(entradaTeclado));

        // Crear Mascota asociada al dueño
        Mascota masc = new Mascota(nombreMascotaCapitalizada, razaCapitalizada, edad, observaciones, duen);
                                    
        // Guardar Mascota en la base de datos
        controladorLogico.crearMascota(masc);
        
        imprimirSeparador();
        System.out.println("Mascota registrada con éxito.");
    }
    
    
    // EDITAR DATOS DE UNA MASCOTA
    public static void editarMascota(ControladoraLogica controladorLogico, Scanner entradaTeclado, boolean volverAlMenu) {
        imprimirSeparador();
        System.out.println("EDITAR DATOS DE UNA MASCOTA");
                                    
        ArrayList<Mascota> listaMascotas = controladorLogico.traerListaMascotas();
        verListaDeMascotas(listaMascotas);
                                  
        imprimirSeparador();
        System.out.println("Elija una opción (o escriba 'salir' para cancelar y volver al menú principal)");
        
        int opcion = entradaIntConOpcionSalir(entradaTeclado, mensajeError_tipearNuevamenteNumeroOsalir());
        if (opcion == -1) return;
        
        if (validacionOpcionIncorrecta_listaMascotas(opcion, listaMascotas)) return; // Frena el flujo del método
                                    
        Mascota mascota = listaMascotas.get(opcion - 1);
        
        System.out.println("Escriba nuevamente el nombre: ");
        String tipeoNombre = entradaString(entradaTeclado);
        
        System.out.println("Escriba nuevamente la raza: ");
        String tipeoRaza = entradaString(entradaTeclado);
        
        System.out.println("Escriba nuevamente la edad: ");
        int tipeoEdad = entradaInt(entradaTeclado);
        entradaTeclado.nextLine(); // Limpiar buffer
        
        System.out.println("Escriba nuevamente una observación: ");
        String tipeoObservacion = entradaString(entradaTeclado);
        
        // Modifico la mascota existente
        mascota.setNombre(capitalizarPalabra(tipeoNombre));
        mascota.setRaza(capitalizarPalabra(tipeoRaza));
        mascota.setEdad(tipeoEdad);
        mascota.setObservaciones(capitalizarPalabra(tipeoObservacion));
        
        // Lo actualizo
        controladorLogico.editarMascota(mascota);
        
        imprimirSeparador();
        System.out.println("Mascota modificada correctamente.");
    }
    
    
    // ELIMINAR UNA MASCOTA
    public static void eliminarMascota(ControladoraLogica controladorLogico, Scanner entradaTeclado, boolean volverAlMenu) {
        imprimirSeparador();
        System.out.println("ELIMINAR MASCOTA");

        ArrayList<Mascota> listaMascotas = controladorLogico.traerListaMascotas();
        verListaDeMascotas(listaMascotas);
        /* "verListaDeMascotas" Muestra por consola la lista de mascotas registrados en la base de datos. 
        Se usa para mostrar los nombres de los mascotas con su número de orden, de modo que el usuario 
        pueda elegir cuál eliminar. */
                                    
        imprimirSeparador();
        System.out.println("Elija una opción (o escriba 'salir' para cancelar y volver al manú principal)");
                                    
        int opcion = entradaIntConOpcionSalir(entradaTeclado, mensajeError_tipearNuevamenteNumeroOsalir());
        if (opcion == -1) return;
        
        if (validacionOpcionIncorrecta_listaMascotas(opcion, listaMascotas)) return;
                                    
        int IDmascotaAeliminar = obtenerIDmascota(listaMascotas, opcion);
        controladorLogico.eliminarMascota(IDmascotaAeliminar);

        imprimirSeparador();
        System.out.println("Mascota eliminada correctamente.");
    }

    
    // VER LISTA DE MASCOTAS (CON NOMBRE DE DUEÑO/A)
    public static void verListaDeMascotasConNombreDuenio(ControladoraLogica controladorLogico, Scanner entradaTeclado, boolean volverAlMenu) {
        imprimirSeparador();
        System.out.println("MASCOTAS REGISTRADAS");
                                    
        ArrayList<Mascota> listaMascotas = controladorLogico.traerListaMascotas();
        if (listaMascotas.size() < 0) {
            System.out.println("No tiene mascotas registradas.");
            return; // Corta el flujo del método
        }
 
        String nombreDuenio;
        
        // Creo una copia para no modificar la original
        ArrayList<Mascota> listaOrdenada = new ArrayList<>(listaMascotas);
        
        // Ordeno por nombre
        Collections.sort(listaOrdenada,Comparator.comparing(Mascota::getNombre));
        
        // // Muestro las mascotas ordenadas alfabéticamente
        for (int i = 0; i < listaOrdenada.size(); i++) {
            nombreDuenio = listaOrdenada.get(i).getDuenioAsociado().getNombre();
            System.out.println((i+1) + ". " + listaOrdenada.get(i).getNombre() + " (" + nombreDuenio + ")");
        }
        
        imprimirSeparador();
        System.out.println("Escriba 'salir' para cancelar y volver al menú principal");
        
        int opcion = entradaIntConOpcionSalir(entradaTeclado, "Entrada inválida. Por favor, escriba 'salir' para volver al menú.");
        if (opcion == -1) return;
        
        if (validacionOpcionLista(opcion)) return;
    }
    
    
    // FILTRAR MASCOTA POR RAZA
    public static void filtrarMascotaPorRaza(ControladoraLogica controladorLogico, Scanner entradaTeclado, boolean volverAlMenu) {
        imprimirSeparador();
        System.out.println("FILTRAR MASCOTA POR RAZA");
        
        ArrayList<Mascota> listaMascotas = controladorLogico.traerListaMascotas();
        
        Set<String> listaRazasSinDuplicados = new TreeSet<>();
        
        for (Mascota masc : listaMascotas) {
            listaRazasSinDuplicados.add(masc.getRaza());
        }
        
        verListaRazasSinDuplicados(listaRazasSinDuplicados);
        
        imprimirSeparador();
        System.out.println("Elija una opción (o escriba 'salir' para cancelar y volver al manú principal)");
        
        int opcion = entradaIntConOpcionSalir(entradaTeclado, mensajeError_tipearNuevamenteNumeroOsalir());
        if (opcion == -1) return;
        
        if (validacionOpcionIncorrecta_listaRazasSinRepetidos(opcion, listaRazasSinDuplicados)) return;
        
        ArrayList<String> listaRazasUnicas = verListaRazasSinDuplicados(listaRazasSinDuplicados);
        String razaElegida = listaRazasUnicas.get(opcion - 1);
        mostrarRazaElegida(listaMascotas, razaElegida);
    }
    
    public static ArrayList<String> verListaRazasSinDuplicados(Set<String> listaRazasSinRepetidos) {
        ArrayList<String> listaRazas = new ArrayList<>(listaRazasSinRepetidos);
        
        for (int i = 0; i < listaRazas.size(); i++) {
            System.out.println((i + 1) + ". " + listaRazas.get(i));
        }
        
        return listaRazas;
    }
    
    
    public static void mostrarRazaElegida(ArrayList<Mascota> listaMascotas, String razaElegida) {
        imprimirSeparador();
        
        for(Mascota mascota : listaMascotas) {
            if (mascota.getRaza().equalsIgnoreCase(razaElegida)) {
                System.out.println(mascota);
            }
        }
    }
    
    
    // FILTRAR MASCOTA POR DUEÑO
    public static void filtrarMascotaPorDuenio(ControladoraLogica controladorLogico, Scanner entradaTeclado, boolean volverAlMenu) {
        imprimirSeparador();
        System.out.println("FILTRAR MASCOTA POR DUEÑO");
        
        ArrayList<Duenio> listaDuenios = controladorLogico.traerListaDuenios();

        verMascotasPorDuenio(listaDuenios);
        
        imprimirSeparador();
        System.out.println("Elija una opción (o escriba 'salir' para cancelar y volver al manú principal)");
        
        int opcion = entradaIntConOpcionSalir(entradaTeclado, mensajeError_tipearNuevamenteNumeroOsalir());
        if (opcion == -1) return;
        
        if (validacionOpcionIncorrecta_listaDuenios(opcion, listaDuenios)) return;
        
        Duenio duenio = listaDuenios.get(opcion - 1);
        System.out.println(duenio.getListaMascota());
    }
    
    public static void verMascotasPorDuenio(ArrayList<Duenio> listaDuenios) {
        
        for (int i = 0; i < listaDuenios.size(); i++) {
            System.out.println((i + 1) + ". " + listaDuenios.get(i).getNombre());
        }
    }
    
    
    // MÉTODOS AUXILIARES PARA EVITAR REPETIR CÓDIGO
    public static void imprimirSeparador() {
        System.out.println("-----------------------");
    }
    
    public static void mensajeOpcionInvalida() {
        imprimirSeparador();
        System.out.println("Opción inválida.");
    }
    
    public static void imprimirIndicador_elijaUnaOpcion() {
        imprimirSeparador();
        System.out.println("Elija una opción: ");
    }
    
    public static String mensajeError_tipearNuevamenteNumeroOsalir() {
        return "Entrada inválida. Por favor, ingresá un número entero o escriba 'salir' para volver al menú.";
    }
    
    
    // FUNCIONES PARA LAS VALIDACIONES
    public static boolean validacionOpcionIncorrecta_listaDuenios(int opcion, ArrayList<Duenio> listaDuenios) {
        // Si el usuario elige una opción incorrecta, muestra mensaje de error y frena el flujo del método
        if (opcion < 1 || opcion > listaDuenios.size()) {
            mensajeOpcionInvalida();
            return true; // Hubo error
        }
        return false; // Todo bien
    }
    
    public static boolean validacionOpcionIncorrecta_listaRazasSinRepetidos(int opcion, Set<String> listaRazasSinDuplicados) {
        // Si el usuario elige una opción incorrecta, muestra mensaje de error y frena el flujo del método
        if (opcion < 1 || opcion > listaRazasSinDuplicados.size()) {
            mensajeOpcionInvalida();
            return true; // Hubo error
        }
        return false; // Todo bien
    }
    
    public static boolean validacionOpcionIncorrecta_listaMascotas(int opcion, ArrayList<Mascota> listaMascotas) {
        // Si el usuario elige una opción incorrecta, muestra mensaje de error y frena el flujo del método
        if (opcion < 1 || opcion > listaMascotas.size()) {
            mensajeOpcionInvalida();
            return true; // Hubo error
        }
        return false; // Todo bien
    }
    
    public static boolean validacionOpcionLista(int opcion) {
        // Si el usuario elige una opción incorrecta, muestra mensaje de error y frena el flujo del método
        if (opcion < 1 || opcion > 1) {
            mensajeOpcionInvalida();
            return true; // Hubo error
        }
        return false; // Todo bien
    }
    
    public static int entradaIntConOpcionSalir(Scanner entrada, String mensaje) {
        while (true) {
            String input = entrada.nextLine().trim();

            if (input.equalsIgnoreCase("salir")) {
                imprimirSeparador();
                System.out.println("Volviendo al menú...");
                return -1; // Usás -1 como valor especial para salir
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(mensaje);
            }
        }
    }
}
