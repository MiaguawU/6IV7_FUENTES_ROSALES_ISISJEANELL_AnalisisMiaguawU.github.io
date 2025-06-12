const express = require('express')
const router = express.Router();
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');
const db = require('./bd');

// Obtener un solo usuario por ID
router.get('/:id', (req, res) => {
    const userId = req.params.id;
    db.query('SELECT * FROM usuarios WHERE id = ?', [userId], (err, result) => {
        if (err) {
            console.log('Error al obtener el usuario:', err);
            return res.status(500).send('Error al consultar usuario');
        }
        if (result.length === 0) {
            return res.status(404).send('Usuario no encontrado');
        }
        res.json(result[0]);
    });
});

//ruta para el registro
router.post('/register', async(req, res) => {
  const { email, password, nombre, segundo_nombre, apellido_p, apellido_m } = req.body;
  try {
    const hashed = await bcrypt.hash(password, 10);
    const sql = `
      INSERT INTO usuarios (email, password, nombre, segundo_nombre, apellido_p, apellido_m)
      VALUES (?, ?, ?, ?, ?, ?)
    `;
    db.query(sql, [email, hashed, nombre, segundo_nombre, apellido_p, apellido_m], (err, result) => {
      if (err) {
        console.log('Error al registrar al usuario', err);
        return res.status(500).send('Error al registrar');
      }
      console.log("Usuario registrado con el ID", result.insertId);
      res.status(200).send('Usuario Registrado');
    });
  } catch (error) {
    console.log('Error en el servidor al momento de registrar {register}: ', error);
    res.status(500).send('Error interno del servidor');
  }
});


//ruta de login
router.post('/login', (req, res) => {
    const { email, password} = req.body;
    db.query('SELECT * FROM usuarios WHERE email = ? ', [email], async(err, result) =>{
        if(err){
            console.log('Error en la consulta del login: ', err);
            return res.status(500).send('Error en el servidor');
        }
        //cuando no se encontro el email
        if(result.length === 0){
            console.log('Usuario no encontrado : ', err);
            return res.status(500).send('Credenciales invalidas');
        }

        //si existe
        const user = result[0];
        //validar el pass haseado
        const valid = await bcrypt.compare(password, user.password);
        if(!valid){
            console.warn("Contraseña incorrecta para usuario, ", email);
            return res.status(401).send('Contraseña incorrecta user no autorizado');
        }
        const token = jwt.sign(
            { id:user.id, email:user.email},
            //proceso del hash
            process.env.JWT_SECRET,
            { expiresIn:'1h' }
        );
        console.log('Token Generado para el usuario: ', user.email);
        res.json({ token, id: user.id });
    });
});
//cambio
module.exports = router

