package com.thedevelopercat.demo.builders

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.annotation.StringRes

class CustomDialogBuilder(context: Context) {

    private var dismiss: Boolean = false
    private var title: String? = null
    private var positiveTitle: String? = null
    private var negativeTitle: String? = null
    @StringRes private var positiveTitleRes: Int? = null
    @StringRes private var negativeTitleRes: Int? = null
    private var message: String? = null

    private var builder: AlertDialog.Builder = AlertDialog.Builder(context)

    fun setTitle(title: String?): CustomDialogBuilder {
        this.title = title
        builder.setTitle(title)
        return this
    }

    fun setMessage(message: String?): CustomDialogBuilder {
        this.message = message
        builder.setMessage(message)
        return this
    }

    fun setPositiveButton(positiveTitle: String?, onClick: ()->Unit): CustomDialogBuilder {
        this.positiveTitle = positiveTitle
        builder.setPositiveButton(positiveTitle, {_, _ -> onClick()})
        return this
    }

    fun setPositiveButton(@StringRes positiveTitle: Int, onClick: ()->Unit): CustomDialogBuilder {
        this.positiveTitleRes = positiveTitle
        builder.setPositiveButton(positiveTitle, {_, _ -> onClick()})
        return this
    }

    fun setNegativeButton(negativeTitle: String?, onClick: ()->Unit = {}): CustomDialogBuilder {
        this.negativeTitle = negativeTitle
        builder.setNegativeButton(negativeTitle, {dialog,_ -> handleNegative(dialog, onClick)})
        return this
    }

    fun setNegativeButton(@StringRes negativeTitle: Int, onClick: ()->Unit = {}): CustomDialogBuilder {
        this.negativeTitleRes = negativeTitle
        builder.setNegativeButton(negativeTitle, {dialog,_ -> handleNegative(dialog, onClick)})
        return this
    }

    fun dismissOnNegative(dismiss: Boolean): CustomDialogBuilder {
        this.dismiss = dismiss
        return this
    }

    fun setNotCancelable(): CustomDialogBuilder {
        builder.setCancelable(false)
        return this
    }

    fun buildAndShow() {
        builder.create().show()
    }

    private fun handleNegative(dialog: DialogInterface, onClick: ()->Unit){
        if(dismiss){
            dialog.dismiss()
        }
        else{
            onClick()
        }
    }
}
