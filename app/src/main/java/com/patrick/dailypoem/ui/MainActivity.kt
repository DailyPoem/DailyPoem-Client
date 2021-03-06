package com.patrick.dailypoem.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import coil.load
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.material.snackbar.Snackbar
import com.patrick.dailypoem.R
import com.patrick.dailypoem.data.model.Poem
import com.patrick.dailypoem.databinding.ActivityMainBinding
import com.patrick.dailypoem.util.CaptureManager
import com.patrick.dailypoem.util.Constants.TAG
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

        MobileAds.initialize(this@MainActivity) {}
        adView.loadAd(AdRequest.Builder().build())

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
                Log.d(TAG, "handlePoemResult: Error occurred with = ${poemResult.message}")
                false
            }
            is NetworkResult.Loading -> {
                true
            }
        }
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(binding.root, "?????? ??????: $message", Snackbar.LENGTH_SHORT).show()
    }

    private fun setPoemData(poem: Poem) = with(binding) {
        ivRandomImage.load(poem.image?.urls?.small ?: R.drawable.placeholder) {
            crossfade(true)
        }
        textAttribution.text = "Photo by ${poem.image?.user?.name ?: "????????? ??????..."} on Unsplash"
        textPoemBody.text = "\"${poem.poem}\""
        textTeller.text = "- ${poem.teller} -"
    }

    fun onRefresh() {
        mainViewModel.getPoem()
    }

    fun copyPoemToClipboard() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val poem = "${binding.textPoemBody.text} ${binding.textTeller.text}"

        clipboard.setPrimaryClip(ClipData.newPlainText("Copied Poem", poem))

        Snackbar.make(binding.root, "?????? ?????????????????????", Snackbar.LENGTH_SHORT)
            .setAction("??????") {}
            .show()
    }

    fun openSettingsDialog() {
        // TODO: ??? ??????...
    }

    fun openShareDialog() {
        CaptureManager.capture(binding.poemWrap, this) { uri ->
            val intent = Intent(Intent.ACTION_SEND)

            intent.type = "image/png"
            intent.putExtra(Intent.EXTRA_STREAM, uri)

            startActivity(Intent.createChooser(intent, "????????????"))
        }
    }

    fun openImageSource() = mainViewModel.poemResult.value?.data?.image?.user?.links?.html?.let {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(it)
        startActivity(intent)
    }
}
