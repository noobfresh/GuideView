package com.pyf.guideview.guide

/**
 * Created by PYF on 2018/9/11
 */
class FloatFullCoverGuide(mKey: String) : FullCoverGuide(mKey) {
    /**
     * FloatView 目标附着的View
     * @param resId
     */
    fun getTargetView(resId: Int) {

    }

    /**
     * 必须是传进来的GuideView内的resId
     * @param resourceId
     */
    fun getFloatView(resourceId: Int) {

    }

    //要怎么判断这个View要放在哪个位置呢，提供什么样的接口来判断
    enum class BenchMark {
        LEFT_TOP, RIGHT_TOP, LEFT_BOTTOM, RIGHT_BOTTOM,
        LEFT, TOP, RIGHT, BOTTOM
    }

    /**
     * 设置基准点，根据这个基准点，来将floatView放在指定target的上下左右
     * @param mark
     */
    fun setBenchMark(mark: BenchMark) {

    }

    fun setFloatPosition(mark: BenchMark) {

    }

    /**
     * 相对基准点的偏移
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    fun setMargins(left: Int, top: Int, right: Int, bottom: Int) {

    }
}