# roony-error-quarkus

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
![Java](https://img.shields.io/badge/Java-21%2B-blue)
![Quarkus](https://img.shields.io/badge/Quarkus-3.x-blueviolet)

Integración de `roony-error-core` para Quarkus / Jakarta REST.  
Registra automáticamente `ExceptionMapper`s que convierten tus `AppException` en respuestas JSON estructuradas, incluyendo `traceId` y `path`.

## Instalación

```xml
<dependency>
    <groupId>io.github.roony</groupId>
    <artifactId>roony-error-quarkus</artifactId>
    <version>1.1.0</version>
</dependency>
```

## Configuración adicional en application.properties
# Indexar la librería para que Quarkus descubra los providers
```properties
quarkus.index-dependency.roony-error-quarkus.group-id=io.github.roony
quarkus.index-dependency.roony-error-quarkus.artifact-id=roony-error-quarkus

# Opcional: omitir campos null en el JSON de error
quarkus.jackson.serialization-inclusion=NON_NULL
```

## Uso
# Lanza tus excepciones normalmente en la capa de servicio:
```java
throw new NotFoundException("Producto", id);
throw new AlreadyExistsException("Categoría duplicada");
```
# Los mappers las capturan automáticamente y devuelven:
```json
{
    "code": "ERR-0003",
    "message": "Producto no encontrado: 5",
    "timestamp": "2026-06-14T19:30:00Z",
    "traceId": "abc123...",
    "path": "/api/productos/5"
}
```

## Extender con categorías personalizadas
# Crea una categoría nueva implementando ErrorCategory
```java
public class MiCategoria 
{
    public static final ErrorCategory MI_ERROR = () -> "MI_ERROR";
}
```

# Regístrala con un código HTTP al iniciar la aplicación
```java
import io.github.roony.error.quarkus.HttpStatusRegistry;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ErrorHttpConfig 
{
    @PostConstruct
    void register() 
    {
        HttpStatusRegistry.register(MiCategoria.MI_ERROR, 418);
    }
}
```

LICENCIA MIT [Roony11-1]