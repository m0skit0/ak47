package org.m0skit0.android.ak47

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