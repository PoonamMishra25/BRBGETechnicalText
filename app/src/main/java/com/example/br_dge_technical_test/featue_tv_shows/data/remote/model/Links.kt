package com.example.br_dge_technical_test.featue_tv_shows.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Links(
    val previousepisode: Previousepisode = Previousepisode(),
    val self: Self = Self()
):Parcelable