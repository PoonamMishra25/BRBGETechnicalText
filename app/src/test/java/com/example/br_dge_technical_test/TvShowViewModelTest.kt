package com.example.br_dge_technical_test

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.br_dge_technical_test.featue_tv_shows.core.Resource
import com.example.br_dge_technical_test.featue_tv_shows.data.repository.TvShowsRepository
import com.example.br_dge_technical_test.featue_tv_shows.domain.GetWordInfoUseCase
import com.example.br_dge_technical_test.featue_tv_shows.presentation.ShowInfoState
import com.example.br_dge_technical_test.featue_tv_shows.presentation.TvShowViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {
    private val mockTvShowsRepo = mockk<TvShowsRepository>()
    private lateinit var mockShowUseCase: GetWordInfoUseCase
    private lateinit var tvViewModel: TvShowViewModel
    private val showInfoState = mockk<ShowInfoState>()

    @Before
    fun setUp() {
        mockShowUseCase = GetWordInfoUseCase(mockTvShowsRepo)
        tvViewModel = TvShowViewModel(mockShowUseCase)
    }

    @Test
    fun initialStateIsLoading() = runBlocking{
        every { tvViewModel.state.value } returns showInfoState
        Assert.assertEquals(tvViewModel.state, true)
    }
}