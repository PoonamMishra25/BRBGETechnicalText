package com.example.br_dge_technical_test

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.br_dge_technical_test.featue_tv_shows.data.local.TVShowsDao
import com.example.br_dge_technical_test.featue_tv_shows.data.local.TVShowsDatabase
import com.example.br_dge_technical_test.featue_tv_shows.data.local.TvShowsEntities
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TVShowDBTest {
    private lateinit var database: TVShowsDatabase
    lateinit var dao: TVShowsDao
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TVShowsDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.tvShowsDao()
    }
    @Test
    fun insertShows()= runBlocking {
        val tvShowsEntities = listOf(
            TvShowsEntities(
                "gener",
                "image",
                "english",
                "dragon",
                5.0,
                "Still going on",
                "Summary",
                "anime",
                "url",
                1
            )
        )
        dao.insertTVShows(tvShowsEntities)
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()
        val result = dao.fetchAllShows()
        mainCoroutineRule.dispatcher.scheduler.advanceUntilIdle()
        Assert.assertEquals(1, result.size)

    }
    @After
    fun tearDown(){
        database.close()
    }
}