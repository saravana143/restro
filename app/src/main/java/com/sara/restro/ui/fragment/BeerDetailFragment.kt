package com.sara.restro.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sara.restro.R
import kotlinx.android.synthetic.main.fragment_beer_detail.*

class BeerDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_beer_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        description.text = arguments?.getString("beerType")
        abv.text = ("ABV - " + arguments?.getFloat("abv").toString())

        Glide.with(imageViewItem.context)
            .load(arguments?.getString("imgUrl"))
            .placeholder(R.drawable.corner_bg)
            .apply(RequestOptions().override(100, 100))
            .into(imageViewItem)

        desc_detail.text = arguments?.getString("desc")
        info_detail.text = arguments?.getString("info")

    }

}

