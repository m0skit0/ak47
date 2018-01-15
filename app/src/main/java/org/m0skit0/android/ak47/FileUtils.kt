package org.m0skit0.android.ak47

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import java.io.File


/**
 * Returns standard Android download public directory.
 */
val defaultDownloadDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

/**
 * Returns directory where attendance photos are stored.
 */
val defaultPicturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)

/**
 * Installs file as APK. Asks for user permission.
 */
fun File.installAPK(context: Context): File {
    val intent = Intent(Intent.ACTION_INSTALL_PACKAGE)
    intent.data = Uri.fromFile(this)
    context.startActivity(intent)
    return this
}

/**
 * Installs file as APK without asking permission. Requires root permissions.
 */
fun File.installAPKNoPrompt(): File {
    "pm install -r ${this.absolutePath}".runCommandAsSu()
    return this
}

private fun File.installSystemAPKLowerThanLollipop(): File {
    "busybox mv ${this.absolutePath} /system/app/".log().runCommandAsSu()
    return this
}

private fun File.installSystemAPKLollipopOrHigher(): File {
    val apkDirectory = "/system/priv-app/$nameWithoutExtension"
    "mkdir $apkDirectory".runCommandAsSu()
    "cp ${this.absolutePath} $apkDirectory".runCommandAsSu()
    return this
}

fun File.forceDelete() {
    "rm -r ${this.canonicalPath}".runCommandAsSu()
}

fun File.forceMkdir() {
    "mkdir $absolutePath".runCommandAsSu()
}

/**
 * Installs file as system APK without asking permission. Requires root permissions.
 * Depending on the Android version, it might require a reboot.
 */
fun File.installSystemAPK(): File {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP ) {
        this.installSystemAPKLowerThanLollipop()
    } else {
        this.installSystemAPKLollipopOrHigher()
    }
}

/**
 * File MD5 hash.
 * @return MD5 hash as string.
 */
fun File.md5() = "md5 ${this.absolutePath}".runCommand().substringBefore(' ')

/**
 * Copies this files to the destination one, no matter file system permissions. Requires root permissions.
 * @return Copied file.
 */
fun File.copyToAsSu(destination: File): File {
    "busybox cp ${this.absolutePath} ${destination.absolutePath}".runCommandAsSu()
    return destination
}

/**
 * Returns image file as Bitmap
 */
fun File.asBitmap() = BitmapFactory.decodeFile(absolutePath)

/**
 * Returns image file as Drawable
 */
fun File.asDrawable() = BitmapDrawable(asBitmap())