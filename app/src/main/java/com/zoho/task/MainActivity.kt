package com.zoho.task

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zoho.task.dao.RoomDao
import com.zoho.task.model.Movie
import com.zoho.task.model.RoomModel
import com.zoho.task.network.ApiService
import com.zoho.task.network.Response
import com.zoho.task.repository.MovieRepository
import com.zoho.task.screens.MovieItem
import com.zoho.task.ui.theme.PopularTvSeriesTheme
import com.zoho.task.viewmodel.MovieViewModel
import com.zoho.task.factory.ViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val roomDao: RoomDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PopularTvSeriesTheme {
                MovieScreen(roomDao)
            }
        }
    }
}

@Composable
fun MovieScreen(roomDao: RoomDao?) {
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(false) }
    var apiHit by remember { mutableStateOf(false) }

    val viewModel: MovieViewModel = viewModel(
        factory = ViewModelFactory(
            MovieRepository(ApiService.NetworkClient.apiService)
        )
    )

    LaunchedEffect(apiHit) {
        if (!apiHit) {
            viewModel.getMovieList("59afaff94bccdd263c2607a28c56de6a")
            apiHit = true
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Spacer(modifier = Modifier.heightIn(min = 60.dp))
        val movieState by viewModel.movieState.collectAsStateWithLifecycle()
        when (val result = movieState) {
            is Response.Loading -> {
                isLoading = true
            }
            is Response.Success -> {
                isLoading = false
                result.data?.let { movie ->
                    MovieList(movie)
                    saveMovieToLocalDatabase(roomDao, movie)
                }
            }
            is Response.Error -> {
                isLoading = false
                Toast.makeText(context, "${result.errorMessage}", Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun MovieList(movie: Movie) {
    MovieItem(movie)
}

private fun saveMovieToLocalDatabase(roomDao: RoomDao?, movie: Movie) {
    roomDao?.roomInterface()?.getMovie()

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PopularTvSeriesTheme {

    }
}
