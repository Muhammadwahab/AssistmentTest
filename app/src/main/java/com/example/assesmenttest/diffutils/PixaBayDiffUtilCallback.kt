package com.example.assesmenttest.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.assesmenttest.states.PixaBayState

class PixaBayDiffUtilCallback : DiffUtil.ItemCallback<PixaBayState>() {
    override fun areItemsTheSame(oldItem: PixaBayState, newItem: PixaBayState): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id    }

    override fun areContentsTheSame(oldItem: PixaBayState, newItem: PixaBayState): Boolean {
        return oldItem == newItem
    }

}
