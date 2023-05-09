package com.example.br_dge_technical_test.featue_tv_shows.presentation

import android.text.Html
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.br_dge_technical_test.R
import com.example.br_dge_technical_test.featue_tv_shows.data.remote.model.TVShowsResponseItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsScreenComponent(
    showItem: TVShowsResponseItem?,
) {
    Surface(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        color = lightGreen.copy(0.1f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (showItem != null) {
                Text(text = showItem.show.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                if (showItem.show.image.original.isNotBlank()) {
                    GlideImage(
                        model = showItem.show.image.original,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(300.dp)
                            .shadow(1.dp),
                        contentScale = ContentScale.FillBounds,
                        alignment = Alignment.CenterStart
                    )

                } else {
                    Image(
                        painter = painterResource(id = R.drawable.tv_default),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(200.dp)
                            .shadow(1.dp),
                        contentScale = ContentScale.FillBounds,
                        alignment = Alignment.CenterStart
                    )
                }
                Card(
                    shape = RoundedCornerShape(10.dp), backgroundColor = Color.White,
                    elevation = 4.dp, border = BorderStroke(1.dp, color = Color.LightGray),
                    modifier = Modifier
                        .padding(16.dp)

                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 10.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = Html.fromHtml(showItem.show.summary).toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                }
                Card(
                    shape = RoundedCornerShape(10.dp), backgroundColor = Color.White,
                    elevation = 4.dp, border = BorderStroke(1.dp, color = Color.LightGray),
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                    ) {


                            TitleDesc(pair = Pair("Geners", showItem.show.genres.first().orEmpty()))

                        TitleDesc(pair = Pair("Status", showItem.show.status.orEmpty()))
                        TitleDesc(pair = Pair("Rating", showItem.show.rating.toString()))
                        TitleDesc(pair = Pair("Language", showItem.show.language))
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun TitleDesc(pair: Pair<String, String> = Pair("ssssss", "asasasas")) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(text = pair.first)
        Text(text = pair.second, fontWeight = FontWeight.SemiBold)
    }
}