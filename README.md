# Arquitectura hexagonal template Java

[![codecov](https://codecov.io/gh/javiertelioz/hexagonal-architecture-java/graph/badge.svg?token=VA3OL8MXQ9)](https://codecov.io/gh/javiertelioz/hexagonal-architecture-java)

Este proyecto es una aplicación de gestión de tareas que implementa la arquitectura hexagonal, también conocida como
Ports and Adapters. Diseñada para demostrar cómo esta arquitectura facilita la separación de responsabilidades, la
aplicación permite realizar operaciones básicas como crear, leer, actualizar y eliminar tareas, además de interactuar
con APIs externas para obtener información adicional sobre las tareas.

## ¿Qué es la Arquitectura Hexagonal?

La arquitectura hexagonal promueve una clara separación entre la lógica de negocio y los mecanismos de entrada/salida a
través de tres capas principales:

* **Dominio**: Incluye entidades y lógica de negocio, centrando el diseño en las reglas y procedimientos del negocio sin
  depender de la infraestructura.
* **Aplicación**: Gestiona los casos de uso y coordina la comunicación entre los puertos de entrada (interfaces para
  acciones externas) y de salida (interfaces para acciones internas hacia servicios o bases de datos).
* **Infraestructura**: Implementa los adaptadores y puertos de salida, configurando y gestionando la interacción con
  servicios externos y la infraestructura de soporte.

## Principios SOLID en la Arquitectura Hexagonal

* **Principio de Responsabilidad Única**: Cada capa tiene una única responsabilidad, lo que simplifica el mantenimiento
  y la evolución del código.
* **Principio Abierto/Cerrado**: Facilita la extensión de funcionalidades sin modificar el código existente.
* **Principio de Sustitución de Liskov**: Permite la intercambiabilidad de adaptadores e implementaciones sin alterar el
  comportamiento del sistema.
* **Principio de Segregación de Interfaces**: Define interfaces específicas para cada funcionalidad, mejorando la
  claridad y la implementación de adaptadores.
* **Principio de Inversión de Dependencias**: Utiliza la inyección de dependencias para desacoplar las capas de dominio
  y aplicación de las implementaciones concretas.

## API Endpoints

La aplicación expone las siguientes rutas de la API para la gestión de tareas:

### Crear una tarea

Método: POST
Ruta: /api/tasks
Input: JSON con la información de la tarea (title, description y completed)

```
{
   "title": "Ejemplo de título",
   "description": "Ejemplo de descripción",
   "completed": false
}
```

### Obtener una tarea por ID

Método: GET
Ruta: /api/tasks/{taskId}
Input: taskId en la ruta (reemplazar {taskId} con el ID de la tarea que deseas obtener)

### Obtener todas las tareas

Método: GET
Ruta: /api/tasks

### Actualizar una tarea

Método: PUT
Ruta: /api/tasks/{taskId}
Input: taskId en la ruta (reemplazar {taskId} con el ID de la tarea que deseas actualizar) y JSON con la información
actualizada de la tarea (title, description y completed)

```
   {
   "title": "Nuevo título",
   "description": "Nueva descripción",
   "completed": true
   }
```

### Eliminar una tarea por ID

Método: DELETE
Ruta: /api/tasks/{taskId}
Input: taskId en la ruta (reemplazar {taskId} con el ID de la tarea que deseas eliminar)

### Obtener información adicional de una tarea

Método: GET
Ruta: /api/tasks/{taskId}/additional-info
Input: taskId en la ruta (reemplazar {taskId} con el ID de la tarea para la que deseas obtener información adicional)
Pruebas

Puedes usar herramientas como Postman o curl para probar estas rutas. Asegúrate de que la aplicación esté en ejecución
antes de realizar las pruebas.
