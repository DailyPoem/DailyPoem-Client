package com.patrick.dailypoem.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.patrick.dailypoem.R
import com.patrick.dailypoem.data.model.PoemData
import com.patrick.dailypoem.data.model.random.RandomImage
import com.patrick.dailypoem.databinding.ActivityMainBinding
import com.patrick.dailypoem.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

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
        mainViewModel.getImage()
        mainViewModel.poemResult.observe(this@MainActivity) { poemResult ->
            handlePoemResult(poemResult)
        }
        mainViewModel.imageResult.observe(this@MainActivity) { imageResult ->
            handleImageResult(imageResult)
        }
    }

    fun onRefresh() {
        mainViewModel.getPoem()
        mainViewModel.getImage()
    }

    fun copyPoemToClipboard() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val poem = binding.textPoemBody.text

        clipboard.setPrimaryClip(ClipData.newPlainText("Copied Poem", poem))

        Snackbar.make(binding.root, "시가 복사되었습니다", Snackbar.LENGTH_SHORT)
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
                val message = poemResult.data!!.data.epitagram
                mainViewModel.isLoading.value = false
                binding.textPoemBody.text = message
                intentSendPoem(message)
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

    private fun handleImageResult(poemResult: NetworkResult<RandomImage>) {
        when (poemResult) {
            is NetworkResult.Success -> {
                // 생성된 이미지를 랜덤하게 보여줍니다.
                val imageUrl = poemResult.data!!.results.random().urls.raw
                mainViewModel.isLoading.value = false
                Glide.with(this).load(imageUrl).into(binding.ivRandomImage)
                Log.d("TAG", "handleImageResult: ${poemResult.data}")
            }
            is NetworkResult.Error -> {
                mainViewModel.isLoading.value = false
                Log.d("TAG", "handleImageResult: ${poemResult.message}")
            }
            is NetworkResult.Loading -> {
                mainViewModel.isLoading.value = false
            }
        }
    }
}