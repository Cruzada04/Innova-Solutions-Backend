--- 1. TABLAS INDEPENDIENTES
INSERT INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_TUTOR'), ('ROLE_ESTUDIANTE');

INSERT INTO plan_suscripcion (nombre, precio) VALUES 
('Plan Gratuito', 0.0), 
('Plan Familiar Mensual', 45.00), 
('Plan Institucional Anual', 450.00);

INSERT INTO categoria (nombre, icono_url) VALUES 
('Matemáticas', 'https://cdn-icons-png.flaticon.com/512/2432/2432572.png'), 
('Comunicación', 'https://cdn-icons-png.flaticon.com/512/3261/3261301.png'), 
('Habilidades Sociales', 'https://cdn-icons-png.flaticon.com/512/1189/1189132.png');

--- 2. USUARIOS Y TEMAS
-- Nota: Las contraseñas están en texto plano. Si usas BCrypt, cámbialas por hashes.
INSERT INTO usuario (nombre_completo, username, contrasena, correo_electronico, metodo_registro, plan_id) VALUES 
('Josué Adrián', 'josue_admin', 'admin123', 'josue.adrian@upc.edu.pe', 'Email', 3),
('Roberto Gómez', 'roberto_tutor', 'tutor123', 'roberto.g@gmail.com', 'Google', 2),
('Lucía Méndez', 'lucia_estudiante', 'user123', 'lucia.m@outlook.com', 'Email', 1);

-- Tabla intermedia ManyToMany (usuario_roles)
INSERT INTO usuario_roles (usuario_id, roles_id) VALUES (1, 1), (2, 2), (3, 3);

INSERT INTO tema (nombre, categoria_id) VALUES 
('Sumas con Imágenes', 1), 
('Reconocimiento de Emociones', 3), 
('Vocales y Consonantes', 2);

--- 3. CONFIGURACIONES Y PERFILES (OneToOne)
INSERT INTO configuracion (velocidad_flashcards, tema_visual, usuario_id) VALUES 
(3, 'Claro', 1), (5, 'Oscuro', 2), (2, 'Alto Contraste', 3);

INSERT INTO perfil_aprendizaje (nivel_espectro, necesidades_especificas, estudiante_id) VALUES 
('Grave', 'Requiere pictogramas grandes', 3),
('Leve', 'Sensibilidad auditiva a sonidos agudos', 2),
('Moderado', 'Refuerzo positivo visual inmediato', 1);

--- 4. RELACIONES TUTOR-ESTUDIANTE
INSERT INTO relacion_tutor_estudiante (tutor_id, estudiante_id) VALUES 
(1, 3), (2, 3), (1, 2);

--- 5. LECCIONES CUSTOM
INSERT INTO leccion_custom (titulo, dificultad, creador_id, estudiante_id, tema_id) VALUES 
('Aprendiendo a Sumar del 1 al 10', 'Fácil', 1, 3, 1),
('¿Cómo me siento hoy?', 'Media', 2, 3, 2),
('Las vocales mágicas', 'Fácil', 1, 2, 3);

--- 6. FLASHCARDS Y PROGRESO
INSERT INTO flashcard (pregunta_texto, imagen_url, color_fondo, color_texto, leccion_id) VALUES 
('¿Cuántas manzanas ves?', 'img_manzanas.png', '#FFEBEE', '#B71C1C', 1),
('Identifica la cara de alegría', 'img_alegria.png', '#E3F2FD', '#0D47A1', 2),
('¿Qué vocal empieza con Avión?', 'img_avion.png', '#F1F8E9', '#33691E', 3);

INSERT INTO progreso_evaluacion (puntaje, medallas_obtenidas, fecha_evaluacion, reporte_generado, estudiante_id, leccion_id) VALUES 
(80, 2, '2026-05-10 10:00:00', 'Buen progreso inicial', 3, 1),
(100, 3, '2026-05-11 15:30:00', 'Completado sin errores', 3, 2),
(60, 1, '2026-05-11 18:00:00', 'Requiere repasar vocales O y U', 2, 3);

--- 7. OPCIONES, RESEÑAS Y ELEMENTOS GUARDADOS
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id) VALUES 
('3 manzanas', true, '¡Correcto!', 1), 
('Alegría', true, '¡Muy bien!', 2), 
('Letra A', true, '¡Excelente!', 3);

INSERT INTO resena_usuario (calificacion, comentario, fecha_publicacion, usuario_id) VALUES 
(5, 'Increíble herramienta para mis clases', '2026-05-10 12:00:00', 2),
(4, 'Me ayuda mucho con mi hijo', '2026-05-11 09:00:00', 3),
(5, 'El sistema de backend es muy fluido', '2026-05-11 19:00:00', 1);

INSERT INTO elemento_guardado (tipo_elemento, elemento_id, usuario_id) VALUES 
('LECCION', 1, 3), ('FLASHCARD', 2, 3), ('LECCION', 3, 2);