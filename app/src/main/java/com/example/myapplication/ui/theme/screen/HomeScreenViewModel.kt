package com.example.myapplication.ui.theme.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.GenericUiState
import com.example.myapplication.data.models.PhotoListResponseItem
import com.example.myapplication.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> get() = _uiState

    fun loadPhotoItems() {
        _uiState.update {
            it.copy(result = GenericUiState.Loading)
        }
        viewModelScope.launch {
            try {

                val response = dataRepository.getPhotoList()
                if (response.isSuccessful && response.code() == 200) {
                    _uiState.update {
                        it.copy(result = GenericUiState.Success(response.body() ?: emptyList()))
                    }
                } else {
                    _uiState.update {
                        it.copy(result = GenericUiState.Failure("Something Went Wrong"))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.update {
                    it.copy(result = GenericUiState.Failure("Something Went Wrong"))
                }

            }
        }
    }
}

data class HomeScreenUiState(

    val result: GenericUiState<List<PhotoListResponseItem>> = GenericUiState.Loading,
)
