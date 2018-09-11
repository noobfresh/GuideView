package com.pyf.guideview.guide

import android.os.Handler
import android.view.View

/**
 * Created by PYF on 2018/9/1
 */

abstract class AbsGuide(open var mKey: String) : Guide {
    override val KEY: String = "AbsGuide"
    protected var mBackKeyDismiss = false
    protected var mAutoDismissDuration: Long = -1L
    protected var mGuideView: View? = null
    protected var mGuideBuilder: Guide.GuideBuilder? = null
    protected var mAnchorView: View? = null
    var isShow = false
    var mIntercepts: Guide.Intercept? = null
    var mLifeCallback: Guide.LifeCallback? = null
    private lateinit var mHandler: Handler
    private var mAutoDismissRunnable: Runnable = object : Runnable {
        override fun run() {
            dismiss()
        }
    }

    override fun setBackKeyDismiss(backKeyDismiss: Boolean) {
        mBackKeyDismiss = backKeyDismiss
    }

    override fun autoDismiss(duration: Long) {
        mAutoDismissDuration = duration
    }

    override fun setAchorView(view: View) {
        mAnchorView = view
    }

    override fun setGuideViewBuilder(builder: Guide.GuideBuilder) {
        mGuideBuilder = builder
    }

    override fun setIntercept(intercepts: Guide.Intercept) {
        mIntercepts = intercepts
    }

    override fun setLifeCallback(lifeCallbacks: Guide.LifeCallback) {
        mLifeCallback = lifeCallbacks
    }

    override fun show() {
        mIntercepts?.let {
            if (it.shouldIntercept(mKey)) {
                return
            }
        }

        if (mBackKeyDismiss) {
            subscribeBackKeyEvent()
        }

        if (mAutoDismissDuration > 0) {
            if (mHandler == null) {
                mHandler = Handler()
            }
            mHandler.postDelayed(mAutoDismissRunnable, mAutoDismissDuration)
        }

        showInternal()

        mLifeCallback?.run {
            onShow()
        }
        isShow = true
    }

    override fun dismiss() {
        dismissInternal()

        if (mAutoDismissDuration != -1L) {
            mHandler.removeCallbacks(mAutoDismissRunnable)
        }

        mAnchorView = null
        mGuideView = null

        mLifeCallback?.run {
            onDismiss()
        }
        isShow = false
    }

    abstract fun showInternal()

    abstract fun dismissInternal()

    abstract fun subscribeBackKeyEvent()

    abstract fun unSubscribeBackKeyEvent()

    fun withOnce(lifeCallbacks: Guide.LifeCallback) {
        val defaultOnce = DefaultOnce(mKey)
        defaultOnce.mLifeCallback = lifeCallbacks
        mIntercepts = defaultOnce
        mLifeCallback = defaultOnce
    }
}