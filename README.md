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
