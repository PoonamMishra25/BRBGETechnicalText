package com.example.br_dge_technical_test.featue_tv_shows.domain

import com.example.br_dge_technical_test.featue_tv_shows.core.Resource
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.TVShowsResponseItem
import com.example.br_dge_technical_test.featue_tv_shows.data.repository.TvShowsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoUseCase(private val repository: TvShowsRepository) {

    operator fun invoke(searchQuery: String): Flow<Resource<List<TVShowsResponseItem>>> {
        if (searchQuery.isEmpty()) {
            return flow { }
        }
        return repository.getTvShows(searchQuery)
    }
}