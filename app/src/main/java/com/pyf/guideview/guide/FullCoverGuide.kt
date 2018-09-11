package com.pyf.guideview.guide

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by PYF on 2018/9/1
 */

open class FullCoverGuide(override var mKey: String): AbsGuide(mKey) {


    override fun showInternal() {
        mAnchorView?.let {
            if (it !is ViewGroup) {
                Log.e(KEY, "必须依附一个ViewGroup,而且不能为空")
                return
            }
            mGuideView = mGuideBuilder?.getView(LayoutInflater.from(it.context))
            if (mGuideView == null) {
                Log.e(KEY, "添加的tips为空")
                return
            }
            it.addView(mGuideView)
        }
    }

    override fun dismissInternal() {
        mAnchorView?.let {
            if (it !is ViewGroup) {
                Log.e(KEY, "必须依附一个ViewGroup,而且不能为空")
                return
            }
            it.removeView(mGuideView)
        }
    }

    override fun subscribeBackKeyEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unSubscribeBackKeyEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}