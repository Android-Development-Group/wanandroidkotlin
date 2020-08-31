package com.yicooll.wanandroidkotlin.manager

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.support.annotation.IntRange
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest


/**
 * Description: glide 加载图片转换器之模糊转换器
 * Copyright  : Copyright (c) 2020
 * Email      : jusenr@163.com
 * Author     : Jusenr
 * Date       : 2020/8/26
 * Time       : 14:48
 * Project    ：wanandroidkotlin
 * PackageName：com.yicooll.wanandroidkotlin.manager.
 * @author 吴天强
 */
class BlurTransformation constructor(context: Context?) : BitmapTransformation() {

    private var context: Context? = null
    private var blurRadius = 20 //模糊度 0=< blurRadius >=25

    /**
     * @param context    context
     * @param blurRadius 模糊度 最大25
     */
    constructor(context: Context?, @IntRange(from = 0, to = 25) blurRadius: Int) : this(context) {
        this.context = context
        this.blurRadius = blurRadius
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
    }

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        return blurBitmap(context!!, toTransform, outWidth, outHeight)!!
    }

    /**
     * @param context   上下文对象
     * @param image     需要模糊的图片
     * @param outWidth  输入出的宽度
     * @param outHeight 输出的高度
     * @return 模糊处理后的Bitmap
     */
    @SuppressLint("ObsoleteSdkInt")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun blurBitmap(context: Context, image: Bitmap, outWidth: Int, outHeight: Int): Bitmap? {
        // 将缩小后的图片做为预渲染的图片
        val inputBitmap = Bitmap.createScaledBitmap(image, outWidth, outHeight, false)
        // 创建一张渲染后的输出图片
        val outputBitmap = Bitmap.createBitmap(inputBitmap)
        // 创建RenderScript内核对象
        val rs = RenderScript.create(context)
        // 创建一个模糊效果的RenderScript的工具对象
        val blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
        val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
        val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)
        // 设置渲染的模糊程度, 25f是最大模糊度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //模糊度最大到25
            if (blurRadius < 0 || blurRadius > 25) {
                blurRadius = 20
            }
            blurScript.setRadius(blurRadius.toFloat())
        }
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn)
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut)
        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap)
        return outputBitmap
    }
}