package com.example.assesmenttest.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assesmenttest.databinding.PagingLoaderLayoutBinding

class HeaderFooterAdapter() :
    LoadStateAdapter<HeaderFooterAdapter.LoadStateViewHolder>() {


    lateinit var retry: () -> Unit

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {


        holder.binding.searchAgainId.setOnClickListener {
            retry()
        }
        when (loadState) {
            LoadState.Loading -> {
                Log.e(HeaderFooterAdapter::class.java.simpleName, "loading")
                holder.binding.loader.show()
            }
            is LoadState.Error -> {
                Log.e(HeaderFooterAdapter::class.java.simpleName, "Error")
                holder.binding.loader.visibility= View.GONE
                holder.binding.searchAgainId.visibility= View.VISIBLE
            }
            else -> {
                holder.binding.loader.visibility= View.GONE
                Log.e(HeaderFooterAdapter::class.java.simpleName, "loading remove")

            }
        }//hide the view

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PagingLoaderLayoutBinding.inflate(inflater, parent, false)
        return LoadStateViewHolder(binding)
    }

    class LoadStateViewHolder(val binding: PagingLoaderLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}