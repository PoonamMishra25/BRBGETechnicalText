package com.example.br_dge_technical_test.featue_tv_shows.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TvShowsEntities::class],
version = 1)
//@TypeConverters(Converters::class)

abstract class TVShowsDatabase :RoomDatabase() {
    abstract fun tvShowsDao() : TVShowsDao
}