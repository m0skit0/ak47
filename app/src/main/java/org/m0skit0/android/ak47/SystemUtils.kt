package org.m0skit0.android.ak47


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

internal inline fun Boolean.ifTrue(block: () -> Unit) = apply {
    if (this) block()
}

internal inline fun Boolean.ifFalse(block: () -> Unit) = apply {
    if (!this) block()
}
