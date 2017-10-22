INSERT INTO tipoempleado (descripcion) VALUES ('Master');
INSERT INTO usuario (nombreusuario, password) VALUES ('master', crypt('master', gen_salt('md5')));
INSERT INTO empleado (idusuario, nombre, apellidos, idtipoempleado, numdoc) VALUES (1, 'Master', '', 1, '314159');