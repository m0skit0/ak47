package org.m0skit0.android.ak47

import android.util.Log
import android.util.Log.*
import java.util.*
import kotlin.system.measureTimeMillis

private const val DEFAULT_TAG = "LogUtils"

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
fun <T> T.logDebug(tag: String = DEFAULT_TAG): T {
    d(tag, this.asString())
    return this
}

/**
 * Log this object as debug (shorter name)
 */
fun <T> T.log(tag: String) = logDebug(tag)

/**
 * Log this object as info.
 */
fun <T> T.logInfo(tag: String = DEFAULT_TAG): T {
    i(tag, this.asString())
    return this
}

/**
 * Log this object as warning.
 */
fun <T> T.logWarning(tag: String = DEFAULT_TAG): T {
    w(tag, this.asString())
    return this
}


/**
 * Log this object as error.
 */
fun <T> T.logError(tag: String = DEFAULT_TAG): T {
    e(tag, this.asString())
    return this
}

/**
 * Log stacktrace of exception as error
 */
fun <T: Exception> T.log(tag: String = DEFAULT_TAG): T {
    getStackTraceString(this).logError(tag)
    return this
}

/**
 * Executes block and logs execution time for it as debug.
 */
inline fun logExecutionTime(tag: String = "LogUtils", block: () -> Unit) {
    val time = measureTimeMillis {
        block()
    }
    "$tag: $time".logDebug("logExecutionTime")
}

/**
 * Logs the call stack trace as debug.
 */
fun logCallTrace(tag: String = DEFAULT_TAG) {
    try {
        throw IllegalStateException("logCallTrace")
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
        e.log("ignoreErrors")
    }
}