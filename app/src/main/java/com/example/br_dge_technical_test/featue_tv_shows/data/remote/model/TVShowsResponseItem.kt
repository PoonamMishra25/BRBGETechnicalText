package com.example.br_dge_technical_test.featue_tv_shows.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TVShowsResponseItem(
    val score: Double = 0.0,
    val show: Show = Show()
):Parcelable{
//    fun toShowInfo():TvShowsEntities =
//        TvShowsEntities(shows = show)
}


//@Parcelize
//data class TVShowsResponse(
//    val listOfTvShows: List<TVShowsResponseItem>
//):Parcelable{
//    fun toShowInfo() : TvShowsEntities =
//        TvShowsEntities(shows = listOfTvShows)
//
//}