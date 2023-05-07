package com.example.br_dge_technical_test.featue_tv_shows.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.br_dge_technical_test.featue_tv_shows.core.Resource
import com.example.br_dge_technical_test.featue_tv_shows.domain.GetWordInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Collections.emptyList
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(private val showsUseCase: GetWordInfoUseCase) :
    ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(ShowInfoState())
    val state: State<ShowInfoState> = _state


    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null
    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            showsUseCase(query).onEach {
                when (it) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            it.data ?: emptyList(), isLoading = false
                        )
                        _eventFlow.emit(UIEvent.ShowSnackBar(it.msg ?: "unknown error!"))

                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(

                            it.data ?: emptyList(), isLoading = true
                        )

                    }
                }
            }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }
}