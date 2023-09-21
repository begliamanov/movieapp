package com.example.movieapp.data.localstorage

import android.content.SharedPreferences
import android.util.Log
import com.example.movieapp.domain.repository.StorageInterface
import com.google.gson.Gson
import javax.inject.Inject

class LocalStorage @Inject constructor(
    private var sharedPreferences: SharedPreferences,
) : StorageInterface {
    companion object {
        const val DEFAULT_LONG = 0L
        const val DEFAULT_FLOAT = 0f
        const val TAG = "LocalStorage"
    }

    override fun getString(key: String): String? =
        sharedPreferences.getString(key, null)

    override fun getInt(key: String): Int = sharedPreferences.getInt(key, 0)

    override fun getLong(key: String): Long =
        sharedPreferences.getLong(key, DEFAULT_LONG)

    override fun getFloat(key: String): Float =
        sharedPreferences.getFloat(key, DEFAULT_FLOAT)

    override fun getBool(key: String): Boolean =
        sharedPreferences.getBoolean(key, false)

    override fun getSet(key: String): Set<String>? =
        sharedPreferences.getStringSet(key, null)

    override fun <T> setObject(
        key: String,
        value: T
    ) {
        try {
            val gson = Gson()
            val valueStr = gson.toJson(value)
            val editor = sharedPreferences.edit()
            editor.putString(key, valueStr)
            Log.d(TAG, "setObject $key set $valueStr")
            editor.apply()
        } catch (e: Exception) {
            Log.e(TAG, "Error storing $key with data $value")
        }
    }

    override fun <T> getObject(key: String, classOfT: Class<T>): T? {
        return try {
            val gson = Gson()
            val valueStr = sharedPreferences.getString(key, null)
            Log.d(TAG, "getObject $key Fetched $valueStr")
            gson.fromJson(valueStr, classOfT)
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching $key, exception $e")
            null
        }
    }

    override fun setKey(key: String, value: Any) {
        with(sharedPreferences.edit()) {
            when (value) {
                is Int -> putInt(key, value)
                is String -> putString(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                is Boolean -> putBoolean(key, value)
                is Set<*> -> putStringSet(key, value as Set<String>?)
            }
            apply()
        }
    }

    override fun containsKey(key: String): Boolean =
        sharedPreferences.contains(key)

    override fun removeKey(key: String) {
        with(sharedPreferences.edit()) {
            remove(key)
            commit()
        }
    }
}