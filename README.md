# Sanos_y_salvos

## 🐳 Levantar Frontend con Docker

### 1. Construir la imagen

Desde la carpeta `front/`, ejecutar:

```bash
docker build -t front-app .
```

### 2. Ejecutar el contenedor

```bash
docker run -p 3000:80 front-app
```

### 3. Acceder a la aplicación

```bash
http://localhost:3000
```

### 📦 Tecnologías utilizadas

Docker
Nginx
Vite
React


```bash

echo "======================================="
echo "   ESTRUCTURA DEL PROYECTO"
echo "======================================="
echo ""

echo "📁 FRONTEND"
echo "   └── front/"
echo "       └── Aplicación React + Nginx"
echo ""

echo "📁 BACKEND"
echo "   └── backend/"
echo "       ├── api-gateway/"
echo "       ├── usuarios/"
echo "       ├── mascotas/"
echo "       ├── matching/"
echo "       ├── notificaciones/"
echo "       └── reportes/"
echo ""

echo "📁 BASES DE DATOS"
echo "   └── MySQL (contenedores Docker)"
echo "       ├── usuarios-db"
echo "       ├── mascotas-db"
echo "       ├── matching-db"
echo "       ├── notificaciones-db"
echo "       └── reportes-db"
echo ""

echo "🐳 DOCKER"
echo "   ├── docker-compose.yml"
echo "   └── Orquesta todos los servicios:"
echo "       ├── frontend"
echo "       ├── api-gateway"
echo "       ├── microservicios"
echo "       └── bases de datos"
echo ""

echo "======================================="
echo "✔ Arquitectura levantada con Docker Compose"
echo "======================================="
```


### Lógica del flujo 

```bash
┌─────────────┐
│   Usuario   │
└──────┬──────┘
       │
       │ Solicitud HTTP
       ▼
┌─────────────────┐
│   API Gateway   │
│   Puerto 8081   │
└──────┬──────────┘
       │
       │ Analiza la ruta solicitada
       │ y redirige al microservicio
       ▼
 ┌───────────────────────────────┐
 │      Microservicios           │
 ├───────────────────────────────┤
 │ /auth/usuarios/**             │
 │ → usuarios-service            │
 │                               │
 │ /usuarios/**                  │
 │ → usuarios-service            │
 │                               │
 │ /api/mascotas/**              │
 │ → mascotas-service            │
 │                               │
 │ /api/matching/**              │
 │ → matching-service            │
 │                               │
 │ /api/notificaciones/**        │
 │ → notificaciones-service      │
 │                               │
 │ /reportes/**                  │
 │ → reportes-service            │
 └───────────────┬───────────────┘
                 │
                 │ Consulta/Actualización
                 ▼
        ┌─────────────────┐
        │ Base de Datos   │
        │ Propia de cada  │
        │ Microservicio   │
        └────────┬────────┘
                 │
                 │ Respuesta
                 ▼
        ┌─────────────────┐
        │ Microservicio   │
        └────────┬────────┘
                 │
                 │ Respuesta HTTP
                 ▼
        ┌─────────────────┐
        │  API Gateway    │
        └────────┬────────┘
                 │
                 ▼
        ┌─────────────────┐
        │    Usuario      │
        └─────────────────┘

```
