package com.patrick.dailypoem.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.patrick.dailypoem.R
import com.patrick.dailypoem.data.model.PoemData
import com.patrick.dailypoem.databinding.ActivityMainBinding
import com.patrick.dailypoem.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) = with(binding) {
        super.onCreate(savedInstanceState)
        lifecycleOwner = this@MainActivity
        activity = this@MainActivity
        viewModel = mainViewModel

        mainViewModel.getPoem()
        mainViewModel.poemResult.observe(this@MainActivity) { poemResult ->
            handlePoemResult(poemResult)
        }
    }

    fun onRefresh() {
        mainViewModel.getPoem()
    }

    fun copyPoemToClipboard() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val poem = binding.textPoemBody.text

        clipboard.setPrimaryClip(ClipData.newPlainText("Copied Poem", poem))

        Snackbar.make(binding.root, "시가 복사되었습니다", Snackbar.LENGTH_SHORT)
            .setAction("확인") {}
            .setAction("확인") {}
            .show()
    }

    fun showShareBottomSheet() {
        ShareBottomSheet().show(supportFragmentManager, null)
    }

    private fun handlePoemResult(poemResult: NetworkResult<PoemData>) {
        when (poemResult) {
            is NetworkResult.Success -> {
                mainViewModel.isLoading.value = false
                io.github.jisungbin.logeukes.logeukes { poemResult.data }
                binding.textPoemBody.text = poemResult.data?.data?.epitagram ?: "메세지를 찾을 수 없습니다."
                // TODO: 요청 성공 시 동작 구현 필요
            }
            is NetworkResult.Error -> {
                mainViewModel.isLoading.value = false
                // TODO: 요청 실패 시 동작 구현 필요
            }
            is NetworkResult.Loading -> {
                mainViewModel.isLoading.value = false
            }
        }
    }
}