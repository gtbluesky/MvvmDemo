package com.github.gtbluesky.mvvmdemo.base

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseMvvmFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startObserve()
    }

    abstract fun startObserve()
}