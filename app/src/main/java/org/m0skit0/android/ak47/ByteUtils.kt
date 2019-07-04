package org.m0skit0.android.ak47

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import java.io.ByteArrayOutputStream

private val HEX_CHARS = "0123456789ABCDEF".toCharArray()

/**
 * Returns hexadecimal string representation of this byte array
 */
fun Array<Byte>.toHex(): String = joinToString(separator = "") { it.toInt().let {
        "${HEX_CHARS[(it and 0xF0).ushr(4)]}${HEX_CHARS[it and 0x0F]}"
    }.let {
        if (it.length == 1) "0$it" else it
    }
}

fun Array<Byte>.toHexLittleEndian(): String = apply { reverse() }.toHex()
fun Array<Byte>.toHexBigEndian(): String = toHex()

/**
 * Returns hexadecimal string representation of this ByteArray
 */
fun ByteArray.toHex(): String = toTypedArray().toHex()
fun ByteArray.toHexLittleEndian(): String = toTypedArray().toHexLittleEndian()
fun ByteArray.toHexBigEndian(): String = toHex()

/**
 * Decode Base64 string to ByteArray
 */
fun String.decode(): ByteArray = Base64.decode(this, Base64.NO_WRAP)

/**
 * Encode ByteArray to Base64 string
 */
fun ByteArray.encode(): String = Base64.encodeToString(this, Base64.NO_WRAP)
