package com.sara.restro.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sara.restro.R
import com.sara.restro.util.ProductListAdapter
import com.sara.restro.viewmodel.ProductListViewModel
import kotlinx.android.synthetic.main.fragment_beer_list.*
import kotlinx.android.synthetic.main.fragment_beer_list.textViewConnectivity
import kotlinx.android.synthetic.main.fragment_products_list.*
import tk.zedlabs.wallportal.util.isConnectedToNetwork


class ProductListFragment() : Fragment(), ProductListAdapter.OnProductItemListener {
    private lateinit var productListViewModel: ProductListViewModel
    private lateinit var productListAdapter: ProductListAdapter
    private lateinit var productLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_products_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Product List"

        when (requireContext().isConnectedToNetwork()) {
            true -> if (textViewConnectivity.visibility == View.VISIBLE) textViewConnectivity.visibility =
                View.GONE
            false -> textViewConnectivity.visibility = View.VISIBLE
        }

        productListAdapter = ProductListAdapter(activity, this)
        productLayoutManager = LinearLayoutManager(this.context)

        productListView.apply {
            this.layoutManager = productLayoutManager
            this.adapter = productListAdapter
        }

        productListViewModel = ViewModelProviders.of(this).get(ProductListViewModel::class.java)
        productListViewModel.beerList?.observe(viewLifecycleOwner, Observer { postList ->
            Log.d("Response", "=" + postList.toString())
            productListAdapter.setProducts(postList)

            progressBar.visibility = View.GONE
            productListView.visibility = View.VISIBLE
        })

    }

    override fun onProductClick(position: Int) {
        val beerDetail = productListViewModel.beerList?.value?.get(position)

        val name = beerDetail?.name ?: "beerName"
        val url = beerDetail?.image_url ?: "url"
        val abv = beerDetail?.abv ?: 0.0f
        val desc = beerDetail?.description ?: "desc"
        val info = beerDetail?.contributed_by ?: "info"

        val action = ProductListFragmentDirections.actionProductListFragmentToBeerDetailFragment(
            name,
            url,
            abv,
            desc,
            info
        )

        findNavController().navigate(action)
    }
}