package com.patrick.dailypoem.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrick.dailypoem.data.model.PoemData
import com.patrick.dailypoem.data.model.random.RandomImage
import com.patrick.dailypoem.data.repository.PoemRepository
import com.patrick.dailypoem.data.repository.random.RandomImageRepository
import com.patrick.dailypoem.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val poemRepository: PoemRepository,
    private val randomImageRepository: RandomImageRepository
) : ViewModel() {
    private val _poemResult: MutableLiveData<NetworkResult<PoemData>> =
        MutableLiveData(NetworkResult.Loading())
    val poemResult: LiveData<NetworkResult<PoemData>> get() = _poemResult

    private val _imageResult: MutableLiveData<NetworkResult<RandomImage>> =
        MutableLiveData(NetworkResult.Loading())
    val imageResult: LiveData<NetworkResult<RandomImage>> get() = _imageResult

    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getPoem() {
        _poemResult.value = NetworkResult.Loading()

        viewModelScope.launch {
            _poemResult.value = poemRepository.getPoem()
        }
    }
    fun getImage() {
        _imageResult.value = NetworkResult.Loading()

        viewModelScope.launch {
            _imageResult.value = randomImageRepository.getRandomImage()
        }
    }
}