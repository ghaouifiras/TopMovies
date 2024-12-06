package com.firas.myapplication.presentation.components.movieList


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.firas.myapplication.common.MovieState
import com.firas.myapplication.domain.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Composable
fun MovieScreen(
    moviesFlow: Flow<PagingData<MovieEntity>>,
    navController: NavController,
) {

    val lazyMovies = moviesFlow.collectAsLazyPagingItems()


    // Handle loading and error states
    Box(modifier = Modifier.fillMaxSize()) {
        // Display loading state when the first page is being loaded
        if (lazyMovies.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        // Display error message for the first page if it fails to load
        if (lazyMovies.loadState.refresh is LoadState.Error) {
            val error = (lazyMovies.loadState.refresh as LoadState.Error).error
            Text(
                text = "Error: ${error.localizedMessage}",
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
        ) {
            items(lazyMovies.itemCount) { index ->
                val movie = lazyMovies[index]
                movie?.let {
                    MovieItem(item = it) {
                        navController.navigate("Details/${it.id}")
                    }

                }

            }

            when (val appendState = lazyMovies.loadState.append) {
                is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }

                is LoadState.Error -> {
                    item {
                        Text(
                            text = "Error loading more movies: ${appendState.error.message}",
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                else -> {}
            }
        }
    }


}

@Composable
fun MovieItem(item: MovieEntity, onMovieClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onMovieClick)
    )
    {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = "https://image.tmdb.org/t/p/w500/" + item.poster_path,
            contentDescription = "Image from URL",
            contentScale = ContentScale.FillBounds
        )

    }

}