package com.example.assesmenttest.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assesmenttest.Constants
import com.example.assesmenttest.MainActivity
import com.example.assesmenttest.R
import com.example.assesmenttest.adapters.HeaderFooterAdapter
import com.example.assesmenttest.adapters.PixaBayItemAdapter
import com.example.assesmenttest.databinding.FragmentHomeBinding
import com.example.assesmenttest.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private lateinit var concatenate: ConcatAdapter
    private lateinit var pixaBayItemAdapter: PixaBayItemAdapter


    private lateinit var homeBinding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setList()
        (activity as MainActivity).showLoader()
        lifecycleScope.launch {
            homeViewModel.flow.collect { pagingData ->
                Log.e(HomeFragment::class.java.simpleName, "collect latest")

                (activity as MainActivity).hideLoader()
                pixaBayItemAdapter.submitData(pagingData)
            }

        }
    }


    private fun setList(
    ) {
         pixaBayItemAdapter = PixaBayItemAdapter()
        pixaBayItemAdapter.detailScreen={

            findNavController().navigate(R.id.detailFragment, args = Bundle().apply {
                putParcelable(Constants.PIXABAY_STATE,it)
            }

            )

        }

        concatenate = pixaBayItemAdapter.withLoadStateFooter(HeaderFooterAdapter().apply {
            retry = {
                pixaBayItemAdapter.retry()
            }
        })



        homeBinding.pixabayList.layoutManager = LinearLayoutManager(requireContext())
        homeBinding.pixabayList.adapter = concatenate

    }


}