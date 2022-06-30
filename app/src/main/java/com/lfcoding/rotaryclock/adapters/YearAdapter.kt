package com.lfcoding.rotaryclock.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lfcoding.rotaryclock.R

class YearAdapter : RecyclerView.Adapter<YearAdapter.ViewHolder>() {

    private val digitsList = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)

    init{
        Log.d("TAG", "Adapter initialized.")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearAdapter.ViewHolder {
        Log.d("TAG", "Caling onCreateViewHolder")
        val v = LayoutInflater.from(parent.context).inflate(R.layout.year_recycler_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: YearAdapter.ViewHolder, position: Int) {
        val pos = position % digitsList.size
        Log.d("TAG", "Pos = $pos")
        holder.itemTextView.text = digitsList[pos].toString()
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
        //return digitsList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTextView: TextView = itemView.findViewById(R.id.textView)
    }
}