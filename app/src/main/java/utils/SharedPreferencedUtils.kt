package utils

import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Log

/**
 * Created by PYF on 2018/9/7
 */

abstract class SharedPreferencedUtils(var mPref: SharedPreferences) {
    @JvmField
    val TAG: String = "SharedPreferencedUtils"
    @JvmField
    val DELEMITER: String = ","

    fun put(key: String, value: String) {
        mPref.edit()
                .putString(key, value)
                .apply()
    }

    fun get(key: String): String {
        return mPref.getString(key, "")
    }

    fun putString(key: String, value: String) {
        put(key, value)
    }

    fun getString(key: String): String {
        return get(key)
    }

    fun putBoolean(key: String, value: Boolean) {
        put(key, value.toString())
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        val rawValue = get(key)
        if (TextUtils.isEmpty(rawValue)) {
            return defaultValue
        }
        var result: Boolean
        try {
            result = rawValue.toBoolean()
        } catch (e: Exception) {
            Log.e(TAG, e.localizedMessage)
            result = defaultValue
        }
        return result
    }

    fun parseInt(value: String, defaultValue: Int): Int {
        var result: Int
        try {
            result = value.toInt()
        } catch (e: Exception) {
            Log.e(TAG, e.localizedMessage)
            result = defaultValue
        }
        return result
    }

    fun putInt(key: String, value: String) {
        put(key, value)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        val rawValue: String = get(key)
        if (TextUtils.isEmpty(rawValue)) {
            return defaultValue
        }
        return parseInt(key, defaultValue)
    }

    fun putLong(key: String, value: Long) {
        put(key, value.toString())
    }

    fun getLong(key: String, defaultValue: Long): Long {
        val rawValue = get(key)
        if (TextUtils.isEmpty(rawValue)) {
            return defaultValue
        }
        try {
            return rawValue.toLong()
        } catch (e: NumberFormatException) {
            Log.e(TAG, e.localizedMessage)
            return defaultValue
        }
    }

    fun getIntArray(key: String, defaultValue: Array<Int>): Array<Int> {

    }

    fun getIntArray(key: String): Array<Int>? {
        val value = get(key)
        val content = value.split(DELEMITER)
        if (TextUtils.isEmpty(value)) {
            return null
        }
        var result = List<Int>()
        for ((index, item) in content.withIndex()) {
            try {
                result[index] = item
            }
        }
    }
}