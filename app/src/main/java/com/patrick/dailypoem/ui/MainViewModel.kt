package com.patrick.dailypoem.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrick.dailypoem.data.model.PoemData
import com.patrick.dailypoem.data.repository.PoemRepository
import com.patrick.dailypoem.data.repository.RandomNameRepository
import com.patrick.dailypoem.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val poemRepository: PoemRepository
) : ViewModel() {
    private val randomNameRepository = RandomNameRepository()

    private val _poemResult: MutableLiveData<NetworkResult<PoemData>> =
        MutableLiveData(NetworkResult.Loading())
    val poemResult: LiveData<NetworkResult<PoemData>> get() = _poemResult

    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getName(): String = randomNameRepository.getRandomName()

    fun getPoem() {
        _poemResult.value = NetworkResult.Loading()

        viewModelScope.launch {
            _poemResult.value = poemRepository.getPoem()
        }
    }
}