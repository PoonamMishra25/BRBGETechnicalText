package com.example.br_dge_technical_test.featue_tv_shows.data.remote.model

import android.os.Parcelable
import com.example.br_dge_technical_test.featue_tv_shows.data.local.TvShowsEntities
import kotlinx.parcelize.Parcelize
import java.lang.NullPointerException

@Parcelize
data class TVShowsResponseItem(
    val show: Show = Show()
) : Parcelable {
    fun toShowInfo(): TvShowsEntities =
        TvShowsEntities(
            genres = show.genres.toString(),
            image = show.image.original,
            language = show.language,
            name = show.name,
            rating = show.rating.average,
            status = show.status,
            summary =show.summary,
            type = show.type,
            url = show.url
        )

}


//@Parcelize
//data class TVShowsResponse(
//    val listOfTvShows: List<TVShowsResponseItem>
//):Parcelable{
//    fun toShowInfo() : TvShowsEntities =
//        TvShowsEntities(shows = listOfTvShows)
//
//}