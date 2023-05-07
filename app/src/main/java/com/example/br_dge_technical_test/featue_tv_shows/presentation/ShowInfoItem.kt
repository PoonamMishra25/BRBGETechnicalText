package com.example.br_dge_technical_test.featue_tv_shows.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.TVShowsResponseItem
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchComponent() {
    val keyboardController = LocalSoftwareKeyboardController.current
    val viewModel: TvShowViewModel = hiltViewModel()
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()//need for snackBar
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is TvShowViewModel.UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                }
            }
        }
    }
    Scaffold(scaffoldState = scaffoldState) {
        Box(modifier = Modifier.background(MaterialTheme.colors.background).padding(it.calculateBottomPadding())) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TextField(
                    value =
                    viewModel.searchQuery.value,
                    onValueChange = viewModel::onSearch,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = "Search..")
                    }
                )
                  keyboardController?.hide()
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.showItem.size) { index ->
                        val wordInfo = state.showItem[index]
                        if (index > 0) {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        ShowInfoItem(tvShowsResponseItem = wordInfo)
                        if (index < state.showItem.size - 1) {
                            Divider()
                        }
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ShowInfoItem(
    tvShowsResponseItem: TVShowsResponseItem,
    modifier: Modifier = Modifier
) {
    val show = tvShowsResponseItem.show
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start) {
        GlideImage(
            model = show.image,
            contentDescription = null,
            modifier = modifier
                .padding(8.dp)
                .size(50.dp),
            contentScale = ContentScale.Fit,
            alignment = Alignment.CenterStart
        )
        Text(
            show.name,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp
        )
    }
}
