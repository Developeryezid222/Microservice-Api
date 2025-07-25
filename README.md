🎓 Microservicios Académicos - Spring Boot & Cloud
Este proyecto implementa una arquitectura de microservicios para una plataforma educativa ficticia, construida con Spring Boot y herramientas del ecosistema Spring Cloud. Contiene servicios separados para gestionar estudiantes y cursos, cada uno con su propia base de datos y expuestos mediante una puerta de enlace central.
🛠️ Tecnologías utilizadas
- Java 17, Spring Boot 3, Maven
- Spring Cloud: Eureka Server, Config Server, API Gateway
- Bases de datos:
- MySQL para el servicio Student
- PostgreSQL para el servicio Course
- Docker para contenedores (opcional)
- Kafka para comunicación asíncrona (opcional)
🧩 Arquitectura
Diagrama de Arquitectura ← (La imagen aparecerá aquí una vez generada)
- config-server: Centraliza la configuración externa (application.yml)
- eureka-server: Servicio de descubrimiento para registrar los microservicios
- api-gateway: Puerta de entrada a los microservicios, maneja rutas dinámicas
- student-service: CRUD para estudiantes + MySQL
- course-service: CRUD para cursos + PostgreSQL
📦 Estructura del proyecto
├── config-server/
├── eureka-server/
├── api-gateway/
├── student-service/
│   └── MySQL DB
├── course-service/
│   └── PostgreSQL DB
└── README.md


🚀 Ejecución del proyecto
- Clonar el repositorio.
- Iniciar config-server y eureka-server.
- Verificar que api-gateway puede enrutar correctamente.
- Ejecutar student-service y course-service.
- (Opcional) Configurar Kafka para eventos.
📚 Endpoints principales
- api-gateway/student/** → Redirige al microservicio de estudiantes
- api-gateway/course/** → Redirige al microservicio de cursos
  
