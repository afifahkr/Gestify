import express from "express"
import dotenv from "dotenv"
import db from "./config/database.js"
import router from "./routes/index.js"
//import Users from "./models/UserModel.js" (komen setelah tabel berhasil digenerate)
dotenv.config()
const app = express()

try {
    await db.authenticate();
    console.log('Database Connected')
        //Jika kita tida punya kolom Users di db kita, sequelize akan otomatis mengenerate
        // await Users.sync(); (di komen/hapus setelah tabel digenerate)

} catch (error) {
    console.error(error)
}

app.use(express.json()) //agar bisa menerima data dalam format json
app.use(router) //middleware

app.listen(5000, () => console.log('Server running at port 5000'))