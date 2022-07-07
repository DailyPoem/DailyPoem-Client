package com.patrick.dailypoem.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrick.dailypoem.data.model.Poem
import com.patrick.dailypoem.data.repository.MainRepository
import com.patrick.dailypoem.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _poemResult: MutableLiveData<NetworkResult<Poem>> =
        MutableLiveData(NetworkResult.Loading())
    val poemResult: LiveData<NetworkResult<Poem>> get() = _poemResult

    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        getPoem()
    }

    fun getPoem() {
        _poemResult.value = NetworkResult.Loading()

        viewModelScope.launch {
            _poemResult.value = mainRepository.getPoem()
        }
    }
}
