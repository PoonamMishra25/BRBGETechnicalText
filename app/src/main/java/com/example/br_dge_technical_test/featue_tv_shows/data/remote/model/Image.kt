package com.example.br_dge_technical_test.featue_tv_shows.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val medium: String = "",
    val original: String = "https://kodi.tv/images/addons/matrix/metadata.tvmaze/resources/icon.png"
):Parcelable