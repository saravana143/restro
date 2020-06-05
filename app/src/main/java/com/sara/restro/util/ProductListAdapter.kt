package com.sara.restro.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sara.restro.R
import com.sara.restro.model.Beer

class ProductListAdapter(context: Context?, onProductItemListener: OnProductItemListener) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    private val mInflater: LayoutInflater
    private var products: List<Beer>
    private var onProductItemListener: OnProductItemListener = onProductItemListener

    init {
        mInflater = LayoutInflater.from(context)
        products = ArrayList<Beer>()
    }

    fun setProducts(products: List<Beer>) {
        this.products = products
        notifyDataSetChanged()
    }

    class ProductViewHolder(view: View, var onProductItemListener: OnProductItemListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        var productNameView: TextView
        var productAbvView: TextView
        var productImageView: ImageView

        fun bindData(item: Beer) {
            productNameView.text = item.name
            productAbvView.text = "ABV - " + item?.abv.toString()

            Glide.with(itemView.context)
                .load(item?.image_url)
                .placeholder(R.drawable.corner_bg)
                .apply(RequestOptions().override(100, 100))
                .into(productImageView)
        }

        init {
            productNameView = itemView.findViewById(R.id.productName)
            productAbvView = itemView.findViewById(R.id.productABV)
            productImageView = itemView.findViewById(R.id.productImage)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onProductItemListener.onProductClick(adapterPosition)
        }
    }

    interface OnProductItemListener {
        fun onProductClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View = mInflater.inflate(R.layout.products_list_item, parent, false)
        return ProductViewHolder(view, onProductItemListener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products.get(position)
        holder.bindData(product)
    }

    override fun getItemCount(): Int {
        return products.size
    }

}
