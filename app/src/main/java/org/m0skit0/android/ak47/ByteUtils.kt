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
fun Array<Byte>.toHex() = joinToString(separator = "") { it.toInt().let {
        "${HEX_CHARS[(it and 0xF0).ushr(4)]}${HEX_CHARS[it and 0x0F]}"
    }.let {
        if (it.length == 1) "0$it" else it
    }
}

fun Array<Byte>.toHexLittleEndian() = apply { reverse() }.toHex()
fun Array<Byte>.toHexBigEndian() = toHex()

/**
 * Returns hexadecimal string representation of this ByteArray
 */
fun ByteArray.toHex() = toTypedArray().toHex()
fun ByteArray.toHexLittleEndian() = toTypedArray().toHexLittleEndian()
fun ByteArray.toHexBigEndian() = toHex()

/**
 * Decode Base64 string to ByteArray
 */
fun String.decode() = Base64.decode(this, Base64.DEFAULT)

/**
 * Encode ByteArray to Base64 string
 */
fun ByteArray.encode() = Base64.encodeToString(this, Base64.DEFAULT)

/**
 * Convert ByteArray to Bitmap
 */
fun ByteArray.asBitmap() = BitmapFactory.decodeByteArray(this, 0, size)

/**
 * Convert ByteArray to Drawable
 */
fun ByteArray.asDrawable() = BitmapDrawable(asBitmap())

/**
 * Returns PNG with quality 50 from a Bitmap.
 */
fun Bitmap.toPNG() = ByteArrayOutputStream().use { bos ->
    compress(Bitmap.CompressFormat.PNG, 50, bos)
    bos.toByteArray()
}
