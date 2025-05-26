-- Insertar nuevo empleado
INSERT INTO Empleado (
  chr_emplcodigo,
  vch_emplpaterno,
  vch_emplmaterno,
  vch_emplnombre,
  vch_emplciudad,
  vch_empldireccion
) VALUES (
  '0015',
  'Monster',
  'Inc',
  'James Sullivan',
  'Monstropolis',
  'Avenida del Terror 1313'
);

-- Insertar nuevo usuario (clave en texto plano)
INSERT INTO Usuario (
  chr_emplcodigo,
  vch_emplusuario,
  vch_emplclave,
  vch_emplestado
) VALUES (
  '0015',
  'MONSTER',
  'MONSTER9',
  'ACTIVO'
);
