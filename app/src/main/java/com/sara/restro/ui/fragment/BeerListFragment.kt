package com.sara.restro.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sara.restro.R
import com.sara.restro.model.Beer
import com.sara.restro.util.BeerListAdapter
import com.sara.restro.viewmodel.BeerListViewModel
import kotlinx.android.synthetic.main.fragment_beer_list.*
import tk.zedlabs.wallportal.util.isConnectedToNetwork

class BeerListFragment : Fragment(), BeerListAdapter.OnImageListener {

    private lateinit var viewAdapter: BeerListAdapter
    private lateinit var viewManager: LinearLayoutManager
    private lateinit var postViewModel: BeerListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_beer_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (requireContext().isConnectedToNetwork()) {
            true -> if (textViewConnectivity.visibility == VISIBLE) textViewConnectivity.visibility =
                GONE
            false -> textViewConnectivity.visibility = VISIBLE
        }

        postViewModel = ViewModelProviders.of(this).get(BeerListViewModel::class.java)
        postViewModel.beerPagedList?.observe(viewLifecycleOwner, Observer { postList ->
            viewAdapter.submitList(postList)
        })

        viewManager = LinearLayoutManager(this.context)
        viewAdapter = BeerListAdapter(this)
        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onImageClick(position: Int) {
        val beerDetail = postViewModel.beerPagedList?.value?.get(position)

        val name = beerDetail?.name ?: "beerName"
        val url = beerDetail?.image_url ?: "url"
        val abv = beerDetail?.abv ?: 0.0f
        val desc = beerDetail?.description ?: "desc"
        val info = beerDetail?.contributed_by ?: "info"

        val action = BeerListFragmentDirections.actionBeerListFragmentToBeerDetailFragment(
            name,
            url,
            abv,
            desc,
            info
        )

        findNavController().navigate(action)
    }
}

