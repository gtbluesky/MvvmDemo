package com.github.gtbluesky.mvvmdemo.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseMvvmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startObserve()
    }

    abstract fun startObserve()
}