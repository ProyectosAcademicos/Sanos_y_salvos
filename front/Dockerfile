# =========================
# 1. BUILD STAGE
# =========================
FROM node:20-alpine AS builder

WORKDIR /app

# Copiamos dependencias primero (mejor cache)
COPY package*.json ./

RUN npm install

# Copiamos el resto del proyecto
COPY . .

# Build de producción (Vite)
RUN npm run build


# =========================
# 2. PRODUCTION STAGE
# =========================
FROM nginx:alpine

# Eliminamos la config default de nginx
RUN rm -rf /usr/share/nginx/html/*

# Copiamos el build al nginx
COPY --from=builder /app/dist /usr/share/nginx/html

# Exponemos el puerto
EXPOSE 80

# Iniciamos nginx
CMD ["nginx", "-g", "daemon off;"]
