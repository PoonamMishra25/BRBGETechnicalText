package com.example.br_dge_technical_test

import com.example.br_dge_technical_test.featue_tv_shows.core.Resource
import com.example.br_dge_technical_test.featue_tv_shows.data.local.TVShowsDao
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.TVShowsResponseItem
import com.example.br_dge_technical_test.featue_tv_shows.data.repository.TvShowsRepository
import com.example.br_dge_technical_test.featue_tv_shows.domain.GetWordInfoUseCase
import com.example.br_dge_technical_test.featue_tv_shows.presentation.TvShowViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TVShowsViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val repo: TvShowsRepository = mock()
    private val useCase: GetWordInfoUseCase = mock()
    private val dao: TVShowsDao = mock()
    private lateinit var viewModel: TvShowViewModel
    private val data = listOf<TVShowsResponseItem>()

    @Before
    fun setup() {

        viewModel = TvShowViewModel(useCase, dao)
    }

    @After
    fun tear() {

    }

    @Test
    fun showALoaderInitially() = runTest {
        Assert.assertEquals(true, viewModel.state.value.isLoading)
    }

    @Test
    fun validateSuccessStateDataIsStoredOnShowsList() = runTest {

        whenever(repo.getTvShows(any())).thenReturn(
            flowOf(
                Resource.Loading(),
                Resource.Success(data)
            )
        )
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()

        Assert.assertEquals(true, viewModel.state.value.isLoading)
        Assert.assertEquals(data, viewModel.state.value.showItem)
    }


    @Test
    fun validateErrorStateWhenWeReceiveErrorFromRepository() = runTest {
        whenever(repo.getTvShows("")).thenReturn(
            flow {
                emit(Resource.Loading())
                emit(Resource.Error("unknown error!"))
            }
        )
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals("unknown error!", viewModel.state.value.error)
    }


}