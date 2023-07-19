package com.app.notes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.notes.R
import com.app.notes.db.NotesSchema

class NotesAdapter(
    private var itemsList: List<NotesSchema>,
    private val clickListener: RecyclerClickListener
) :
    RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvId: TextView = view.findViewById(R.id.tvId)
        var tvDate: TextView = view.findViewById(R.id.tvDate)
        var itemTextView: TextView = view.findViewById(R.id.tvTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notes, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.apply {
            tvId.text = "${item.sNo}."
            tvDate.text = "Created at : ${item.date}"
            itemTextView.text = item.title
            itemView.setOnClickListener {
                clickListener.onItemClick(position, item)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}

interface RecyclerClickListener {
    fun onItemRemoveClick(position: Int)
    fun onItemClick(position: Int, data: NotesSchema)
}

