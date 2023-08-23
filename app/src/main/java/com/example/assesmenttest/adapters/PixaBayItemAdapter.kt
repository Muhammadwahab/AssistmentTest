package com.example.assesmenttest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.assesmenttest.ImageLoaderUtils
import com.example.assesmenttest.databinding.PixabayAdapterLayoutBinding
import com.example.assesmenttest.diffutils.PixaBayDiffUtilCallback
import com.example.assesmenttest.states.PixaBayState

class PixaBayItemAdapter() :
    PagingDataAdapter<PixaBayState, PixaBayItemAdapter.ViewHolder>(PixaBayDiffUtilCallback()) {

    lateinit var detailScreen: (PixaBayState?) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PixabayAdapterLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pixaState=getItem(position)
        holder.binding.pixaBayState =pixaState

        ImageLoaderUtils.loadImageFromServerCrop(pixaState?.largeImage?:"",holder.binding.imagePlaceHolderId)
        holder.itemView.setOnClickListener {

            detailScreen(pixaState)
        }

        holder.binding.executePendingBindings()
    }


    class ViewHolder( val binding: PixabayAdapterLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}