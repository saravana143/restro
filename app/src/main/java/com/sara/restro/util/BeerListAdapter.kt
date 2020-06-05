package com.sara.restro.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sara.restro.R
import com.sara.restro.model.Beer
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class BeerListAdapter(onImageListener: OnImageListener) :
    PagedListAdapter<Beer, BeerListAdapter.MyViewHolder>(diffCallback) {

    private var mOnImageListener: OnImageListener = onImageListener

    class MyViewHolder(view: View, var onImageListener: OnImageListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onImageListener.onImageClick(adapterPosition)
        }
    }

    interface OnImageListener {
        fun onImageClick(position: Int)
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<Beer>() {
            override fun areItemsTheSame(
                oldItem: Beer,
                newItem: Beer
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Beer,
                newItem: Beer
            ): Boolean =
                oldItem.equals(newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(view, mOnImageListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = getItem(position)

        holder.itemView.description.setText(post?.name)
        holder.itemView.abv.setText( "ABV - " + post?.abv.toString())

        Glide.with(holder.itemView.context)
            .load(post?.image_url)
            .placeholder(R.drawable.corner_bg)
            .apply(RequestOptions().override(100, 100))
            .into(holder.itemView.imageViewItem)
    }

}
