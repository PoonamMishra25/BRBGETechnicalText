package com.example.br_dge_technical_test.featue_tv_shows.data.remote.model


data class TVShowsResponseItem(
    val score: Double = 0.0,
    val show: Show = Show()
){
//    fun toShowInfo() : TvShowsEntities =
//        TvShowsEntities(shows = show,
//        score = score)
}