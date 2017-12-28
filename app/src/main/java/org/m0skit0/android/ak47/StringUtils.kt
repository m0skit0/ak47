package org.m0skit0.android.ak47

import android.app.Activity
import android.content.Context
import android.view.View

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

private fun List<String>.runCommand() =
        Runtime.getRuntime().exec(toTypedArray()).apply { waitFor() }.inputStream.bufferedReader().readText()

/**
 * Executes command on shell and returns standard output.
 * @return Standard output of the command.
 */
fun String.runCommand() =
        Runtime.getRuntime().exec(this).apply { waitFor() }.inputStream.bufferedReader().readText()

/**
 * Executes command on shell as root (superuser) and returns standard output.
 * NOTE: This call needs superuser privileges.
 * @return Standard output of the command.
 */
fun String.runCommandAsSu() =
        listOf("su", "-c", this).runCommand()





