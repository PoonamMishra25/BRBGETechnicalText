package com.example.br_dge_technical_test.featue_tv_shows.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.br_dge_technical_test.R
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.TVShowsResponseItem
import com.example.br_dge_technical_test.featue_tv_shows.navigation.Screens
import com.example.br_dge_technical_test.featue_tv_shows.navigation.TV_SHOWS_INDEX_KEY
import kotlinx.coroutines.flow.collectLatest


internal val lightGreen = Color(0xFF009688)

@Composable
fun SearchComponent(navController: NavController) {
    val viewModel: TvShowViewModel = hiltViewModel()
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState() // need for snackBar
    var errorMessage  by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is TvShowViewModel.UIEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                    errorMessage = event.message
                }
            }
        }
    }
    Scaffold(scaffoldState = scaffoldState) {
        Box(
            modifier = Modifier
                .background(lightGreen.copy(0.1f))
                .padding(it.calculateBottomPadding()),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tv_icon),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier.padding(8.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.tv_default),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier.padding(8.dp)
                )

                Spacer(Modifier.height(16.dp))
                var searchQuery by remember {
                    mutableStateOf("")
                }
                TextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White),
                    shape = RoundedCornerShape(8.dp),
                    leadingIcon = {
                        Image(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(4.dp)
                                .clickable {
                                    viewModel.onSearch(searchQuery)
                                }
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    searchQuery = ""
                                })
                    },
                    placeholder = {
                        Text(text = "Search..")
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        trailingIconColor = lightGreen
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (errorMessage.isNotBlank() && state.showItem.isEmpty()) {
                    state.isLoading = false
                    Text(
                        text = stringResource(id = R.string.error_message),
                        color = MaterialTheme.colors.error,
                        style = TextStyle(fontSize = 14.sp, textAlign = TextAlign.Center)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                if (state.isLoading) {
                    CircularProgressIndicator()
                    state.isLoading = false
                } else {
                    state.isLoading = false
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )
                    {
                        itemsIndexed(state.showItem) { index, item ->

                            if (index > 0) {
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                            ShowInfoItem(tvShowsResponseItem = item) {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    key = TV_SHOWS_INDEX_KEY,
                                    value = item
                                )
                                navController.navigate(Screens.DetailsScreen.name)
                            }
                            if (index < state.showItem.size - 1) {
                                Divider()
                            }
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
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val show = tvShowsResponseItem.show
    Row(
        modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        GlideImage(
            model = show.image.original,
            contentDescription = null,
            modifier = modifier
                .padding(8.dp)
                .size(60.dp)
                .shadow(1.dp, shape = CircleShape),
            contentScale = ContentScale.FillBounds,
            alignment = Alignment.CenterStart
        )
        Spacer(Modifier.width(8.dp))
        Text(
            show.name,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp
        )
    }
}
