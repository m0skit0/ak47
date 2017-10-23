package org.m0skit0.android.ak47

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

/**
 * Checks if all permissions are granted for Android M or higher.
 * If so, executes block and returns Any(). If not, directly returns null.
 * @param permissions Collection of permissions to check.
 * @param block Lambda expression to execute if all permissions granted.
 * @return Any() if block was executed, null otherwise.
 */
inline fun Context.checkAllPermissions(permissions: Collection<String>, block: () -> Unit): Any? {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        return if (permissions.all { checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED }) {
            block()
            Any()
        } else {
            null
        }
    }
    return null
}

/**
 * Wraps Activity#requestPermissions() with Android version check.
 */
fun Activity.requestPermissions(permissions: Array<String>, requestCode: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        requestPermissions(permissions, requestCode)
    }
}