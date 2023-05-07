package com.example.br_dge_technical_test.featue_tv_shows.data.remote.model

data class Show(
    val _links: Links = Links(),
    val averageRuntime: Int = 0,
    val dvdCountry: Any? = null,
    val ended: String = "",
    val externals: Externals = Externals(),
    val genres: List<String> = listOf(),
    val id: Int = 0,
    val image: Image = Image(),
    val language: String = "",
    val name: String = "",
    val network: Network = Network(),
    val officialSite: Any? = null,
    val premiered: String = "",
    val rating: Rating = Rating(),
    val runtime: Int = 0,
    val schedule: Schedule = Schedule(),
    val status: String = "",
    val summary: String = "",
    val type: String = "",
    val updated: Int = 0,
    val url: String = "",
    val webChannel: Any? = null,
    val weight: Int = 0
)