package fr.louprod.testlbc.customviews

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AlertDialog
import fr.louprod.testlbc.R

class CustomLoader(context: Context) {
    private var dialog: Dialog? = null
    init {
        dialog = AlertDialog.Builder(context)
            .setView(R.layout.custom_loader)
            .setCancelable(false)
            .create()
    }

    fun show() {
        dialog?.show()
    }

    fun hide() {
        dialog?.hide()
    }
}