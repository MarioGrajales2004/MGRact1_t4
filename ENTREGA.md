# MGRact1_t4 — Spring Boot Act 1 (Mario Eduardo Grajales Ramírez)

## Endpoints
| Método | Ruta | Respuesta |
|---|---|---|
| GET | `/api/proyecto` | `MGR - Spring Boot Act 1 - Entorno Local OK` |
| GET | `/api/estudiante` | JSON con nombre, carrera, materias, estatus |
| GET | `/estado` | JSON con estado del servidor y fecha/hora |

## Comandos locales (Windows)
```
./mvnw spring-boot:run          # desarrollo
./mvnw clean package            # genera target/demo-0.0.1-SNAPSHOT.jar
java -jar target/demo-0.0.1-SNAPSHOT.jar --server.port=8080
```

## Despliegue en VPS Ubuntu
a) Subir el JAR (desde tu PC):
```
scp target/demo-0.0.1-SNAPSHOT.jar usuario@IP_DEL_VPS:~/
```
b) Puerto personalizado en `src/main/resources/application.properties` (antes de empaquetar):
```
server.port=8081
```
(Alternativa sin recompilar: `java -jar app.jar --server.port=8081`.)

c) Firewall y Java en el VPS:
```
sudo apt update && sudo apt install -y openjdk-17-jre-headless
sudo ufw allow 8081/tcp
```
d) Ejecutar en segundo plano:
```
nohup java -jar demo-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```
Verificar: `curl http://localhost:8081/api/proyecto` y `tail -f app.log`.

## Respuestas para Classroom
**a) @Controller vs @RestController.** `@Controller` marca una clase como controlador MVC tradicional: el valor que retorna un método se interpreta como el *nombre de una vista* (p. ej. una plantilla Thymeleaf/JSP) que un `ViewResolver` renderiza como HTML; para devolver datos directamente se debe añadir `@ResponseBody` a cada método. `@RestController` equivale a `@Controller` + `@ResponseBody`: no se resuelven vistas, y el objeto retornado se escribe en el cuerpo de la respuesta HTTP, serializándose automáticamente a JSON (vía Jackson) o texto plano si es `String`. Por eso es la opción para APIs REST.

**b) @GetMapping.** Es un atajo de `@RequestMapping(method = RequestMethod.GET)`. Se coloca sobre un método para que Spring le enrute las peticiones HTTP GET de una ruta concreta, que se declara como atributo: `@GetMapping("/proyecto")`. Si la clase tiene `@RequestMapping("/api")`, la ruta final es la concatenación: `/api/proyecto`.

**c) Puerto 8081 en lugar de 8080.** 8080 es el puerto por defecto de Tomcat embebido y es el más usado por otros servicios (proxies, otras aplicaciones Java, herramientas de administración); si ya está ocupado, la app falla con "Port 8080 was already in use". Definirlo manualmente con `server.port=8081` evita colisiones, hace explícito y reproducible el puerto expuesto, y permite abrir en el firewall (`ufw`) únicamente ese puerto, reduciendo la superficie de ataque, y convivir con otras aplicaciones en el mismo VPS.

**d) Enlaces de entrega.**
- Repositorio GitHub: `https://github.com/<TU_USUARIO>/MGRact1_t4`
- Servicio en el VPS: `http://IP_DEL_VPS:8081/api/proyecto`
