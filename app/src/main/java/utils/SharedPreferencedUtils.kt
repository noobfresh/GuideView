package utils

import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Log
import android.view.View
import java.util.ArrayList

/**
 * Created by PYF on 2018/9/7
 * 这个类是否可以用泛型优化呢
 * 在还没做创建的情况。我还是通过activity来获取sharedPreferences
 */

open class SharedPreferencedUtils(var mPref: SharedPreferences) {
    @JvmField
    val TAG: String = "SharedPreferencedUtils"
    @JvmField
    val DELEMITER: String = ","

    val listener = View.OnClickListener {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.i(TAGTWO, "AAAAAAAAAAAAAAAAAAAAAAA")
    }

    companion object Tester {
        const val TAGTWO: String = "TEST"
    }

    fun put(key: String, value: String) {
        mPref.edit()
                .putString(key, value)
                .apply()
    }

    fun get(key: String): String {
        return mPref.getString(key, "")
    }

    fun remove(key: String) {
        mPref.edit().remove(key).apply()
    }

    fun clear() {
        mPref.edit().clear().apply()
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

    fun getIntArray(key: String, defaultValue: Array<Int>): Array<Int>? {
        val result = getIntArray(key)
        result?.let {
            if (result.isEmpty()) {
                return null
            }
            if (result.size < defaultValue.size) {
                return defaultValue
            }
        }
        return result
    }

    //不确定这个toString 会不会结果奇怪。应该会出事，毕竟是用分隔符切割的
    fun putIntArray(key: String, value: Array<Int>) {
        putIntList(key, value.asList())
    }

    fun getIntArray(key: String): Array<Int>? {
        val value = get(key)
        val content = value.split(DELEMITER)
        if (TextUtils.isEmpty(value) || content.isEmpty()) {
            return null
        }

        val result = ArrayList<Int>()
        for ((index, item) in content.withIndex()) {
            try {
                result[index] = item.toInt()
            } catch (e: Exception) {
                Log.e(TAG, e.localizedMessage)
                continue
            }
        }
        val array: Array<Int> = arrayOf(result.size)
        return result.toArray(array)
    }

    fun putIntList(key: String, values: List<Int>) {
        values.let {
            if (values.isEmpty()) {
                return
            }
        }

        val result = "".buildWithDelimiter(values, DELEMITER)
        put(key, result)
    }

    fun <E> String.buildWithDelimiter(values: List<E>, Delimiter: String): String {
        val buffer = StringBuffer()
        values.forEach {
            buffer.append(it.toString())
            buffer.append(Delimiter)
        }
        return buffer.toString()
    }

    fun getAll(): Map<String, *> {
        return mPref.all
    }

    fun contain(key: String):Boolean {
        key.let {
            if (key.isEmpty()) {
                return false
            }
        }
        return mPref.contains(key)
    }
}