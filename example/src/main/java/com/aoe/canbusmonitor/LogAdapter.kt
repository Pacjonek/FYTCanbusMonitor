package com.aoe.canbusmonitor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LogAdapter(private val maxLines: Int) : RecyclerView.Adapter<LogAdapter.LogViewHolder>() {

    private val lines = ArrayList<String>()

    class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.log_line)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_log, parent, false)
        return LogViewHolder(view)
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.text.text = lines[position]
    }

    override fun getItemCount(): Int = lines.size

    /** Appends [line]; trims the oldest entry when over [maxLines]. Returns the new last index. */
    fun add(line: String): Int {
        val trimmed = lines.size == maxLines
        if (trimmed) {
            lines.removeAt(0)
            notifyItemRemoved(0)
        }
        lines.add(line)
        val insertedIndex = lines.size - 1
        notifyItemInserted(insertedIndex)
        if (trimmed) {
            notifyItemRangeChanged(0, insertedIndex)
        }
        return insertedIndex
    }
}
