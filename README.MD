
# Sistema de Gestión de Inventarios

[![Java](https://img.shields.io/badge/Java-17-red.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.12-green.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-27.3.1-blue.svg)](https://www.docker.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16.1-336791.svg)](https://www.postgresql.org/)

Sistema integral de gestión de inventarios diseñado con arquitectura de microservicios, implementando las mejores prácticas de desarrollo y patrones de diseño modernos.

---

## Estructura del Proyecto

```plaintext
Inventario/
├── InventarioBack/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   ├── docker-compose.yml
│   └── pom.xml
└── README.md
```

## Características Principales

### Backend
- **Java 17**: Uso de características modernas como `Records`, `Pattern Matching` y `Sealed Classes`.
- **Spring Boot 3.0.12**: Framework para el desarrollo de APIs REST con:
  - **Spring Data JPA** para la persistencia de datos.
  - **Spring Validation** para validación de datos.
- **PostgreSQL 16.1**: Base de datos robusta y escalable.
- **Arquitectura de Microservicios**: Cada servicio tiene responsabilidades específicas y se comunica a través de API REST.
- **Docker Compose**: Orquestación de contenedores para simplificar el despliegue en entornos locales y de producción.

---

## Requisitos del Sistema

### Herramientas Necesarias

| Herramienta         | Versión Mínima | Descripción                                  |
|----------------------|----------------|----------------------------------------------|
| **Java**            | 17.0          | JDK para desarrollo y ejecución.             |
| **Docker Desktop**  | 27.3.1        | Containerización y orquestación.             |
| **Maven**           | 3.9.7         | Gestión de dependencias y construcción.      |
| **Git**             | 2.45.1        | Control de versiones.                        |

### IDEs Recomendados
- **Backend**: IntelliJ IDEA 2023.3 o superior.
- **Base de Datos**: DBeaver 23.2.x, DataGrip o PGAdmin 6.1.x.

---

## Configuración del Entorno de Desarrollo

### 1. Clonar el Repositorio
```bash
git clone https://github.com/weizmanfabian/Inventario.git
cd Inventario
```

### 2. Configuración de la Base de Datos

#### 2.1. Crear Contenedores con Docker
Acceda al directorio del backend y levante los contenedores:
```bash
cd InventarioBack
docker-compose up -d
```

Para detener los contenedores después de las pruebas:
```bash
docker-compose down
```

El archivo `docker-compose.yml` incluye:
- PostgreSQL 16.1 con persistencia de datos.
- Volúmenes para asegurar la persistencia.
- Variables de entorno configuradas para desarrollo.

#### 2.2. Credenciales de la Base de Datos
Conéctese con estas credenciales si desea inspeccionar la base de datos:
```properties
Host: localhost
Puerto: 5437
Usuario: weizman
Contraseña: YourStrong#Passw0rd
Base de datos: inventario
```

### 3. Ejecución del Backend
Compilar y ejecutar el proyecto:
```bash
mvn clean install
mvn spring-boot:run
```
La API REST estará disponible en: `http://localhost:8087/inventario`

---

## Pruebas de la Aplicación

### 1. Configurar Postman
1. Importe la colección `Inventario.postman_collection` que se encuentra en el directorio raíz del proyecto.
2. Ejecute las pruebas disponibles.

### 2. Proceso de Autorización
1. Acceda a [OAuth Debugger](https://oauthdebugger.com/).
2. Inicie sesión con uno de los usuarios de prueba:
    - **Usuarios disponibles:**
        - `weizman.herreno@email.com`
        - `maria.gonzalez@email.com`
        - `carlos.rodriguez@email.com`
    - **Contraseñas disponibles:**
        - `password123`
        - `password456`
        - `password789`
3. Copie el código de autorización (`Authorization code`) proporcionado tras iniciar sesión.

### 3. Autorización en Postman
1. Abra la petición **Token** en Postman.
2. Pegue el `Authorization code` en el parámetro `code` del cuerpo de la solicitud.
3. Envíe la petición para obtener el token de autorización.

### 4. Pruebas Disponibles
- **Consultar Estado Actual:** Verifique el estado actual del inventario.
- **Registrar Entrada y Salida:** Ejecute las peticiones correspondientes cambiando los parámetros según sea necesario.

---

## Persistencia de Datos
Los cambios realizados pueden verificarse directamente desde la API o en la base de datos conectándose con las credenciales proporcionadas.

