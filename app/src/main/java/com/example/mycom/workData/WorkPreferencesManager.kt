package com.example.mycom.workData

import android.content.Context
import android.content.SharedPreferences

class WorkPreferencesManager(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("work_pref", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_COUNT = "work_count"
    }

    fun getcount():Int {
        return preferences.getInt(KEY_COUNT,0)
    }

    fun setCount(count: Int) {
        preferences.edit().putInt(KEY_COUNT, count).apply()
    }
}