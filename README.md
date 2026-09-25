# Innova Solutions · Backend

API REST de **InnovaSolutions**, una plataforma educativa basada en **flashcards** para apoyar el aprendizaje de niños menores de 10 años dentro del espectro autista. Permite que docentes y tutores creen flashcards con imagen, colores y opciones de respuesta, las organicen en lecciones y temas, y sigan el progreso de cada estudiante.

Proyecto académico desarrollado por el **Grupo 06** (Facultad de Ingeniería, UPC), curso *Arquitectura de Aplicaciones Web*.

Frontend: [Innova-Solutions-FrontEnd](https://github.com/Cruzada04/Innova-Solutions-FrontEnd)

## Funcionalidades

- Registro e inicio de sesión con **autenticación JWT** (Spring Security).
- Gestión de usuarios, roles y relaciones tutor–estudiante (incluye registro de alumnos).
- **Flashcards** con texto, imagen, colores de fondo y de texto, y opciones de respuesta (creación de la flashcard con sus opciones en una sola petición).
- Organización por categorías, temas y lecciones personalizadas.
- Progreso de evaluaciones con puntaje y medallas.
- Elementos guardados, reseñas, perfiles de aprendizaje, configuraciones y planes de suscripción.
- Subida de imágenes.
- **Reportes:** progreso por mes, flashcards y lecciones por dificultad, y estadísticas del dashboard del docente.
- Documentación interactiva de la API con **Swagger UI** (OpenAPI).

## Tecnologías

| Área | Herramientas |
|---|---|
| Lenguaje y framework | Java 17, Spring Boot 3.5 |
| Persistencia | Spring Data JPA, Hibernate, PostgreSQL |
| Seguridad | Spring Security, JWT (jjwt) |
| Documentación | springdoc-openapi (Swagger UI) |
| Otros | Lombok, ModelMapper, Bean Validation |
| Despliegue | Docker (multi-stage build), Docker Compose |

## Arquitectura

Arquitectura en capas, con paquetes por responsabilidad en `com.upc.innovasolutionsbackend`:

```
controladores/   Endpoints REST
servicios/       Lógica de negocio
repositorios/    Acceso a datos (Spring Data JPA)
entidades/       Modelo de dominio (JPA)
dtos/            Objetos de petición y respuesta
security/        JWT, filtros y configuración de seguridad
config/          Configuración web y ModelMapper
```

## Cómo ejecutarlo

### Opción 1: Docker Compose (recomendada)

Requiere Docker. Levanta PostgreSQL y la API.

```bash
git clone https://github.com/Cruzada04/Innova-Solutions-Backend.git
cd Innova-Solutions-Backend
docker compose up --build
```

La API queda en `http://localhost:8080`.

### Opción 2: Local con Maven

Requisitos: Java 17 y PostgreSQL con una base de datos llamada `db_innovasolutions`.

```bash
./mvnw spring-boot:run
```

### Variables de entorno

| Variable | Descripción | Valor por defecto |
|---|---|---|
| `SPRING_DATASOURCE_URL` | URL de PostgreSQL | `jdbc:postgresql://localhost:5432/db_innovasolutions` |
| `SPRING_DATASOURCE_USERNAME` | Usuario de la base de datos | `postgres` |
| `SPRING_DATASOURCE_PASSWORD` | Contraseña de la base de datos | (definir) |
| `JWT_SECRET` | Clave para firmar los tokens JWT | (definir una propia) |
| `IP_FRONTEND` | Origen permitido del frontend | `http://localhost:4200` |

> Usa valores propios para `JWT_SECRET` y la contraseña. No los subas al repositorio.

## Documentación de la API

Con la aplicación en ejecución:

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI (JSON): `http://localhost:8080/v3/api-docs`

Para consumir los endpoints protegidos, inicia sesión en `POST /api/authenticate` y envía el token en el encabezado `Authorization: Bearer <token>`.

### Recursos principales

| Recurso | Ruta base |
|---|---|
| Autenticación | `/api/authenticate` |
| Usuarios | `/api/usuarios` (incluye `/registro-alumno` y `/maestro/dashboard-stats`) |
| Roles | `/api/roles` |
| Flashcards | `/api/flashcards` (incluye `/con-opciones` y `/reporte/dificultad`) |
| Temas | `/api/temas` |
| Lecciones | `/api/lecciones-custom` (incluye `/reporte/dificultad`) |
| Progreso de evaluaciones | `/api/progresos-evaluacion` (incluye `/reporte/pormes`) |
| Elementos guardados | `/api/elementos-guardados` |
| Relaciones tutor–estudiante | `/api/relaciones-tutor-estudiante` |
| Perfiles de aprendizaje | `/api/perfiles-aprendizaje` |
| Reseñas | `/api/resenas` |
| Configuraciones | `/api/configuraciones` |
| Planes de suscripción | `/api/planes-suscripcion` |
| Subida de archivos | `/api/upload` |

## Equipo

Grupo 06, UPC. Mi participación: [describe tu rol, por ejemplo: diseño de la API REST, seguridad con JWT, modelo de datos y despliegue con Docker].
