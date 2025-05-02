package com.jmarser.mydelivery.data.local.shared

import android.content.SharedPreferences
import com.jmarser.mydelivery.utilities.MyLog

/**
 * Project: My Delivery
 * File: AppPreferences
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 02/05/2025
 */

class AppPreferences(
    val sharedPreferences: SharedPreferences
) {

    inline fun <reified T> save(key: String, value: T) {
        if (value == null) {
            MyLog.d("APP_PREFERENCES", "ERROR Se ha encontrado un valor NULL en la variable: core")
            return // Retorna tempranamente para evitar intentar guardar un valor null.
        }

        with(sharedPreferences.edit()) {
            value.let {
                when (T::class) {
                    String::class -> putString(key, value as String)
                    Boolean::class -> putBoolean(key, value as Boolean)
                    Float::class -> putFloat(key, value as Float)
                    Int::class -> putInt(key, value as Int)
                    Long::class -> putLong(key, value as Long)
                    Double::class -> putLong(key, (value as Double).toRawBits())
                    else -> error("Tipo desconocido")
                }.apply()
            }
        }
    }

    inline fun <reified T> get(key: String, defaultValue: T): T {
        return when (T::class) {
            String::class -> sharedPreferences.getString(key, defaultValue as? String ?: "") as T
            Boolean::class -> sharedPreferences.getBoolean(
                key,
                defaultValue as? Boolean ?: false
            ) as T

            Float::class -> sharedPreferences.getFloat(key, defaultValue as? Float ?: 0f) as T
            Int::class -> sharedPreferences.getInt(key, defaultValue as? Int ?: 0) as T
            Long::class -> sharedPreferences.getLong(key, defaultValue as? Long ?: 0L) as T
            Double::class -> Double.fromBits(
                sharedPreferences.getLong(
                    key,
                    (defaultValue as? Double ?: 0.0).toRawBits()
                )
            ) as T // Recupera el Double
            else -> error("This type is not supported")
        }
    }

    fun clearAll() {
        try {
            with(sharedPreferences.edit()) {
                clear()
                apply()
            }
        } catch (e: SecurityException) {
            MyLog.d("CLEAR_PREFERENCES", "Error: ${e.message} / ${e.localizedMessage}")
        }
    }
}