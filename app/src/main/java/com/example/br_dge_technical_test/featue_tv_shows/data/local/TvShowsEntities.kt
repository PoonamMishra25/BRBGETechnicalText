package com.example.br_dge_technical_test.featue_tv_shows.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.Image
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.Rating
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.Show
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.TVShowsResponseItem

@Entity(tableName = "_tv_shows")
data class TvShowsEntities(
    val genres: String = "",
    val image: String = "",
    val language: String = "",
    val name: String = "",
    val rating: Double = 0.0,
    val status: String = "",
    val summary: String = "",
    val type: String = "",
    val url: String = "",
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) {

    fun toShow(): TVShowsResponseItem =
        TVShowsResponseItem(
            Show(
                genres = listOf(genres),
                image = Image("", image),
                language = language,
                name = name,
                rating = Rating(rating),
                status = status,
                summary,
                type,
                url
            )
        )
}