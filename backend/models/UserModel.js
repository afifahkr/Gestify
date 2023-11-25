import { Sequelize } from "sequelize"
import db from "../config/database.js"

/*panggil datatype dari sequelize
Dalam Sequelize, DataTypes adalah objek yang menyediakan tipe data 
untuk mendefinisikan kolom-kolom pada model Sequelize. 
Tipe data ini digunakan untuk memberi tahu Sequelize tentang jenis data yang akan disimpan di dalam kolom database.
*/
const { DataTypes } = Sequelize

//define 'nama tabel', {fieldnya}, {opsi}
const Users = db.define('users', {
    name: {
        type: DataTypes.STRING
    },
    email: {
        type: DataTypes.STRING
    },
    password: {
        type: DataTypes.STRING
    },
    refresh_token: {
        type: DataTypes.TEXT
    }
}, {
    freezeTableName: true
})

export default Users