package com.example.br_dge_technical_test.featue_tv_shows.di

import com.example.br_dge_technical_test.featue_tv_shows.data.remote.TVShowsService
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.TVShowsService.Companion.BASE_URL
import com.example.br_dge_technical_test.featue_tv_shows.data.repository.TvShowsRepository
import com.example.br_dge_technical_test.featue_tv_shows.data.repository.TvShowsRepositoryImp
import com.example.br_dge_technical_test.featue_tv_shows.domain.GetWordInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ShowInfoModule {

    @Provides
    @Singleton
    fun provideApiService(): TVShowsService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TVShowsService::class.java)

//    @Provides
//    @Singleton
//    fun provideRepository(api: TVShowsService,dao:TVShowsDao): TvShowsRepository {
//        return TvShowsRepositoryImp(api, dao)
//    }
    @Provides
    @Singleton
    fun provideRepository(api: TVShowsService): TvShowsRepository {
        return TvShowsRepositoryImp(api)
    }
    @Provides
    @Singleton
    fun provideShowInfoUseCase(repo: TvShowsRepository, ) = GetWordInfoUseCase(repo)

//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context):TVShowsDatabase{
//        return Room.databaseBuilder(context,
//            TVShowsDatabase::class.java,"TVInfoDB")
//            .addTypeConverter(Converters(GsonParser(Gson())))
//            .build()
//    }

//    @Provides
//    @Singleton
//    fun provideDao(database:TVShowsDatabase):TVShowsDao{
//        return database.tvShowsDao()
//    }
}