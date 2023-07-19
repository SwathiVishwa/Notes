package com.app.notes.utils

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
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