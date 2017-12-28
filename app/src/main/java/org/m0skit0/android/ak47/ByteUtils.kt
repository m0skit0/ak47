package org.m0skit0.android.ak47

import android.util.Base64

private val HEX_CHARS = "0123456789ABCDEF".toCharArray()

fun Array<Byte>.toHex() = joinToString(separator = "") { it.toInt().let {
        "${HEX_CHARS[(it and 0xF0).ushr(4)]}${HEX_CHARS[it and 0x0F]}"
    }.let {
        if (it.length == 1) {
            "0$it"
        } else {
            it
        }
    }
}

fun Array<Byte>.toHexLittleEndian() = apply { reverse() }.toHex()
fun Array<Byte>.toHexBigEndian() = toHex()

fun ByteArray.toHex() = toTypedArray().toHex()
fun ByteArray.toHexLittleEndian() = toTypedArray().toHexLittleEndian()
fun ByteArray.toHexBigEndian() = toHex()

fun String.decode() = Base64.decode(this, Base64.DEFAULT)
fun ByteArray.encode() = Base64.encodeToString(this, Base64.DEFAULT)
fun String.encode() = Base64.encodeToString(toByteArray(), Base64.DEFAULT)
