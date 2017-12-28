package org.m0skit0.android.ak47

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
