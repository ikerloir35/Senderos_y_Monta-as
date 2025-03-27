# Senderos y Montañas - Sistema de Gestión de Club de Montaña

## Resumen del Proyecto

### Propósito

Este proyecto es un sistema integral de gestión de club de montaña diseñado para ayudar a organizar y gestionar actividades del club, membresías y excursiones. El sistema facilita la gestión de miembros del club (estándar, federados y niños), excursiones, inscripciones e información de seguros a través de una interfaz moderna basada en JavaFX.

### Stack Tecnológico

- **Lenguaje**: Java 22
- **Plataforma**: JavaFX
- **Arquitectura**: MVC (Modelo-Vista-Controlador)
- **Tecnologías Principales**:
  - JavaFX 22 para la interfaz de usuario
  - Hibernate 6.2.6 para operaciones de base de datos
  - MySQL 8.3.0 para almacenamiento de datos
  - FormsFX 11.6.0 para manejo de formularios
  - JUnit 5.10.0 para pruebas

### Características

- **Gestión de Miembros**:

  - Diferentes tipos de miembros (Estándar, Federados, Niños)
  - Registro y gestión de información de miembros
  - Seguimiento del estado de federación
  - Gestión de seguros

- **Gestión de Excursiones**:

  - Planificación y organización de excursiones
  - Gestión de información de senderos
  - Programación de actividades
  - Seguimiento de participantes

- **Sistema de Inscripción**:

  - Gestión de inscripciones a excursiones
  - Seguimiento de participación de miembros
  - Verificación de seguros
  - Registro de asistencia

- **Interfaz de Usuario**:

  - Interfaz moderna e intuitiva basada en JavaFX
  - Diseño responsivo
  - Formularios y controles interactivos
  - Navegación fácil de usar

- **Características Técnicas**:
  - Integración de base de datos con Hibernate
  - Persistencia y recuperación de datos
  - Validación y procesamiento de formularios
  - Generación de informes

### Seguridad

- **Seguridad de Datos**:

  - Conexiones seguras a la base de datos
  - Validación de datos
  - Copias de seguridad regulares

- **Mejores Prácticas**:
  - Validación de entrada
  - Prevención de inyección SQL
  - Manejo seguro de datos

### Co-Desarrolladores

- **Desarrollador Principal**:
  - Iker López Iribas
  - Damià Belles Sampera
- **Contributors**
  - Patricia Vallejo LLivicura

## Configuración del Entorno

### Prerrequisitos

- Java Development Kit (JDK) 22 o superior
- Maven 3.6 o superior
- MySQL 8.3.0 o superior
- Git

### Instrucciones de Configuración

1. **Clonar el Repositorio**

   ```bash
   git clone https://github.com/ikerloir35/Senderos_y_Monta-as.git
   ```

2. **Configuración del Entorno**

   - Configurar la conexión a la base de datos en `application.properties`
   - Configurar ajustes de la aplicación

3. **Configuración de Compilación**

   ```bash
   mvn clean install
   ```

4. **Ejecutar la Aplicación**
   ```bash
   mvn javafx:run
   ```

### Mejores Prácticas de Seguridad

1. **Seguridad del Código**

   - Auditorías de seguridad regulares
   - Escaneo de vulnerabilidades en dependencias
   - Proceso de revisión de código

2. **Flujo de Desarrollo**
   - Desarrollo por ramas de características
   - Revisión de pull requests
   - Pruebas automatizadas
   - Integración continua

### Solución de Problemas

1. **Problemas de Compilación**

   - Verificar compatibilidad de versión de Java
   - Verificar dependencias de Maven
   - Limpiar caché de Maven si es necesario

2. **Problemas de Ejecución**
   - Verificar conectividad de base de datos
   - Verificar configuración de la aplicación
   - Revisar logs de la aplicación

## Licencia

Este proyecto está licenciado bajo la Licencia Universal Creative Commons CC0 1.0. Esta es una licencia de dedicación al dominio público que permite a cualquier persona usar, modificar y distribuir el software libremente para cualquier propósito, incluyendo uso comercial, sin restricciones ni requisitos de atribución.

Para el texto completo de la licencia, consulte el archivo LICENSE.
