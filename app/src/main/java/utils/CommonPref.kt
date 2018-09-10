package utils

import android.app.Activity
import android.content.Context

/**
 * Created by PYF on 2018/9/10
 */

class CommonPref private constructor(activity: Activity): SharedPreferencedUtils(activity.getPreferences(Context.MODE_PRIVATE)) {
    companion object {
        const val TAG = "CommonPref"
        const val OVER_LENGTH_STRING_VALUES = 300
        @Volatile var sInt: CommonPref? = null

        fun getInstance(activity: Activity): CommonPref {
            if (sInt == null) {
                synchronized(CommonPref::class) {
                    if (sInt == null) {
                        sInt = CommonPref(activity)
                    }
                }
            }
            return sInt!!
        }
    }
}