package org.m0skit0.android.ak47

import java.text.SimpleDateFormat
import java.util.*


private fun List<String>.runCommand() = Runtime.getRuntime().exec(toTypedArray()).apply { waitFor() }.inputStream.bufferedReader().readText()

/**
 * Executes command on shell and returns standard output.
 * @return Standard output of the command.
 */
fun String.runCommand() = Runtime.getRuntime().exec(this).apply { waitFor() }.inputStream.bufferedReader().readText()

/**
 * Executes command on shell as root (superuser) and returns standard output.
 * NOTE: This call needs superuser privileges.
 * @return Standard output of the command.
 */
fun String.runCommandAsSu() = listOf("su", "-c", this).runCommand()

/**
 * Powers off the device
 */
fun shutdown() {
    "reboot -p".runCommand()
}

/**
 * Reboots the device
 */
fun reboot() {
    "reboot".runCommand()
}

inline fun Boolean.ifTrue(block: () -> Unit) = apply {
    if (this) block()
}

inline fun Boolean.ifFalse(block: () -> Unit) = apply {
    if (!this) block()
}

/**
 * Returns the date formatted according to the passed format.
 * @param format Format to be used as specified by DateFormat.
 * @return Formatted date according to passed string format.
 */
fun Date.format(format: String) = SimpleDateFormat(format, Locale.US).format(this)
