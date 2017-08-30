package org.m0skit0.android.ak47

import android.app.Activity
import android.content.Context

private val STRING_RESOURCE_IDENTIFIER_REGEX = "@string/(.*)".toRegex()

/**
 * Returns a string resource by id or the same string if not a resource identifier.
 * @param context Android Context for Resources
 */
fun String.getStringResourceByName(context: Context): String {
    val id = STRING_RESOURCE_IDENTIFIER_REGEX.matchEntire(this)?.groups?.get(1)?.value ?: return this
    return context.getString(context.resources.getIdentifier(id, "string", context.packageName))!!
}

fun String.findViewByName(activity: Activity) =
        activity.findViewById(activity.resources.getIdentifier(this, "id", activity.packageName))
