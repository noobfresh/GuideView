package com.pyf.guideview.guide

import android.content.SharedPreferences

/**
 * Created by PYF on 2018/9/1
 */

class DefaultOnce (var mKey: String) : Guide.LifeCallback, Guide.Intercept {
    var mHasShow: Boolean = false
    lateinit var mLifeCallback: Guide.LifeCallback

    override fun shouldIntercept(key: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        if (mHasShow) {
            return true
        }
        mHasShow =
    }

    override fun onShow() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDismiss() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}