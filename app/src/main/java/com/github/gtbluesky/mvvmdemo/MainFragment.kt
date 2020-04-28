package com.github.gtbluesky.mvvmdemo

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.gtbluesky.mvvmdemo.base.BaseMvvmFragment
import com.github.gtbluesky.mvvmdemo.model.bean.HomeStatBean
import com.github.gtbluesky.mvvmdemo.viewmodel.HomeViewModel
import com.github.gtbluesky.mvvmdemo.viewmodel.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_main_layout.*

class MainFragment : BaseMvvmFragment() {

    private val viewModel: HomeViewModel by viewModels {
        ViewModelProvider.provideHomeViewModel()
    }
    private var contentView: View? = null

    companion object {
        fun newInstance(bundle: Bundle? = null): MainFragment {
            val fragment = MainFragment()
            bundle?.let {
                fragment.arguments = it
            }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return contentView?.also {
            (it.parent as? ViewGroup)?.removeView(it)
        } ?: inflater.inflate(R.layout.fragment_main_layout, container, false).also {
            contentView = it
            initView()
            setListener()
        }
    }

    private fun initView() {

    }

    private fun setListener() {

    }

    override fun startObserve() {
        viewModel.statValue.observe(this, Observer {
            showStat(it)
        })

        viewModel.menuValue.observe(this, Observer {
        })
    }

    private fun loadData() {
        viewModel.loadStateInfo()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun showStat(bean: HomeStatBean) {
        tv_report.text = bean.report_total
        tv_project.text = bean.project_total
        tv_task.text = bean.task_total
        tv_news.text = if (TextUtils.isEmpty(bean.news_total)) "0" else bean.news_total
    }
}