package com.patrick.dailypoem.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.patrick.dailypoem.R
import com.patrick.dailypoem.data.model.Poem
import com.patrick.dailypoem.databinding.ActivityMainBinding
import com.patrick.dailypoem.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) = with(binding) {
        super.onCreate(savedInstanceState)
        lifecycleOwner = this@MainActivity
        activity = this@MainActivity
        viewModel = mainViewModel

        mainViewModel.poemResult.observe(this@MainActivity) { poemResult ->
            handlePoemResult(poemResult)
        }
    }

    private fun handlePoemResult(poemResult: NetworkResult<Poem>) {
        mainViewModel.isLoading.value = when (poemResult) {
            is NetworkResult.Success -> {
                setPoemData(poemResult.data!!)
                false
            }
            is NetworkResult.Error -> {
                showErrorMessage(poemResult.message!!)
                false
            }
            is NetworkResult.Loading -> {
                true
            }
        }
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(binding.root, "로딩 실패: $message", Snackbar.LENGTH_SHORT).show()
    }

    private fun setPoemData(poem: Poem) = with(binding) {
        ivRandomImage.load(poem.imageUrl) {
            crossfade(true)
        }
        textPoemBody.text = "\"${poem.poem}\""
        textTeller.text = "- ${poem.teller} -"
    }

    fun openShareDialog() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, binding.textPoemBody.text)
        val shareIntent: Intent = Intent.createChooser(intent, "share")
        startActivity(shareIntent)
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
            .show()
    }
}
