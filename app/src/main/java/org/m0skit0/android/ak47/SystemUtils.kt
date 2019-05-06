package org.m0skit0.android.ak47

import android.annotation.TargetApi
import android.app.AlarmManager
import android.content.Context
import android.hardware.camera2.CameraManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.nfc.NfcManager
import android.os.BatteryManager
import android.os.Build
import android.telephony.TelephonyManager
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

inline infix fun Boolean.ifTrue(block: () -> Unit) = apply {
    if (this) block()
}

inline infix fun Boolean.ifFalse(block: () -> Unit) = apply {
    if (!this) block()
}

/**
 * Returns the date formatted according to the passed format.
 * @param format Format to be used as specified by DateFormat.
 * @return Formatted date according to passed string format.
 */
fun Date.format(format: String) = SimpleDateFormat(format, Locale.US).format(this)

/**
 * Simplified system services
 */
fun Context.getAlarmManager() = getSystemService(Context.ALARM_SERVICE) as AlarmManager
fun Context.getTelephonyManager() = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
fun Context.getLocationManager() = getSystemService(Context.LOCATION_SERVICE) as LocationManager
fun Context.getConnectivityManager() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
fun Context.getNFCManager() = getSystemService(Context.NFC_SERVICE) as NfcManager

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getBatteryManager() = getSystemService(Context.BATTERY_SERVICE) as BatteryManager

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun Context.getCameraManager() = getSystemService(Context.CAMERA_SERVICE) as CameraManager

fun Context.getWifiManager() = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager


