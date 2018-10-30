package org.m0skit0.android.ak47

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import kotlin.concurrent.thread

/**
 * Activity extension that shows a progress dialog with messages and callbacks.
 * @param msgDoing Working message.
 * @param msgDone Message shown after task has finished successfully (if it returns anything but false)
 * @param msgDoneFailure Message shown if task returns false.
 * @param result Function run after main task.
 * @param task Function to run on background.
 */
fun Activity.progressDialog(msgDoing: Int, msgDone: Int? = null, msgDoneFailure: Int? = null, result: (Any?) -> Unit = {}, task: () -> Any?) {
    ProgressDialog(this).run {
        thread {
            runOnUiThread {
                setCancelable(false)
                setMessage(getString(msgDoing))
                show()
            }

            task().let { returnValue ->
                result(returnValue)

                val msg = if (returnValue is Boolean && returnValue) {
                    msgDoneFailure
                } else {
                    msgDone
                }

                runOnUiThread {
                    dismiss()
                    msg?.run { toast(msg) }
                }
            }
        }
    }
}

/**
 * Creates and shows a confirmation Yes/No dialog.
 * @param title Dialog title.
 * @param message Dialog message.
 * @param cancel Optional lambda to be executed if No button is pressed.
 * @param ok Lambda to be execute if Yes button is pressed.
 */
fun Context.confirmationDialog(title: Int, message: Int, cancel: () -> Unit = {}, ok: () -> Unit) =
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes) { _, _ -> ok() }
                .setNegativeButton(android.R.string.no) { _, _ -> cancel() }
                .show()

/**
 * Creates and shows a single choice dialog, where a list of options is presented and user must select one (no item is preselected).
 * @param title Dialog title.
 * @param elements Collection of elements to be shown for selection.
 * @param notSelectedMessage Message to show if no item has been selected.
 * @param cancel Lambda to be executed if Cancel button is pressed.
 * @param ok Lambda to be executed if Cancel button is pressed. Index of selected element is passed as parameter.
 */
fun Activity.singleChoiceDialog(title: Int, elements: List<String>, notSelectedMessage: Int, cancel: () -> Unit = {}, ok: (String) -> Unit) =
        AlertDialog.Builder(this)
                .setTitle(title)
                .setNegativeButton(android.R.string.cancel) { _, _ -> cancel() }
                .apply {
                    var checkedItem = -1
                    setSingleChoiceItems(elements.toTypedArray(), -1) { _, which -> checkedItem = which }
                    setPositiveButton(android.R.string.ok) { _, _ ->
                        if (checkedItem == -1) {
                            toast(notSelectedMessage)
                        } else {
                            ok(elements[checkedItem])
                        }
                    }
                }
                .show()
