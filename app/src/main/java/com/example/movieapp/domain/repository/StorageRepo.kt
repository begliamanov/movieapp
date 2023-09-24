package com.example.movieapp.domain.repository

interface StorageRepo {
    fun getString(key: String): String?
    fun getInt(key: String): Int?
    fun getLong(key: String): Long
    fun getFloat(key: String): Float
    fun getBool(key: String): Boolean
    fun getSet(key: String): Set<Any>?
    fun setKey(key: String, value: Any)
    fun containsKey(key: String): Boolean

    fun removeKey(key: String)

    fun <T> setObject(key: String, value: T)
    fun <T> getObject(key: String, classOfT: Class<T>): T?

}