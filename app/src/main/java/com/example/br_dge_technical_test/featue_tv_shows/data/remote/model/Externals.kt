package com.example.br_dge_technical_test.featue_tv_shows.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Externals(
    val imdb: String = "",
    val thetvdb: Int = 0,
    val tvrage: Int = 0
):Parcelable