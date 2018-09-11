package com.pyf.guideview.guide

import android.view.LayoutInflater
import android.view.View

/**
 * Created by PYF on 2018/9/1
 */

interface Guide {
    val KEY: String

    /**
     * 返回键是否消失
     */
    fun setBackKeyDismiss(backKeyDismiss: Boolean)

    fun autoDismiss(duration: Long)

    fun setAchorView(view: View)

    fun setGuideViewBuilder(builder: GuideBuilder)

    fun setIntercept(intercepts: Intercept)

    fun setLifeCallback(lifeCallbacks: LifeCallback)

    fun show()

    fun dismiss()

    interface GuideBuilder {
        fun getView(inflater: LayoutInflater): View
    }

    interface Intercept {
        fun shouldIntercept(key: String): Boolean
    }

    interface LifeCallback {
        fun onShow()

        fun onDismiss()
    }
}