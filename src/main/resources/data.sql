--- 1. TABLAS INDEPENDIENTES
INSERT INTO roles (id, name)
SELECT 1, 'ROLE_PROFESOR' WHERE NOT EXISTS (SELECT 1 FROM roles WHERE id = 1);
INSERT INTO roles (id, name)
SELECT 2, 'ROLE_PADRE' WHERE NOT EXISTS (SELECT 1 FROM roles WHERE id = 2);
INSERT INTO roles (id, name)
SELECT 3, 'ROLE_ALUMNO' WHERE NOT EXISTS (SELECT 1 FROM roles WHERE id = 3);


INSERT INTO plan_suscripcion (nombre, precio)
SELECT 'Plan Gratuito', 0.0 WHERE NOT EXISTS (SELECT 1 FROM plan_suscripcion WHERE nombre = 'Plan Gratuito');
INSERT INTO plan_suscripcion (nombre, precio)
SELECT 'Plan Familiar Mensual', 45.0 WHERE NOT EXISTS (SELECT 1 FROM plan_suscripcion WHERE nombre = 'Plan Familiar Mensual');
INSERT INTO plan_suscripcion (nombre, precio)
SELECT 'Plan Institucional Anual', 450.0 WHERE NOT EXISTS (SELECT 1 FROM plan_suscripcion WHERE nombre = 'Plan Institucional Anual');

INSERT INTO categoria (nombre, icono_url)
SELECT 'Matemáticas', 'https://cdn-icons-png.flaticon.com/512/2432/2432572.png' WHERE NOT EXISTS (SELECT 1 FROM categoria WHERE nombre = 'Matemáticas');
INSERT INTO categoria (nombre, icono_url)
SELECT 'Comunicación', 'https://cdn-icons-png.flaticon.com/512/3261/3261301.png' WHERE NOT EXISTS (SELECT 1 FROM categoria WHERE nombre = 'Comunicación');
INSERT INTO categoria (nombre, icono_url)
SELECT 'Habilidades Sociales', 'https://cdn-icons-png.flaticon.com/512/1189/1189132.png' WHERE NOT EXISTS (SELECT 1 FROM categoria WHERE nombre = 'Habilidades Sociales');

--- 2. USUARIOS Y TEMAS
-- Nota: Las contraseñas están encriptadas con BCrypt. Todas son: password123
INSERT INTO usuario (nombre_completo, username, contrasena, correo_electronico, metodo_registro, plan_id, rol_id)
SELECT 'Josué Adrián', 'josue_admin', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOn2', 'josue.adrian@upc.edu.pe', 'Email', 3, 1
WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE username = 'josue_admin');

INSERT INTO usuario (nombre_completo, username, contrasena, correo_electronico, metodo_registro, plan_id, rol_id, creado_por_id)
SELECT 'Roberto Gómez', 'roberto_tutor', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOn2', 'roberto.g@gmail.com', 'Google', 2, 2, (SELECT id FROM usuario WHERE username='josue_admin')
WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE username = 'roberto_tutor');

INSERT INTO usuario (nombre_completo, username, contrasena, correo_electronico, metodo_registro, plan_id, rol_id, creado_por_id)
SELECT 'Lucía Méndez', 'lucia_estudiante', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOn2', 'lucia.m@outlook.com', 'Email', 1, 3, (SELECT id FROM usuario WHERE username='roberto_tutor')
WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE username = 'lucia_estudiante');

-- Tabla intermedia ManyToMany (usuario_roles)
INSERT INTO usuario_roles (usuario_id, roles_id)
SELECT 1, 1 WHERE NOT EXISTS (SELECT 1 FROM usuario_roles WHERE usuario_id = 1 AND roles_id = 1);
INSERT INTO usuario_roles (usuario_id, roles_id)
SELECT 2, 2 WHERE NOT EXISTS (SELECT 1 FROM usuario_roles WHERE usuario_id = 2 AND roles_id = 2);
INSERT INTO usuario_roles (usuario_id, roles_id)
SELECT 3, 3 WHERE NOT EXISTS (SELECT 1 FROM usuario_roles WHERE usuario_id = 3 AND roles_id = 3);

INSERT INTO tema (nombre, categoria_id)
SELECT 'Sumas con Imágenes', 1 WHERE NOT EXISTS (SELECT 1 FROM tema WHERE nombre = 'Sumas con Imágenes');
INSERT INTO tema (nombre, categoria_id)
SELECT 'Reconocimiento de Emociones', 3 WHERE NOT EXISTS (SELECT 1 FROM tema WHERE nombre = 'Reconocimiento de Emociones');
INSERT INTO tema (nombre, categoria_id)
SELECT 'Vocales y Consonantes', 2 WHERE NOT EXISTS (SELECT 1 FROM tema WHERE nombre = 'Vocales y Consonantes');

--- 3. CONFIGURACIONES Y PERFILES (OneToOne)
INSERT INTO configuracion (velocidad_flashcards, tema_visual, usuario_id)
SELECT 3, 'Claro', 1 WHERE NOT EXISTS (SELECT 1 FROM configuracion WHERE usuario_id = 1);
INSERT INTO configuracion (velocidad_flashcards, tema_visual, usuario_id)
SELECT 5, 'Oscuro', 2 WHERE NOT EXISTS (SELECT 1 FROM configuracion WHERE usuario_id = 2);
INSERT INTO configuracion (velocidad_flashcards, tema_visual, usuario_id)
SELECT 2, 'Alto Contraste', 3 WHERE NOT EXISTS (SELECT 1 FROM configuracion WHERE usuario_id = 3);

INSERT INTO perfil_aprendizaje (nivel_espectro, necesidades_especificas, estudiante_id)
SELECT 'Grave', 'Requiere pictogramas grandes', 3 WHERE NOT EXISTS (SELECT 1 FROM perfil_aprendizaje WHERE estudiante_id = 3);
INSERT INTO perfil_aprendizaje (nivel_espectro, necesidades_especificas, estudiante_id)
SELECT 'Leve', 'Sensibilidad auditiva a sonidos agudos', 2 WHERE NOT EXISTS (SELECT 1 FROM perfil_aprendizaje WHERE estudiante_id = 2);
INSERT INTO perfil_aprendizaje (nivel_espectro, necesidades_especificas, estudiante_id)
SELECT 'Moderado', 'Refuerzo positivo visual inmediato', 1 WHERE NOT EXISTS (SELECT 1 FROM perfil_aprendizaje WHERE estudiante_id = 1);

--- 4. RELACIONES TUTOR-ESTUDIANTE
INSERT INTO relacion_tutor_estudiante (tutor_id, estudiante_id)
SELECT 1, 3 WHERE NOT EXISTS (SELECT 1 FROM relacion_tutor_estudiante WHERE tutor_id = 1 AND estudiante_id = 3);
INSERT INTO relacion_tutor_estudiante (tutor_id, estudiante_id)
SELECT 2, 3 WHERE NOT EXISTS (SELECT 1 FROM relacion_tutor_estudiante WHERE tutor_id = 2 AND estudiante_id = 3);
INSERT INTO relacion_tutor_estudiante (tutor_id, estudiante_id)
SELECT 1, 2 WHERE NOT EXISTS (SELECT 1 FROM relacion_tutor_estudiante WHERE tutor_id = 1 AND estudiante_id = 2);

--- 5. LECCIONES CUSTOM
INSERT INTO leccion_custom (titulo, dificultad, creador_id, estudiante_id, tema_id)
SELECT 'Aprendiendo a Sumar del 1 al 10', 'Fácil', 1, 3, 1 WHERE NOT EXISTS (SELECT 1 FROM leccion_custom WHERE titulo = 'Aprendiendo a Sumar del 1 al 10');
INSERT INTO leccion_custom (titulo, dificultad, creador_id, estudiante_id, tema_id)
SELECT '¿Cómo me siento hoy?', 'Media', 2, 3, 2 WHERE NOT EXISTS (SELECT 1 FROM leccion_custom WHERE titulo = '¿Cómo me siento hoy?');
INSERT INTO leccion_custom (titulo, dificultad, creador_id, estudiante_id, tema_id)
SELECT 'Las vocales mágicas', 'Fácil', 1, 2, 3 WHERE NOT EXISTS (SELECT 1 FROM leccion_custom WHERE titulo = 'Las vocales mágicas');

--- 6. FLASHCARDS Y PROGRESO
INSERT INTO flashcard (pregunta_texto, imagen_url, color_fondo, color_texto, leccion_id)
SELECT '¿Cuántas manzanas ves?', 'https://images.unsplash.com/photo-1568702846914-96b305d2aaeb?auto=format&fit=crop&w=400&q=80', '#FFEBEE', '#B71C1C', 1 WHERE NOT EXISTS (SELECT 1 FROM flashcard WHERE pregunta_texto = '¿Cuántas manzanas ves?');
INSERT INTO flashcard (pregunta_texto, imagen_url, color_fondo, color_texto, leccion_id)
SELECT 'Identifica la cara de alegría', 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?auto=format&fit=crop&w=400&q=80', '#E3F2FD', '#0D47A1', 2 WHERE NOT EXISTS (SELECT 1 FROM flashcard WHERE pregunta_texto = 'Identifica la cara de alegría');
INSERT INTO flashcard (pregunta_texto, imagen_url, color_fondo, color_texto, leccion_id)
SELECT '¿Qué vocal empieza con Avión?', 'https://images.unsplash.com/photo-1540962351504-03099e0a754b?auto=format&fit=crop&w=400&q=80', '#F1F8E9', '#33691E', 3 WHERE NOT EXISTS (SELECT 1 FROM flashcard WHERE pregunta_texto = '¿Qué vocal empieza con Avión?');
INSERT INTO flashcard (pregunta_texto, imagen_url, color_fondo, color_texto, leccion_id)
SELECT '¿Cuál es el color azul?', 'https://images.unsplash.com/photo-1579783902614-a3fb3927b6a5?auto=format&fit=crop&w=400&q=80', '#E3F2FD', '#0D47A1', 1 WHERE NOT EXISTS (SELECT 1 FROM flashcard WHERE pregunta_texto = '¿Cuál es el color azul?');
INSERT INTO flashcard (pregunta_texto, imagen_url, color_fondo, color_texto, leccion_id)
SELECT '¿Cuántos dedos hay en una mano?', 'https://images.unsplash.com/photo-1516641396056-0ce60a35d714?auto=format&fit=crop&w=400&q=80', '#FFF3E0', '#E65100', 1 WHERE NOT EXISTS (SELECT 1 FROM flashcard WHERE pregunta_texto = '¿Cuántos dedos hay en una mano?');
INSERT INTO flashcard (pregunta_texto, imagen_url, color_fondo, color_texto, leccion_id)
SELECT '¿Qué animal ladra?', 'https://images.unsplash.com/photo-1543466835-00a7907e9de1?auto=format&fit=crop&w=400&q=80', '#EFEBE9', '#4E342E', 2 WHERE NOT EXISTS (SELECT 1 FROM flashcard WHERE pregunta_texto = '¿Qué animal ladra?');
INSERT INTO flashcard (pregunta_texto, imagen_url, color_fondo, color_texto, leccion_id)
SELECT '¿Qué letra es la primera del abecedario?', 'https://images.unsplash.com/photo-1509869175650-a1d979e25595?auto=format&fit=crop&w=400&q=80', '#EDE7F6', '#4A148C', 3 WHERE NOT EXISTS (SELECT 1 FROM flashcard WHERE pregunta_texto = '¿Qué letra es la primera del abecedario?');

INSERT INTO progreso_evaluacion (puntaje, medallas_obtenidas, fecha_evaluacion, reporte_generado, estudiante_id, leccion_id)
SELECT 80, 2, '2026-05-10 10:00:00', 'Buen progreso inicial', 3, 1 WHERE NOT EXISTS (SELECT 1 FROM progreso_evaluacion WHERE estudiante_id = 3 AND leccion_id = 1 AND fecha_evaluacion = '2026-05-10 10:00:00');
INSERT INTO progreso_evaluacion (puntaje, medallas_obtenidas, fecha_evaluacion, reporte_generado, estudiante_id, leccion_id)
SELECT 100, 3, '2026-05-11 15:30:00', 'Completado sin errores', 3, 2 WHERE NOT EXISTS (SELECT 1 FROM progreso_evaluacion WHERE estudiante_id = 3 AND leccion_id = 2 AND fecha_evaluacion = '2026-05-11 15:30:00');
INSERT INTO progreso_evaluacion (puntaje, medallas_obtenidas, fecha_evaluacion, reporte_generado, estudiante_id, leccion_id)
SELECT 60, 1, '2026-05-11 18:00:00', 'Requiere repasar vocales O y U', 2, 3 WHERE NOT EXISTS (SELECT 1 FROM progreso_evaluacion WHERE estudiante_id = 2 AND leccion_id = 3 AND fecha_evaluacion = '2026-05-11 18:00:00');

--- 7. OPCIONES, RESEÑAS Y ELEMENTOS GUARDADOS
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT '1', true, '¡Correcto!', 1 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = '1' AND flashcard_id = 1);
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT '2', false, 'Faltan algunas', 1 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = '2' AND flashcard_id = 1);
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT '3', false, 'Son demasiadas', 1 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = '3' AND flashcard_id = 1);

INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT '😢', false, 'Esa es tristeza', 2 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = '😢' AND flashcard_id = 2);
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT '😡', false, 'Ese es enojo', 2 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = '😡' AND flashcard_id = 2);
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT '😊', true, '¡Correcto! Es alegría', 2 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = '😊' AND flashcard_id = 2);

INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT 'E', false, 'E de Elefante', 3 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = 'E' AND flashcard_id = 3);
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT 'I', false, 'I de Iglesia', 3 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = 'I' AND flashcard_id = 3);
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT 'A', true, '¡Excelente!', 3 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = 'A' AND flashcard_id = 3);

-- Opciones para Flashcard 4 (¿Cuál es el color azul?)
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT 'Rojo', false, 'No, es rojo', 4 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = 'Rojo' AND flashcard_id = 4);
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT 'Azul', true, '¡Correcto! Es azul', 4 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = 'Azul' AND flashcard_id = 4);
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT 'Verde', false, 'No, es verde', 4 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = 'Verde' AND flashcard_id = 4);

-- Opciones para Flashcard 5 (¿Cuántos dedos hay en una mano?)
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT '5', true, '¡Excelente! Son 5 dedos', 5 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = '5' AND flashcard_id = 5);
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT '10', false, 'No, 10 es en dos manos', 5 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = '10' AND flashcard_id = 5);
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT '3', false, 'Faltan dedos', 5 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = '3' AND flashcard_id = 5);

-- Opciones para Flashcard 6 (¿Qué animal ladra?)
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT 'Gato', false, 'El gato hace miau', 6 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = 'Gato' AND flashcard_id = 6);
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT 'Perro', true, '¡Correcto! El perro ladra (guau guau)', 6 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = 'Perro' AND flashcard_id = 6);
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT 'Loro', false, 'El loro repite palabras', 6 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = 'Loro' AND flashcard_id = 6);

-- Opciones para Flashcard 7 (¿Qué letra es la primera del abecedario?)
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT 'A', true, '¡Correcto! Es la letra A', 7 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = 'A' AND flashcard_id = 7);
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT 'Z', false, 'No, Z es la última', 7 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = 'Z' AND flashcard_id = 7);
INSERT INTO opcion_respuesta (texto_opcion, es_correcta, feedback_respuesta, flashcard_id)
SELECT 'M', false, 'No, es la letra A', 7 WHERE NOT EXISTS (SELECT 1 FROM opcion_respuesta WHERE texto_opcion = 'M' AND flashcard_id = 7);

INSERT INTO resena_usuario (calificacion, comentario, fecha_publicacion, usuario_id)
SELECT 5, 'Increíble herramienta para mis clases', '2026-05-10 12:00:00', 2 WHERE NOT EXISTS (SELECT 1 FROM resena_usuario WHERE usuario_id = 2 AND fecha_publicacion = '2026-05-10 12:00:00');
INSERT INTO resena_usuario (calificacion, comentario, fecha_publicacion, usuario_id)
SELECT 4, 'Me ayuda mucho con mi hijo', '2026-05-11 09:00:00', 3 WHERE NOT EXISTS (SELECT 1 FROM resena_usuario WHERE usuario_id = 3 AND fecha_publicacion = '2026-05-11 09:00:00');
INSERT INTO resena_usuario (calificacion, comentario, fecha_publicacion, usuario_id)
SELECT 5, 'El sistema de backend es muy fluido', '2026-05-11 19:00:00', 1 WHERE NOT EXISTS (SELECT 1 FROM resena_usuario WHERE usuario_id = 1 AND fecha_publicacion = '2026-05-11 19:00:00');

INSERT INTO elemento_guardado (tipo_elemento, elemento_id, usuario_id)
SELECT 'LECCION', 1, 3 WHERE NOT EXISTS (SELECT 1 FROM elemento_guardado WHERE tipo_elemento = 'LECCION' AND elemento_id = 1 AND usuario_id = 3);
INSERT INTO elemento_guardado (tipo_elemento, elemento_id, usuario_id)
SELECT 'FLASHCARD', 2, 3 WHERE NOT EXISTS (SELECT 1 FROM elemento_guardado WHERE tipo_elemento = 'FLASHCARD' AND elemento_id = 2 AND usuario_id = 3);
INSERT INTO elemento_guardado (tipo_elemento, elemento_id, usuario_id)
SELECT 'LECCION', 3, 2 WHERE NOT EXISTS (SELECT 1 FROM elemento_guardado WHERE tipo_elemento = 'LECCION' AND elemento_id = 3 AND usuario_id = 2);

--- 8. EXTRA ALUMNOS MOCK
INSERT INTO usuario (nombre_completo, username, contrasena, correo_electronico, metodo_registro, plan_id, rol_id, creado_por_id)
SELECT 'María Gonzalez', 'maria_estudiante', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOn2', 'maria.g@gmail.com', 'Email', 1, 3, (SELECT id FROM usuario WHERE username='roberto_tutor')
WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE username = 'maria_estudiante');
INSERT INTO usuario_roles (usuario_id, roles_id)
SELECT (SELECT id FROM usuario WHERE username='maria_estudiante'), 3 
WHERE NOT EXISTS (SELECT 1 FROM usuario_roles WHERE usuario_id = (SELECT id FROM usuario WHERE username='maria_estudiante') AND roles_id = 3);

INSERT INTO usuario (nombre_completo, username, contrasena, correo_electronico, metodo_registro, plan_id, rol_id, creado_por_id)
SELECT 'Pedro Lopez', 'pedro_estudiante', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.TVuHOn2', 'pedro.l@gmail.com', 'Email', 1, 3, (SELECT id FROM usuario WHERE username='roberto_tutor')
WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE username = 'pedro_estudiante');
INSERT INTO usuario_roles (usuario_id, roles_id)
SELECT (SELECT id FROM usuario WHERE username='pedro_estudiante'), 3 
WHERE NOT EXISTS (SELECT 1 FROM usuario_roles WHERE usuario_id = (SELECT id FROM usuario WHERE username='pedro_estudiante') AND roles_id = 3);
-- Backfill missing roles for users created manually
INSERT INTO usuario_roles (usuario_id, roles_id)
SELECT id, rol_id FROM usuario
WHERE rol_id IS NOT NULL
  AND NOT EXISTS (
      SELECT 1 FROM usuario_roles ur
      WHERE ur.usuario_id = usuario.id AND ur.roles_id = usuario.rol_id
  );
