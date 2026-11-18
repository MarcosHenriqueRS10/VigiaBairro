package br.com.fiap.vigiabairro.data.repository

import android.content.Context
import br.com.fiap.vigiabairro.data.model.Occurrence
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/** Persiste as ocorrências localmente usando SharedPreferences. */
class OccurrenceRepository(context: Context) {

    private val preferences = context.getSharedPreferences(
        PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )
    private val gson = Gson()

    fun getAll(): List<Occurrence> {
        val json = preferences.getString(KEY_OCCURRENCES, null)
            ?: return emptyList()
        return runCatching {
            val type = object : TypeToken<List<Occurrence>>() {}.type
            gson.fromJson<List<Occurrence>>(json, type) ?: emptyList()
        }.getOrDefault(emptyList())
    }

    fun add(
        title: String,
        description: String,
        location: String,
        type: String,
        date: String
    ): Occurrence {
        val current = getAll()
        val occurrence = Occurrence(
            id = (current.maxOfOrNull { it.id } ?: 0) + 1,
            title = title.trim(),
            description = description.trim(),
            location = location.trim(),
            type = type,
            date = date
        )
        save(current + occurrence)
        return occurrence
    }

    private fun save(occurrences: List<Occurrence>) {
        preferences.edit()
            .putString(KEY_OCCURRENCES, gson.toJson(occurrences))
            .apply()
    }

    private companion object {
        const val PREFERENCES_NAME = "vigia_bairro_data"
        const val KEY_OCCURRENCES = "occurrences"
    }
}
