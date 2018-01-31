package org.m0skit0.android.ak47

import android.util.Log
import android.util.Log.*
import java.util.*
import kotlin.system.measureTimeMillis

private const val TAG = "LogUtils"

private fun <T> T.asString() = when (this) {
    is Array<*> -> Arrays.toString(this)
    is ByteArray -> Arrays.toString(this)
    is ShortArray -> Arrays.toString(this)
    is IntArray -> Arrays.toString(this)
    is LongArray -> Arrays.toString(this)
    else -> this.toString()
}

/**
 * Log this object as debug.
 */
fun <T> T.logDebug(tag: String = TAG) = apply {
    d(tag, this.asString())
}

/**
 * Log this object as debug (shorter name)
 */
fun <T> T.log(tag: String = TAG) = logDebug(tag)

/**
 * Log this object as info.
 */
fun <T> T.logInfo(tag: String = TAG) = apply {
    i(tag, this.asString())
}

/**
 * Log this object as warning.
 */
fun <T> T.logWarning(tag: String = TAG) = apply {
    w(tag, this.asString())
}


/**
 * Log this object as error.
 */
fun <T> T.logError(tag: String = TAG) = apply {
    e(tag, this.asString())
}

/**
 * Log stacktrace of exception as error
 */
fun <T: Exception> T.log(tag: String = TAG) = apply {
    getStackTraceString(this).logError(tag)
}

/**
 * Executes block and logs execution time for it as debug.
 */
inline fun <T> logExecutionTime(tag: String = "LogUtils", block: () -> T?): T? {
    var value: T? = null
    measureTimeMillis {
        value = block()
    }.apply {
        "$tag: $this".logDebug(tag)
    }
    return value
}

/**
 * Logs the call stack trace as debug.
 */
fun logCallTrace(tag: String = TAG) {
    try {
        throw IllegalStateException()
    } catch (e: IllegalStateException) {
        Log.getStackTraceString(e).logDebug(tag)
    }
}

/**
 * Catches and logs any exception for this block and continue execution.
 * Note that execution of block will exit on first statement that throws an exception.
 * Exception is logged as ERROR level.
 */
inline fun ignoreErrors(block: () -> Unit) {
    try {
        block()
    } catch (e: Exception) {
        e("ignoreErrors", Log.getStackTraceString(e))
    }
}