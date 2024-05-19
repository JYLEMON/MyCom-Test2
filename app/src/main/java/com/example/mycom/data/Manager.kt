package com.example.mycom.data

object ManagerList {
    data class Manager(
        val id: String,
        val name: String,
        val email: String,
        val password: String,
        val salary: String,
    )

    val manager = Manager(
        id = "M001",
        name = "Tan Jian Yang",
        email = "tjy@gmail.com",
        password = "000000",
        salary = "3000"
    )
}