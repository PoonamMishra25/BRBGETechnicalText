package com.example.br_dge_technical_test.featue_tv_shows.data.repository

import com.example.br_dge_technical_test.featue_tv_shows.core.Resource
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.TVShowsService
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.TVShowsResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface TvShowsRepository {
    fun getTvShows(searchQuery: String): Flow<Resource<List<TVShowsResponseItem>>>
}

class TvShowsRepositoryImp @Inject constructor(
    private val api: TVShowsService,
    //private val dao: TVShowsDao
) : TvShowsRepository {

    override fun getTvShows(searchQuery: String): Flow<Resource<List<TVShowsResponseItem>>> =
        flow {
            emit(Resource.Loading())
           // val tvShowInfo = dao.getShowInfo(searchQuery).map { it.toTvShowsResponse() }
           // emit(Resource.Loading())
            try {
                val remoteShowInfo = api.fetchAllShowsList(searchQuery = searchQuery)
                emit(Resource.Success(remoteShowInfo))
//                dao.deleteShowsInfos(searchQuery)
//                dao.insertTVShows(remoteShowInfo.map { it.toShowInfo() })

            } catch (e: IOException) {
                emit(Resource.Error("${e.message}"))

            } catch (e: HttpException) {
                emit(Resource.Error("${e.message}"))
            }

            //val newWordInfo = dao.getShowInfo(searchQuery).map { it.toTvShowsResponse() }
            //emit(Resource.Success(newWordInfo))

        }

}

