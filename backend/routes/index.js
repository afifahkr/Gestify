import express from "express"
//menangani permintaan (request) untuk mendapatkan data pengguna.
import { getUsers } from "../controllers/Users.js";
import { Register } from "../controllers/Users.js";
import { Login } from "../controllers/Users.js";
import { Logout } from "../controllers/Users.js";
import { verifyToken } from "../middleware/VerifyToken.js";
import { refreshToken } from "../controllers/RefreshToken.js";

//Membuat objek router dari Express.
const router = express.Router();

//Mendefinisikan rute HTTP GET pada path /users. Ketika permintaan GET diterima pada path ini, fungsi getUsers dari controller akan dijalankan.
router.get('/users', verifyToken, getUsers)
router.post('/users', Register)
router.post('/login', Login)
router.get('/token', refreshToken)
router.delete('/logout', Logout)


//Mengeskpor objek router agar dapat digunakan di file lain dalam proyek. 
export default router