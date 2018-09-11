package com.pyf.guideview.guide

import android.content.SharedPreferences
import utils.CommonPref

/**
 * Created by PYF on 2018/9/1
 */

class DefaultOnce (var mKey: String) : Guide.LifeCallback, Guide.Intercept {
    var mHasShow: Boolean = false
    var mLifeCallback: Guide.LifeCallback? = null

    override fun shouldIntercept(key: String): Boolean {
        if (mHasShow) {
            return true
        }
        mHasShow = CommonPref.getInstance(null).getBoolean(mKey, false)
        return mHasShow
    }

    override fun onShow() {
        CommonPref.getInstance(null).putBoolean(mKey, true)
        mLifeCallback?.run {
            onShow()
        }
    }

    override fun onDismiss() {
        mLifeCallback?.run {
            onDismiss()
        }
    }
}