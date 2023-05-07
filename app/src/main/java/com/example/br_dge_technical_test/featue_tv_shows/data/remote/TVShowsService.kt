package com.example.br_dge_technical_test.featue_tv_shows.data.remote

import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.TVShowsResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowsService {
    //https://api.tvmaze.com/search/shows?q=girls
    @GET("search/shows?")
   suspend fun fetchAllShowsList(@Query("q") searchQuery: String):
        List<TVShowsResponseItem>

   companion object{
       const val BASE_URL="https://api.tvmaze.com/"
   }
}