package com.example.managementsystem.Data

object WorkListData {
    data class Work (
        val title: String,
        val id: String,
        val description: String
    )

    val workList: MutableSet<Work> =
        mutableSetOf (
            Work("Preparing Document", "J001", "Prepare document relating to Chinese New Year"),
            Work("Replace Work", "J002", "Help Customer Service to respond to call"),
            Work("Reporting", "J003", "Make an report on investigation of current marketing enviroment")
        )

    fun addWork(title: String, id: String, description: String): Work {
        val newWork = Work(title, id, description)
        workList.add(newWork)
        return newWork
    }
}