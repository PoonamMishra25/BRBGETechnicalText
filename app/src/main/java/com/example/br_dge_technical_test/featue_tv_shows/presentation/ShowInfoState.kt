package com.example.br_dge_technical_test.featue_tv_shows.presentation

import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.TVShowsResponseItem

data class ShowInfoState(
    val showItem:List<TVShowsResponseItem>,
    var isLoading:Boolean,
    val error:String
)
