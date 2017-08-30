package org.m0skit0.android.ak47

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
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
fun File.installAPK(context: Context) = apply {
        val intent = Intent(Intent.ACTION_INSTALL_PACKAGE)
        intent.data = Uri.fromFile(this)
        context.startActivity(intent)
    }

/**
 * Installs file as APK without asking permission.
 * NOTE: This call needs superuser privileges.
 */
fun File.installAPKNoPrompt() = apply {
            "pm install -r ${this.absolutePath}".runCommandAsSu()
        }
/**
 * Installs file as system APK without asking permission.
 * NOTE: Depending on the Android version, it might require a reboot.
 * NOTE: This call needs superuser privileges.
 */
fun File.installSystemAPK() = apply {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP ) {
            "cp $absolutePath /system/app".runCommandAsSu()
        } else {
            val apkDirectory = "/system/priv-app/${getNameWithoutExtension()}"
            "mkdir $apkDirectory".runCommandAsSu()
            "cp $absolutePath $apkDirectory".runCommandAsSu()
        }
    }

/**
 * Returns standard Android download public directory.
 */
fun getDownloadDirectory() = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

/**
 * Calculates this file's MD5 hash.
 * @return MD5 hash of file as string.
 */
fun File.md5() = "md5 $absolutePath".runCommand().substringBefore(' ')