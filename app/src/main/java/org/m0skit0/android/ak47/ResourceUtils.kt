package org.m0skit0.android.ak47

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import java.io.Serializable


private val STRING_RESOURCE_IDENTIFIER_REGEX = "@string/(.*)".toRegex()

/**
 * Returns a string resource by id or the same string if not a resource identifier.
 * @param context Android Context for Resources.
 * @return String resource that has that name or the same name if none found.
 */
fun String.getStringResourceByName(context: Context): String {
    val id = STRING_RESOURCE_IDENTIFIER_REGEX.matchEntire(this)?.groups?.get(1)?.value ?: return this
    return context.getString(context.resources.getIdentifier(id, "string", context.packageName))!!
}

/**
 * Returns a View by its string name (not integer identifier).
 * @param activity Android Activity where the View is present.
 * @return View that has that name or null if no View found
 */
fun <T: View> String.findViewByName(activity: Activity) =
        activity.findViewById<T>(activity.resources.getIdentifier(this, "id", activity.packageName))

/**
 * Returns a new map with the contents of the Bundle. This is useful specially for logging.
 * @return Map that mirrors the Bundle.
 */
fun Bundle.toMap() = keySet().associate { it to get(it) }

/**
 * Returns a Bundle from a map.
 * @return Bundle that mirrors the map
 */
fun Map<String, Any>.toBundle() = Bundle().apply {
    keys.forEach { key ->
        this@toBundle[key].let { value ->
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Float -> putFloat(key, value)
                is Double -> putDouble(key, value)
                is Boolean -> putBoolean(key, value)
                is ByteArray -> putByteArray(key, value)
                is Serializable -> putSerializable(key, value)
                else -> throw IllegalArgumentException("Unsupported value of type ${value?.javaClass}")
            }
        }
    }
}
