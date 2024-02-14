package com.example.techjet.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.techjet.R
import com.example.techjet.model.Item

class ShowAdapter(val items: MutableList<Item>, val context: Context,var mOnItemClickListener:OnItemClickListener) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = items[position]
        Glide.with(context).load(items.media.m).into(holder.imge)

        holder.des.text = HtmlCompat.fromHtml(items.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
        holder.des.setOnClickListener {
            mOnItemClickListener.onItemClick(items)
        }

    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }


}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val des = view.findViewById<AppCompatTextView>(R.id.tv_description)
    val imge = view.findViewById<AppCompatImageView>(R.id.imageView)
}


interface OnItemClickListener {
    fun onItemClick(item: Item?)
}