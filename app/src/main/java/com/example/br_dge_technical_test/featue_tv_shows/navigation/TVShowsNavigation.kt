package com.example.br_dge_technical_test.featue_tv_shows.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.TVShowsResponseItem
import com.example.br_dge_technical_test.featue_tv_shows.presentation.DetailsScreenComponent
import com.example.br_dge_technical_test.featue_tv_shows.presentation.SearchComponent

@Composable
fun TVShowsNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.HomeScreen.name) {
        composable(Screens.HomeScreen.name) {
            SearchComponent(navController = navController)
        }
        composable(
            route = Screens.DetailsScreen.name
        ) {
            val result =
                navController.previousBackStackEntry?.savedStateHandle?.get<TVShowsResponseItem>(
                    TV_SHOWS_INDEX_KEY
                )
            DetailsScreenComponent(result)
        }
    }

}

internal const val TV_SHOWS_INDEX_KEY = "TV_SHOWS_KEY"

enum class Screens {
    HomeScreen,
    DetailsScreen
}