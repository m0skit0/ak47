package org.m0skit0.android.ak47

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

/**
 * Checks if all permissions are granted for Android M or higher.
 * If so, executes block and returns Any(). If not, directly returns null.
 * If Android version is less than M, executes block and returns Any().
 * @param permissions Collection of permissions to check.
 * @param block Lambda expression to execute if all permissions granted.
 * @return Any() if block was executed, null otherwise.
 */
inline fun Context.checkAllPermissions(permissions: Collection<String>, block: () -> Unit): Any? {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        (permissions.none { checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED }).ifTrue {
            return null
        }
    }
    block()
    return Any()
}

/**
 * Wraps Activity#requestPermissions() with Android version check.
 */
fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        requestPermissions(activity, permissions, requestCode)
    }
}

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.toast(id: Int) {
    Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.longToast(id: Int) {
    Toast.makeText(this, id, Toast.LENGTH_LONG).show()
}

/**
 * Close keyboard
 */
fun Activity.closeKeyboard() {
    currentFocus?.run { (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(windowToken, 0) }
}

/**
 * Open keyboard
 */
fun Activity.openKeyboard() {
    currentFocus?.run { (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInputFromWindow(windowToken, InputMethodManager.SHOW_FORCED,0) }
}

/**
 * Creates an instance of an anonymous BroadcastReceiver.
 * @param block Lambda to be executed when intent is received.
 */
fun broadcastReceiver(block: (Context, Intent) -> Unit) = object : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        block(context, intent)
    }
}