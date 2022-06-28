package com.patrick.dailypoem.ui

import android.content.* // ktlint-disable no-wildcard-imports
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.link.LinkClient
import com.kakao.sdk.link.WebSharerClient
import com.kakao.sdk.template.model.* // ktlint-disable no-wildcard-imports
import com.patrick.dailypoem.R
import com.patrick.dailypoem.data.model.Poem
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

    override fun onCreate(savedInstanceState: Bundle?) = with(binding) {
        super.onCreate(savedInstanceState)
        lifecycleOwner = this@MainActivity
        activity = this@MainActivity
        viewModel = mainViewModel

//        mainViewModel.getPoem()
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
//        ShareBottomSheet().show(supportFragmentManager, null)
        sendKakaoLink()
//        val intent = Intent(Intent.ACTION_SEND)
//        intent.type = "text/plain"
//        intent.putExtra(Intent.EXTRA_TEXT, "poem")
//        val shareIntent: Intent = Intent.createChooser(intent, "share")
//        startActivity(shareIntent)
    }

    // todo 안의 내용 변경해야함
    private fun defaultFeed() = FeedTemplate(
        content = Content(
            title = "김보석이 지은 시입니다.!",
            imageUrl = "http://mud-kage.kakao.co.kr/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png",
            link = Link(
                webUrl = "https://developers.kakao.com",
                mobileWebUrl = "https://developers.kakao.com"
            )
        ),
        social = Social(
            likeCount = 286,
            commentCount = 45,
            sharedCount = 845
        ),
        buttons = listOf(

            Button(
                "앱으로 보기",
                Link(
                    androidExecParams = mapOf("key1" to "value1", "key2" to "value2"),
                    iosExecParams = mapOf("key1" to "value1", "key2" to "value2")
                )
            )
        )
    )

    fun sendKakaoLink() {
        if (LinkClient.instance.isKakaoLinkAvailable(this)) {
            // 카카오톡으로 카카오링크 공유 가능
            LinkClient.instance.defaultTemplate(
                this,
                defaultFeed()
            ) { linkResult, error ->
                if (error != null) {
                    Timber.e(error, "카카오링크 보내기 실패")
                } else if (linkResult != null) {
                    Timber.d("카카오링크 보내기 성공 ${linkResult.intent}")
                    startActivity(linkResult.intent)

                    // 카카오링크 보내기에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
                    Timber.w("Warning Msg: ${linkResult.warningMsg}")
                    Timber.w("Argument Msg: ${linkResult.argumentMsg}")
                }
            }
        } else {
            // 카카오톡 미설치: 웹 공유 사용 권장
            // 웹 공유 예시 코드
            val sharerUrl = WebSharerClient.instance.defaultTemplateUri(defaultFeed())

            // CustomTabs으로 웹 브라우저 열기

            // 1. CustomTabs으로 Chrome 브라우저 열기
            try {
                KakaoCustomTabsClient.openWithDefault(this, sharerUrl)
            } catch (e: UnsupportedOperationException) {
                // Chrome 브라우저가 없을 때 예외처리
            }

            // 2. CustomTabs으로 디바이스 기본 브라우저 열기
            try {
                KakaoCustomTabsClient.open(this, sharerUrl)
            } catch (e: ActivityNotFoundException) {
                // 인터넷 브라우저가 없을 때 예외처리
            }
        }
    }

    private fun handlePoemResult(poemResult: NetworkResult<Poem>) {
        when (poemResult) {
            is NetworkResult.Success -> {
                mainViewModel.isLoading.value = false
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