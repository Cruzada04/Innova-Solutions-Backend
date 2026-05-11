-- ==========================================================
-- MODIFICADO AQUÍ PARA QUE CORRA BIEN AL MOMENTO DE HACER COMPOSE UP
-- Data balanceada para pruebas de reportes y seguridad
-- ==========================================================

-- 1. Roles (Sincronizado con la nueva tabla 'roles' de Josue)
INSERT INTO roles (name) VALUES ('ADMIN');       -- ID 1
INSERT INTO roles (name) VALUES ('TUTOR');       -- ID 2
INSERT INTO roles (name) VALUES ('ESTUDIANTE');  -- ID 3

-- 2. Planes de Suscripción
INSERT INTO plan_suscripcion (nombre, precio) VALUES ('BASICO', 0.0);    -- ID 1
INSERT INTO plan_suscripcion (nombre, precio) VALUES ('PREMIUM', 29.99); -- ID 2

-- 3. Categorías iniciales
INSERT INTO categoria (nombre, icono_url) VALUES ('Lenguaje', 'https://icon-library.com/images/language-icon/language-icon-2.jpg');            -- ID 1
INSERT INTO categoria (nombre, icono_url) VALUES ('Matemáticas', 'https://icon-library.com/images/math-icon/math-icon-10.jpg');               -- ID 2
INSERT INTO categoria (nombre, icono_url) VALUES ('Habilidades Sociales', 'https://icon-library.com/images/social-skills-icon/social-skills-icon-5.jpg'); -- ID 3

-- 4. Usuarios de prueba
-- Contraseña para todos: password123 (Encriptada con BCrypt para que funcione el Security)
-- Admin
INSERT INTO usuario (nombre_completo, username, contrasena, correo_electronico, metodo_registro, rol_id, plan_id)
VALUES ('Administrador Sistema', 'admin', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOn2', 'admin@innovasolutions.com', 'DIRECTO', 1, 2); -- ID 1

-- Tutor
INSERT INTO usuario (nombre_completo, username, contrasena, correo_electronico, metodo_registro, rol_id, plan_id)
VALUES ('Profesor Agustin', 'tutor1', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOn2', 'tutor@innovasolutions.com', 'DIRECTO', 2, 1); -- ID 2

-- Estudiantes
INSERT INTO usuario (nombre_completo, username, contrasena, correo_electronico, metodo_registro, rol_id, plan_id)
VALUES ('Alumno Uno', 'alumno1', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOn2', 'alumno1@test.com', 'DIRECTO', 3, 1); -- ID 3

INSERT INTO usuario (nombre_completo, username, contrasena, correo_electronico, metodo_registro, rol_id, plan_id)
VALUES ('Alumno Dos', 'alumno2', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOn2', 'alumno2@test.com', 'DIRECTO', 3, 1); -- ID 4

-- 5. Relaciones de Roles (Tabla unificada 'usuario_rol')
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (1, 1); -- Admin
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (2, 2); -- Tutor
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (3, 3); -- Estudiante 1
INSERT INTO usuario_rol (usuario_id, rol_id) VALUES (4, 3); -- Estudiante 2

-- 6. Temas
INSERT INTO tema (nombre, categoria_id) VALUES ('Las Vocales', 1);        -- ID 1
INSERT INTO tema (nombre, categoria_id) VALUES ('Consonantes', 1);        -- ID 2
INSERT INTO tema (nombre, categoria_id) VALUES ('Números 1-10', 2);       -- ID 3
INSERT INTO tema (nombre, categoria_id) VALUES ('Suma Simple', 2);        -- ID 4
INSERT INTO tema (nombre, categoria_id) VALUES ('Mis Emociones', 3);      -- ID 5

-- 7. Lecciones (Diferentes dificultades para probar reportes)
INSERT INTO leccion_custom (titulo, dificultad, creador_id, estudiante_id, tema_id)
VALUES ('Aprendiendo la A y E', 'FACIL', 2, 3, 1);    -- ID 1
INSERT INTO leccion_custom (titulo, dificultad, creador_id, estudiante_id, tema_id)
VALUES ('Números Básicos', 'FACIL', 2, 3, 3);         -- ID 2
INSERT INTO leccion_custom (titulo, dificultad, creador_id, estudiante_id, tema_id)
VALUES ('Sumando con Manzanas', 'MEDIO', 2, 3, 4);    -- ID 3
INSERT INTO leccion_custom (titulo, dificultad, creador_id, estudiante_id, tema_id)
VALUES ('Identificando Tristeza', 'MEDIO', 2, 3, 5);  -- ID 4
INSERT INTO leccion_custom (titulo, dificultad, creador_id, estudiante_id, tema_id)
VALUES ('Consonantes Complejas', 'DIFICIL', 2, 3, 2); -- ID 5

-- 8. Flashcards
INSERT INTO flashcard (pregunta_texto, imagen_url, color_fondo, color_texto, leccion_id)
VALUES ('¿Qué vocal es esta? A', 'url_a', '#FFFFFF', '#000000', 1);
INSERT INTO flashcard (pregunta_texto, imagen_url, color_fondo, color_texto, leccion_id)
VALUES ('¿Qué vocal es esta? E', 'url_e', '#FFFFFF', '#000000', 1);
INSERT INTO flashcard (pregunta_texto, imagen_url, color_fondo, color_texto, leccion_id)
VALUES ('¿Cuánto es 1 + 1?', 'url_suma', '#E3F2FD', '#0D47A1', 3);
INSERT INTO flashcard (pregunta_texto, imagen_url, color_fondo, color_texto, leccion_id)
VALUES ('¿Cómo se siente este niño?', 'url_emocion', '#FFF3E0', '#E65100', 4);

-- 9. Progreso de Evaluación (Datos para reporte por mes 2026)
-- Marzo 2026
INSERT INTO progreso_evaluacion (puntaje, medallas_obtenidas, fecha_evaluacion, reporte_generado, estudiante_id, leccion_id)
VALUES (80, 2, '2026-03-15 10:00:00', 'Buen progreso inicial', 3, 1);
INSERT INTO progreso_evaluacion (puntaje, medallas_obtenidas, fecha_evaluacion, reporte_generado, estudiante_id, leccion_id)
VALUES (70, 1, '2026-03-20 11:00:00', 'Necesita practicar más', 3, 2);

-- Abril 2026
INSERT INTO progreso_evaluacion (puntaje, medallas_obtenidas, fecha_evaluacion, reporte_generado, estudiante_id, leccion_id)
VALUES (90, 3, '2026-04-10 09:30:00', 'Excelente dominio', 3, 1);
INSERT INTO progreso_evaluacion (puntaje, medallas_obtenidas, fecha_evaluacion, reporte_generado, estudiante_id, leccion_id)
VALUES (85, 3, '2026-04-25 15:45:00', 'Mejora constante', 3, 3);

-- Mayo 2026
INSERT INTO progreso_evaluacion (puntaje, medallas_obtenidas, fecha_evaluacion, reporte_generado, estudiante_id, leccion_id)
VALUES (95, 3, '2026-05-01 10:15:00', 'Dominio total', 3, 4);
INSERT INTO progreso_evaluacion (puntaje, medallas_obtenidas, fecha_evaluacion, reporte_generado, estudiante_id, leccion_id)
VALUES (60, 1, '2026-05-08 14:00:00', 'Lección difícil detectada', 3, 5);