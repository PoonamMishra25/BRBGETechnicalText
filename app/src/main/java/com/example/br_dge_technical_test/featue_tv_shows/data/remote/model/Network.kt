package com.example.br_dge_technical_test.featue_tv_shows.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Network(
    val country: Country = Country(),
    val id: Int = 0,
    val name: String = "",
    val officialSite: String = ""
):Parcelable