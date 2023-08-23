package com.example.assesmenttest.states

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PixaBayState(val id:Int?,  val largeImage:String?,  val tags:String?,  val userName:String?,  val likes:String?):Parcelable
