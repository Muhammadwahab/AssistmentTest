package com.example.assesmenttest.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseActivity :AppCompatActivity() {


    fun showLoader(){
        Toast.makeText(applicationContext,"show loader",Toast.LENGTH_LONG).show()

    }

    fun hideLoader(){
        Toast.makeText(applicationContext,"hide loader",Toast.LENGTH_LONG).show()

    }
}