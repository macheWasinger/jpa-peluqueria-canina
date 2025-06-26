# Peluquería Canina 🐶 - Proyecto Java con JPA y MySQL

Este es un proyecto que desarrollé como práctica para aplicar Programación Orientada a Objetos en Java y trabajar con persistencia de datos usando JPA (con EclipseLink) y MySQL. La idea fue simular una app simple para una peluquería canina, donde se pueden registrar dueños y sus mascotas.

No tiene interfaz gráfica, lo probé directamente desde consola porque el foco del proyecto está puesto en la lógica y en el manejo de datos con JPA.

---

## Tecnologías utilizadas

- Java (JDK 17)  
- JPA 2.1 con EclipseLink  
- MySQL  
- Maven  
- NetBeans

---

## Funcionalidades

- Agregar, editar, eliminar y consultar dueños de mascotas  
- Ver todos los dueños registrados o consultar uno en detalle (junto a sus mascotas)  
- Agregar mascotas asociándolas a un dueño existente  
- Editar y eliminar mascotas  
- Listar todas las mascotas mostrando el nombre de su dueño  
- Filtrar mascotas por raza o por nombre de dueño  
- Persistencia completa de los datos usando JPA (EclipseLink) con relaciones OneToMany y ManyToOne


---

## Cómo ejecutar el proyecto

1. Tener instalado Java (JDK 17 o superior)  
2. Tener MySQL corriendo y crear una base de datos para el proyecto  
3. Configurar los datos de conexión en el archivo `persistence.xml`  
4. Abrir el proyecto en NetBeans  
5. Ejecutarlo desde consola o desde el IDE

---

## Cosas a futuro (pendientes)

- Agregar una interfaz gráfica o una API web  
- Validaciones más completas  
- Más funcionalidades como historial de atención, etc.

---

## Autor

Marcelo Wasinger

---

Gracias por pasarte por el repo. Cualquier sugerencia o mejora es bienvenida.
