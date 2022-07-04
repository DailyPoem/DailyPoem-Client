package com.patrick.dailypoem.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
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
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }
    private val mainViewModel: MainViewModel by viewModels()
    private val notFoundMessage = "메세지를 찾을 수 없습니다."

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

    private fun intentSendPoem(message: String?) {
        binding.buttonShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, message)
            val shareIntent: Intent = Intent.createChooser(intent, "share")
            startActivity(shareIntent)
        }
    }

    private fun handlePoemResult(poemResult: NetworkResult<PoemData>) {
        when (poemResult) {
            is NetworkResult.Success -> {
                val message = poemResult.data?.data?.epitagram ?: notFoundMessage
                mainViewModel.isLoading.value = false
                binding.textPoemBody.text = message
                intentSendPoem(message)
                Timber.d("${poemResult.data?.data}")
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