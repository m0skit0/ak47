package org.m0skit0.android.ak47

import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.TextView


// Animations
fun View.doAnimation(animationId: Int, preAnimation: (Animation) -> Unit) = apply {
    AnimationUtils.loadAnimation(context, animationId).apply {
        preAnimation(this)
        startAnimation(this)
    }
}

fun View.slideUp(preAnimation: (Animation) -> Unit = {}): View = doAnimation(R.anim.slide_up, preAnimation)
fun View.slideDown(preAnimation: (Animation) -> Unit = {}): View = doAnimation(R.anim.slide_down, preAnimation)
fun View.shake(preAnimation: (Animation) -> Unit = {}): View = doAnimation(R.anim.shake, preAnimation)


// Visibility
fun View.visible() = apply { visibility = VISIBLE }
fun View.invisible() = apply { visibility = INVISIBLE }
fun View.gone() = apply { visibility = GONE }

// Enabling/disabling
fun View.enable() = apply { isEnabled = true }
fun View.disable() = apply { isEnabled = false }

private fun ViewGroup.enableDisableChildren(enable: Boolean): ViewGroup = apply {
    (0 until childCount).forEach {
        when (val view = getChildAt(it)) {
            is ViewGroup -> view.enableDisableChildren(enable)
            else -> if (enable) view.enable() else view.disable()
        }
    }
}

fun ViewGroup.disableChildren() = enableDisableChildren(false)
fun ViewGroup.enableChildren() = enableDisableChildren(true)

/**
 * Simpler set/get text for EditTexts.
 * BufferType is set to NORMAL
 */
var EditText.textString: String
    get() = text.toString()
    set(value) {
        setText(value, TextView.BufferType.NORMAL)
    }

