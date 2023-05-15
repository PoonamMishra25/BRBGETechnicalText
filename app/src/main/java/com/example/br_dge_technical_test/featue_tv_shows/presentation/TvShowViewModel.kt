package com.example.br_dge_technical_test.featue_tv_shows.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.br_dge_technical_test.featue_tv_shows.core.Resource
import com.example.br_dge_technical_test.featue_tv_shows.data.local.TVShowsDao
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.TVShowsResponseItem
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
class TvShowViewModel @Inject constructor(
    private val showsUseCase: GetWordInfoUseCase,
    private val dao: TVShowsDao
) :
    ViewModel() {

    private val _state = mutableStateOf(ShowInfoState(kotlin.collections.emptyList(),true,"unknown error!"))
    val state: State<ShowInfoState> = _state


    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null
    fun onSearch(query: String) {
        //_searchQuery.value = query
        // searchJob?.cancel()
        //searchJob =
        viewModelScope.launch {
            // delay(500L)
            showsUseCase(query).onEach {
                when (it) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                             isLoading = false , error = it.msg ?:"unknown error!"
                        )
                        _eventFlow.emit(UIEvent.ShowSnackBar(it.msg ?: "unknown error!"))
                    }

                    is Resource.Loading -> {
                        _state.value = state.value.copy(isLoading = true)
                    }

                    is Resource.Success -> {

                        _state.value = state.value.copy(showItem = it.data!!, isLoading = false)
                    }
                }
            }.launchIn(this)
        }

    }

    fun fetchAlldata(): List<TVShowsResponseItem> {
        var listofTvShowsDb = emptyList<TVShowsResponseItem>()
        viewModelScope.launch {
           listofTvShowsDb = dao.fetchAllShows().map { it.toShow() }
        }
        return listofTvShowsDb
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }

}