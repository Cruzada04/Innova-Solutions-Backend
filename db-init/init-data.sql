-- Crear la base de datos si no existe (Esto es para cuando se usa fuera de Docker)
-- SELECT 'CREATE DATABASE db_innovasolutions' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'db_innovasolutions');

-- Nota: Docker Compose ya crea la base de datos db_innovasolutions automáticamente
-- debido a la variable POSTGRES_DB. Este script se ejecuta DENTRO de esa base de datos.

-- Insertar Roles básicos
INSERT INTO rol (nombre) VALUES ('ADMIN'), ('TUTOR'), ('ESTUDIANTE') ON CONFLICT DO NOTHING;

-- Insertar Planes de Suscripción
INSERT INTO plan_suscripcion (nombre, precio) VALUES ('BASICO', 0.0), ('PREMIUM', 29.99) ON CONFLICT DO NOTHING;

-- Insertar Categorías iniciales
INSERT INTO categoria (nombre, icono_url) VALUES 
('Lenguaje', 'https://icon-library.com/images/language-icon/language-icon-2.jpg'),
('Matemáticas', 'https://icon-library.com/images/math-icon/math-icon-10.jpg'),
('Habilidades Sociales', 'https://icon-library.com/images/social-skills-icon/social-skills-icon-5.jpg')
ON CONFLICT DO NOTHING;

-- Insertar un Usuario Admin (Password: password123)
INSERT INTO usuario (nombre_completo, correo_electronico, contrasena, metodo_registro, rol_id, plan_id) 
VALUES ('Administrador Sistema', 'admin@innovasolutions.com', 'password123', 'DIRECTO', 1, 2)
ON CONFLICT DO NOTHING;
