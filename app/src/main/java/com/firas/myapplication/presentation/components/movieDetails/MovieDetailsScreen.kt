package com.firas.myapplication.presentation.components.movieDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.firas.myapplication.data.model.Genre
import com.firas.myapplication.presentation.viewmodels.MovieDetailViewModel
import com.firas.myapplication.R

@Composable
fun MovieDetails(detailViewModel: MovieDetailViewModel) {

    val state = detailViewModel.state.value
    val detailMovie = state.moviesDetail
    Column(
        modifier =
        Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier =
            Modifier
                .fillMaxWidth()
        ) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = "https://image.tmdb.org/t/p/w500/" + detailMovie?.backdrop_path,
                contentDescription = "Image from URL",
                contentScale = ContentScale.FillBounds
            )
            SectionAddFavoriteAndShare()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                RatingStars(
                    rating = detailMovie?.vote_average ?: 0.0,
                    maxStars = 5,
                    starSize = 24,
                    emptyColor = Color.Gray
                )
            }

        }

        OverviewAndTitle(
            detailMovie?.overview ?: "No overview available",
            detailMovie?.title ?: "Unknown Title"
        )

        GenreChips(detailMovie?.genres ?: emptyList())

        SectionPlayAndDownload()


    }


}

@Composable
fun GenreChips(genres: List<Genre>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        genres.forEach { genre ->
            AssistChip(
                onClick = {},
                label = { Text(genre.name) },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    labelColor = MaterialTheme.colorScheme.primary
                )

            )
        }
    }
}

@Composable
fun OverviewAndTitle(overview: String, title: String) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.padding(5.dp))
        Text(text = overview, style = MaterialTheme.typography.bodyMedium)

    }

}

@Composable
fun RatingStars(
    rating: Double,
    maxStars: Int = 5,
    starSize: Int = 5,//24
    filledColor: Color = colorResource(R.color.gold),
    emptyColor: Color = Color.Gray
) {
    Row {
        val filledStars = if (rating.toInt() > 5) 4 else rating.toInt()
        val halfStar = (rating - filledStars) >= 0.5
        val emptyStars = maxStars - filledStars - if (halfStar) 1 else 0

        repeat(filledStars) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Filled Star",
                tint = filledColor,
                modifier = Modifier.size(starSize.dp)
            )
        }

        if (halfStar) {
            Box {
                // Grey full star
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = "Full Star",
                    tint = Color.Gray,
                    modifier = Modifier.size(starSize.dp)

                )

                Icon(
                    imageVector = halfStar(),
                    contentDescription = "Half Star",
                    tint = filledColor,
                    modifier = Modifier.size(starSize.dp)
                )

            }

        }

        repeat(emptyStars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = "Empty Star",
                tint = emptyColor,
                modifier = Modifier.size(starSize.dp)
            )
        }
    }
}

@Composable
fun SectionPlayAndDownload() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,

                )
        ) {
            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play")
            Text(text = "Play")
        }
        Button(colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ), onClick = {
            //onDownloadClick()
        }) {
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Download")
            Text(text = "Download")
        }
    }
}

@Composable
fun SectionAddFavoriteAndShare() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.End
    ) {

        FloatingActionButton(
            modifier = Modifier.size(30.dp),
            onClick = { /* Handle click */ },
            containerColor = Color.Gray.copy(alpha = 0.5f),
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Add"
            )
        }
        Spacer(modifier = Modifier.width(8.dp)) // Adds space between the buttons

        FloatingActionButton(
            modifier = Modifier.size(30.dp),
            onClick = { /* Handle click */ },
            containerColor = Color.Gray.copy(alpha = 0.5f),

            ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share"
            )
        }
    }
}

fun halfStar(): ImageVector {
    return materialIcon(name = "Outlined.HalfStar") {
        materialPath {
            moveTo(12.0f, 17.27f)
            lineTo(12.0f, 2.0f)
            lineTo(9.19f, 8.63f)
            lineTo(2.0f, 9.24f)
            lineToRelative(5.46f, 4.73f)
            lineTo(5.82f, 21.0f)
            lineTo(12.0f, 17.27f)
            close()
        }
    }
}