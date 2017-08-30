package org.m0skit0.android.ak47

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import java.io.File

/**
 * Return file name without extension.
 * NOTE: file name should not contain any dots (.).
 */
fun File.getNameWithoutExtension() = this.name.substringBefore('.')

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
 * Returns standard Android download public directory.
 */
fun getDownloadDirectory() = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)