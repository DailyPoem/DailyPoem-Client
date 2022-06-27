package com.patrick.dailypoem.ui

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

object MainActivityBinding {
    @JvmStatic
    @BindingAdapter("onRefresh")
    fun onRefresh(swipeRefreshLayout: SwipeRefreshLayout, onRefresh: () -> Unit) {
        swipeRefreshLayout.setOnRefreshListener {
            onRefresh()
        }
    }

    @JvmStatic
    @BindingAdapter("isLoading")
    fun isLoading(swipeRefreshLayout: SwipeRefreshLayout, isLoading: Boolean) {
        swipeRefreshLayout.isRefreshing = isLoading
    }
}