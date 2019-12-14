package com.github.gtbluesky.mvvmdemo

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.github.gtbluesky.mvvmdemo.base.BaseMvvmActivity

class MainActivity : BaseMvvmActivity() {

    companion object {
        private const val FRAGMENT_MAIN = "FRAGMENT_MAIN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setWindowFlag()
        setContentView(R.layout.activity_main_layout)
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fl_container,
                MainFragment.newInstance(),
                FRAGMENT_MAIN
            ).addToBackStack(FRAGMENT_MAIN)
            .commit()
    }

    private fun setWindowFlag() {
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            attributes = attributes.apply {
                systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
            }
        }
    }

    override fun onBackPressed() {
        supportFragmentManager.backStackEntryCount.let {
            when {
                it > 1 -> supportFragmentManager.popBackStack()
                it == 1 -> {
                    finish()
                }
                else -> super.onBackPressed()
            }
        }

    }

    override fun startObserve() {

    }
}