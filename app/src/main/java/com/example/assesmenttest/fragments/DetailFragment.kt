package com.example.assesmenttest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assesmenttest.Constants
import com.example.assesmenttest.ImageLoaderUtils
import com.example.assesmenttest.R
import com.example.assesmenttest.databinding.FragmentDetailBinding
import com.example.assesmenttest.databinding.FragmentHomeBinding
import com.example.assesmenttest.states.PixaBayState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {


    private lateinit var fragmentDetailBinding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)

        return fragmentDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pixaBayState = arguments?.getParcelable<PixaBayState>(Constants.PIXABAY_STATE)
        ImageLoaderUtils.loadImageFromServerCrop(
            pixaBayState?.largeImage ?: "", fragmentDetailBinding.largeImage
        )
    }
}