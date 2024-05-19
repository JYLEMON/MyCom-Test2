package com.example.mycom

import android.content.Context
import android.content.SharedPreferences

object OneTimeRunUtil {
    private const val PREFS_NAME = "OneTimeRunPrefs"
    private const val KEY_HAS_RUN = "hasRun"

    fun hasRun(context: Context): Boolean {
        val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return preferences.getBoolean(KEY_HAS_RUN, false)
    }

    fun setHasRun(context: Context) {
        val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        preferences.edit().putBoolean(KEY_HAS_RUN, true).apply()
    }
}