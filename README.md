<h1 align="center">🎓 Microservicios Académicos - Spring Boot & Cloud</h1>

Este proyecto presenta una arquitectura de microservicios para una plataforma educativa basica, desarrollada con Spring Boot y el ecosistema Spring Cloud. La solución incluye servicios independientes para la gestión de estudiantes y cursos, cada uno con su propia base de datos, integrados a través de una puerta de enlace centralizada.

### **🛠️ Tecnologías Utilizadas**

- Lenguaje y Framework: Java 17, Spring Boot 3
- Gestión de Dependencias: Maven
- Spring Cloud:
- Eureka Server: Registro y descubrimiento de servicios
- Config Server: Gestión centralizada de configuraciones
- API Gateway: Enrutamiento dinámico y acceso unificado


### **Bases de Datos:**
- MySQL (Servicio de Estudiantes)
- PostgreSQL (Servicio de Cursos)

- Contenerización: Docker (opcional)
- Mensajería: Kafka para comunicación asíncrona (opcional)


### **🧩 Arquitectura**
La arquitectura está diseñada para ser modular, escalable y tolerante a fallos, siguiendo los principios de microservicios. A continuación, se describe la estructura general:
 (La imagen se incluirá aquí una vez generada)

- Config Server: Centraliza las configuraciones externas (application.yml) para todos los microservicios.
  
- Eureka Server: Facilita el registro y descubrimiento dinámico de los microservicios.
- API Gateway: Punto de entrada único que enruta las solicitudes a los servicios correspondientes.
- Student Service: Gestiona operaciones CRUD para estudiantes, respaldado por una base de datos MySQL.
- Course Service: Administra operaciones CRUD para cursos, utilizando una base de datos PostgreSQL.
____________________________________________________________________________________________________

### **📦 Estructura del Proyecto**

microservices-academic/

├── config-server/             # Gestión de configuraciones externas

├── eureka-server/             # Registro y descubrimiento de servicios

├── api-gateway/               # Puerta de enlace para enrutamiento

├── student-service/           # Microservicio para estudiantes

│   └── MySQL DB              # Base de datos para estudiantes

├── course-service/            # Microservicio para cursos

│   └── PostgreSQL DB         # Base de datos para cursos

└── README.md                  # Documentación del proyecto


### **🚀 Ejecución del Proyecto**
Sigue estos pasos para poner en marcha el proyecto:

Clonar el repositorio:git clone <URL-del-repositorio>


- Iniciar Config Server: Asegúrate de que el servidor de configuraciones esté activo para cargar las propiedades de los microservicios.
- Iniciar Eureka Server: Levanta el servidor de descubrimiento para registrar los microservicios.
- Iniciar API Gateway: Verifica que la puerta de enlace pueda enrutar correctamente las solicitudes.
- Ejecutar Student Service y Course Service: Inicia ambos servicios, que se conectarán automáticamente a sus respectivas bases de datos (MySQL y PostgreSQL).
- (Opcional) Configurar Kafka: Si se desea habilitar la comunicación asíncrona, configura el clúster de Kafka y asegúrate de que los servicios estén suscritos a los tópicos correspondientes.
_______________________________________________________________________________

### **📚 Endpoints Principales**
El API Gateway actúa como punto de entrada único y redirige las solicitudes a los microservicios correspondientes:


# **RESTful API Endpoints**
Descripción

/v1/student/**
Redirige al microservicio de estudiantes

/v1/courses/**
Redirige al microservicio de cursos

Ejemplo de uso:

- GET: v1/students: Obtiene la lista de estudiantes.
- POST: v1/courses: Crea un nuevo curso.
- PUT: /v1/courses/{id}
- DELETE:


### **📝 Notas Adicionales**

Contenerización: Cada microservicio puede ejecutarse en contenedores Docker para facilitar el despliegue y la escalabilidad.
Escalabilidad: La arquitectura permite añadir nuevos microservicios según las necesidades de la plataforma.
Comunicación Asíncrona: Kafka puede configurarse para manejar eventos como la inscripción de estudiantes en cursos.

  
