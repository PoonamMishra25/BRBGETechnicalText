package com.example.br_dge_technical_test

import app.cash.turbine.test
import com.example.br_dge_technical_test.featue_tv_shows.data.local.TVShowsDao
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.TVShowsService
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.TVShowsResponseItem
import com.example.br_dge_technical_test.featue_tv_shows.data.repository.TvShowsRepository
import com.example.br_dge_technical_test.featue_tv_shows.data.repository.TvShowsRepositoryImp
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowsRepositoryTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var repository: TvShowsRepository
    private var apiService = mockk<TVShowsService>(relaxed = true)
    private val dao = mockk<TVShowsDao>(relaxed = true)
    private val data = mockk<List<TVShowsResponseItem>>(relaxed = true)

    @Before
    fun setUp() {
        repository = TvShowsRepositoryImp(apiService, dao)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun returnSuccessWhenGotDataFromBackend() = runTest {
        coEvery {
            apiService.fetchAllShowsList("search")
        } coAnswers {data}

        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()
val result =  repository.getTvShows("search").firstOrNull()
     //   Assert.assertEquals(data,result)
        repository.getTvShows("search").test {
            Assert.assertEquals(data, awaitItem().data)
            awaitComplete()
        }


    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun returnErrorMessageDuringException() = runTest {

        coEvery { apiService.fetchAllShowsList(any()) }.throws(Exception("Something went wrong"))

        val result = repository.getTvShows("s")
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()
     //   assertThrows<Exception> { apiService.fetchAllShowsList(null) }
//        result?.let{
//            Assert.assertEquals("Something went wrong", it.first().msg)
//        }

        result.test {
            val error = awaitItem()
            Assert.assertEquals("Something went wrong", error.msg)
            awaitComplete()
        }
    }


}