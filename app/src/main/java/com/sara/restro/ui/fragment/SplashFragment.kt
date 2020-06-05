package com.sara.restro.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sara.restro.R
import java.util.*
import kotlin.concurrent.schedule

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_splash, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timer("SettingUp", false).schedule(DELAY) {
            val action = SplashFragmentDirections.actionSplashFragmentToProductListFragment()
            findNavController().navigate(action)
        }
    }

    companion object {
        const val DELAY: Long = 4000
    }

}

