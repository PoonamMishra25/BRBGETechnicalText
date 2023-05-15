package com.example.br_dge_technical_test.featue_tv_shows.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TVShowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTVShows(tvShows: List<TvShowsEntities>)

    @Query("Delete from _tv_shows where name IN(:name)")
    suspend fun deleteShowsInfos(name: String)

    @Query("Select * from _tv_shows where name like '%' || :name || '%'")
    suspend fun getShowInfo(name: String): List<TvShowsEntities>
    @Query("Select * from _tv_shows")
    suspend fun fetchAllShows(): List<TvShowsEntities>
}