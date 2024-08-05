package com.zoho.task.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.zoho.task.R
import com.zoho.task.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MovieItem(movie: Movie) {
    var searchQuery by remember { mutableStateOf("") }
    var pullDistance by remember { mutableFloatStateOf(0f) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val productionCompanies by produceState(initialValue = movie.productionCompanies) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                if (index >= value.size - 3) {
                    value = value + fetchMoreProductionCompanies()
                }
            }
    }

    var isRefreshing by remember { mutableStateOf(false) }
    if (isRefreshing) {
        LaunchedEffect(isRefreshing) {
            delay(1000)
            isRefreshing = false
        }
    }

    val filteredCompanies = productionCompanies.filter {
        it.name.contains(searchQuery, ignoreCase = true) || it.originCountry.contains(searchQuery, ignoreCase = true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectVerticalDragGestures { _, dragAmount ->
                    pullDistance += dragAmount
                    if (pullDistance > 1000) {
                        pullDistance = 0f
                        isRefreshing = true
                    }
                }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
                .background(colorResource(id = R.color.white))
        ) {
            if (isRefreshing) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                )
            }

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(top = 30.dp)
            ) {
                val (posterPath, releaseDate, status, tagline, runtime, title, voteAverage, overview, lazyColumn, searchField) = createRefs()

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search Production Companies") },
                    shape = RoundedCornerShape(16.dp),
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                        .padding(start = 20.dp, end = 20.dp)
                        .constrainAs(searchField) {
                            top.linkTo(title.bottom, margin = 5.dp)
                            start.linkTo(parent.start, margin = 20.dp)
                            end.linkTo(parent.end, margin = 20.dp)
                        }
                )

                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${movie.posterPath}"),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(CircleShape)
                        .constrainAs(posterPath) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(searchField.bottom, margin = 5.dp)
                        }
                        .size(90.dp, 90.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = movie.status,
                    modifier = Modifier.constrainAs(status) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(posterPath.bottom, margin = 5.dp)
                    },
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Normal,
                    ),
                )

                Text(
                    text = movie.releaseDate,
                    modifier = Modifier.constrainAs(releaseDate) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(status.bottom, margin = 5.dp)
                    },
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Normal,
                    ),
                )

                Text(
                    text = movie.originalLanguage,
                    modifier = Modifier.constrainAs(overview) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(releaseDate.bottom, margin = 5.dp)
                    },
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Normal,
                    ),
                )

                Text(
                    text = movie.tagline,
                    modifier = Modifier.constrainAs(tagline) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(overview.bottom, margin = 10.dp)
                    },
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Normal,
                    ),
                )
                Text(
                    text = movie.runtime.toString(),
                    modifier = Modifier.constrainAs(runtime) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(tagline.bottom, margin = 5.dp)
                    },
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Normal,
                    ),
                )
                Text(
                    text = movie.voteAverage.toString(),
                    modifier = Modifier.constrainAs(voteAverage) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(runtime.bottom, margin = 5.dp)

                    },
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                    ),
                )

                Spacer(modifier = Modifier.constrainAs(createRef()) {
                    top.linkTo(voteAverage.bottom, margin = 50.dp)
                })

                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .constrainAs(lazyColumn) {
                            top.linkTo(voteAverage.bottom, margin = 250.dp)
                            bottom.linkTo(parent.bottom)
                        }
                ) {
                    items(filteredCompanies) { company ->
                        ProductionCompanyCard(company = company)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductionCompanyCard(company: Movie.ProductionCompany) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Surface {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${company.logoPath}"),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f),
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = company.name,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 17.sp),
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = company.originCountry,
                        style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp),
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}

suspend fun fetchMoreProductionCompanies(): List<Movie.ProductionCompany> {
    delay(1000)
    return listOf(
        Movie.ProductionCompany()
    )
}
