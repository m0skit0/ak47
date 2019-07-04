package org.m0skit0.android.ak47

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import java.io.ByteArrayOutputStream


/**
 * Convert ByteArray to Bitmap
 */
fun ByteArray.asBitmap(): Bitmap = BitmapFactory.decodeByteArray(this, 0, size)

/**
 * Convert ByteArray to Drawable
 */
fun ByteArray.asDrawable(context: Context): Drawable = BitmapDrawable(context.resources, asBitmap())

/**
 * Returns PNG with set quality from a Bitmap. Default quality is 100.
 */
fun Bitmap.toPNG(quality: Int = 100): ByteArray = ByteArrayOutputStream().use { bos ->
    compress(Bitmap.CompressFormat.PNG, quality, bos)
    bos.toByteArray()
}