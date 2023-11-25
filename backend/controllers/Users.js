import Users from "../models/UserModel.js"
import bcrypt from "bcrypt"
import jwt from "jsonwebtoken"

export const getUsers = async(req, res) => {
    try {
        const users = await Users.findAll()
        res.json(users)

    } catch (error) {
        console.log(error)

    }
}

export const Register = async(req, res) => {
    const { name, email, password, confpassword } = req.body
    if (password !== confpassword)
        return res.status(400).json({
            message: "Password dan confirm password tidak cocok"
        })
    const salt = await bcrypt.genSalt()
    const hashPassword = await bcrypt.hash(password, salt)

    try {
        await Users.create({
            name: name,
            email: email,
            password: hashPassword
        })
        res.json({
            message: "Register berhasil"
        })
    } catch (error) {
        console.log(error)
    }


}