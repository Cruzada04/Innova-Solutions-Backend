-- Roles básicos
INSERT INTO rol (nombre) VALUES ('ADMIN'), ('TUTOR'), ('ESTUDIANTE');

-- Planes de Suscripción
INSERT INTO plan_suscripcion (nombre, precio) VALUES ('BASICO', 0.0), ('PREMIUM', 29.99);

-- Categorías iniciales
INSERT INTO categoria (nombre, icono_url) VALUES ('Lenguaje', 'https://icon-library.com/images/language-icon/language-icon-2.jpg');
INSERT INTO categoria (nombre, icono_url) VALUES ('Matemáticas', 'https://icon-library.com/images/math-icon/math-icon-10.jpg');
INSERT INTO categoria (nombre, icono_url) VALUES ('Habilidades Sociales', 'https://icon-library.com/images/social-skills-icon/social-skills-icon-5.jpg');

-- Usuario Admin inicial
-- Nota: La contraseña aquí es texto plano para pruebas, pero debe encriptarse cuando agreguen Security.
INSERT INTO usuario (nombre_completo, correo_electronico, contrasena, metodo_registro, rol_id, plan_id) 
VALUES ('Administrador Sistema', 'admin@innovasolutions.com', 'password123', 'DIRECTO', 1, 2);
