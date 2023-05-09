package com.example.br_dge_technical_test.featue_tv_shows.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Show(
    val genres: List<String> = listOf(),
    val image: Image = Image(),
    val language: String = "",
    val name: String = "",
    val rating: Rating = Rating(),
    val status: String = "",
    val summary: String = "",
    val type: String = "",
    val url: String = "",
):Parcelable