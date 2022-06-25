package com.patrick.dailypoem.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.patrick.dailypoem.R
import com.patrick.dailypoem.data.model.Poem
import com.patrick.dailypoem.databinding.ActivityMainBinding
import com.patrick.dailypoem.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) = with(binding) {
        super.onCreate(savedInstanceState)

        buttonCopy.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newPlainText("Copied Poem", textPoemBody.text))

            Snackbar.make(it, "시가 복사되었습니다", Snackbar.LENGTH_SHORT)
                .setAction("확인") {}
                .show()
        }
        buttonShare.setOnClickListener {
            ShareBottomSheet().show(supportFragmentManager, null)
        }

        mainViewModel.getPoem()
        mainViewModel.poemResult.observe(this@MainActivity) { poemResult ->
            handlePoemResult(poemResult)
        }
    }

    private fun handlePoemResult(poemResult: NetworkResult<Poem>) {
        when (poemResult) {
            is NetworkResult.Success -> {
                isLoading(false)
                // TODO: 요청 성공 시 동작 구현 필요
            }
            is NetworkResult.Error -> {
                isLoading(false)
                // TODO: 요청 실패 시 동작 구현 필요
            }
            is NetworkResult.Loading -> {
                isLoading(true)
            }
        }
    }

    private fun isLoading(isLoading: Boolean): Unit = with(binding) {
        if (isLoading) {
            progressBar.visibility = VISIBLE
            buttonCopy.isEnabled = false
            buttonShare.isEnabled = false
            buttonSettings.isEnabled = false
        } else {
            progressBar.visibility = GONE
            buttonCopy.isEnabled = true
            buttonShare.isEnabled = true
            buttonSettings.isEnabled = true
        }
    }
}