import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("employee_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_COUNT = "employee_count"
    }

    fun getCount(): Int {
        return preferences.getInt(KEY_COUNT, 0)
    }

    fun setCount(count: Int) {
        preferences.edit().putInt(KEY_COUNT, count).apply()
    }
}
