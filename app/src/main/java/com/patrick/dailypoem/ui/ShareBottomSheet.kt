package com.patrick.dailypoem.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.patrick.dailypoem.R
import com.patrick.dailypoem.databinding.FragmentShareBottomSheetBinding


class ShareBottomSheet : BottomSheetDialogFragment() {
    private val binding: FragmentShareBottomSheetBinding by lazy { FragmentShareBottomSheetBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = with(binding) {
        buttonShareKakaoTalk.setOnClickListener { dismiss() }

        return root
    }
}