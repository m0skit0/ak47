package org.m0skit0.android.ak47

private val HEX_CHARS = "0123456789ABCDEF".toCharArray()

fun Array<Byte>.toHex() = this.joinToString(separator = "") { it.toInt().let {
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
