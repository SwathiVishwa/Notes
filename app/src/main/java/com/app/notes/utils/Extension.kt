package com.app.notes.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun showToast(context: Context, msg: String) {
    Toast.makeText(
        context,
        msg, Toast.LENGTH_LONG
    ).show()
}

fun placeHolder(
    list: ArrayList<*>,
    rvNotes: RecyclerView,
    ivPlaceholder: AppCompatImageView,
    noItem: AppCompatTextView,
    noDataSub: AppCompatTextView
) {
    if (list.isNotEmpty()) {
        rvNotes.visible()
        ivPlaceholder.gone()
        noItem.gone()
        noDataSub.gone()
    } else {
        rvNotes.gone()
        ivPlaceholder.visible()
        noItem.visible()
        noDataSub.visible()
    }
}