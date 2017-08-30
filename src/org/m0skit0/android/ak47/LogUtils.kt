package org.m0skit0.android.ak47

import android.util.Log
import android.util.Log.*
import java.util.*
import kotlin.system.measureTimeMillis

private fun <T> T.asString() = when(this) {
        is Array<*> -> Arrays.toString(this)
        else -> this.toString()
    }

/**
 * Log this object as debug.
 */
fun <T> T.logDebug(tag: String): T {
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
fun <T> T.logInfo(tag: String): T {
    i(tag, this.asString())
    return this
}

/**
 * Log this object as warning.
 */
fun <T> T.logWarning(tag: String): T {
    w(tag, this.asString())
    return this
}


/**
 * Log this object as error.
 */
fun <T> T.logError(tag: String): T {
    e(tag, this.asString())
    return this
}

/**
 * Log stacktrace of exception as error
 */
fun <T: Exception> T.log(tag: String): T {
    getStackTraceString(this).logError(tag)
    return this
}

/**
 * Executes block and logs execution time for it as debug.
 */
inline fun logExecutionTime(tag: String, block: () -> Unit) {
    val time = measureTimeMillis {
        block()
    }
    "$tag: $time".logDebug("logExecutionTime")
}

/**
 * Logs the call stack trace as debug.
 */
fun logCallTrace() {
    try {
        throw IllegalStateException("logCallTrace")
    } catch (e: IllegalStateException) {
        Log.getStackTraceString(e).logDebug("logCallTrace")
    }
}