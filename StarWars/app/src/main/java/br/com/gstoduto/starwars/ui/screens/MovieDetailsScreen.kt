package br.com.gstoduto.starwars.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.material.icons.filled.PlaylistRemove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.gstoduto.starwars.model.Movie
import br.com.gstoduto.starwars.sampleData.sampleMovieAdded
import br.com.gstoduto.starwars.sampleData.sampleMovieRemoved
import br.com.gstoduto.starwars.ui.theme.StarWarsTheme
import br.com.gstoduto.starwars.ui.uistates.MovieDetailsUiState
import br.com.gstoduto.starwars.util.Constants
import coil.compose.AsyncImage

private class ToggleButton(
    val text: String,
    val icon: ImageVector,
    val action: (Movie) -> Unit,
    val buttonColor: Long
)

@Composable
fun MovieDetailsScreen(
    uiState: MovieDetailsUiState,
    onAddToMyListClick: (Movie) -> Unit,
    onRemoveFromMyList: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color(0xFF2B2B2B))
    ) {

        val movie = uiState.movie
        Column {
            Column(
                Modifier
                    .padding(horizontal = 24.dp, vertical = 36.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    movie?.let {
                        val urlImage = movie.url
                            .substring(movie.url.length - 3)
                            .split("/")
                            .drop(1)
                            .joinToString("")
                        val image = "${Constants.BASE_URL_MOVIE_IMAGE}$urlImage.jpg"

                        AsyncImage(
                            model = image,
                            contentDescription = null,
                            Modifier
                                .size(
                                    height = 152.dp,
                                    width = 100.dp
                                )
                                .clip(shape = RoundedCornerShape(4.dp)),
                            placeholder = ColorPainter(Color.Gray),
                            contentScale = ContentScale.Crop
                        )
                        Column {
                            Text(text = movie.title)
                            Text(text = "Episode ${movie.episodeId.toString()}")
                            Text(text = "Director: ${movie.director}")
                            Text(text = "Producer(s): ${movie.producer}")
                            movie.openingCrawl?.let {
                                Text(
                                    text = "Opening Crawl: ${
                                        it.substring(
                                            0,
                                            20
                                        )
                                    }"
                                )
                            }
                        }
                    }

                }
                Spacer(modifier = Modifier.size(24.dp))
                Row {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val toggleButton = if (movie?.inMyList == true) {
                            ToggleButton(
                                icon = Icons.Default.PlaylistRemove,
                                text = "Remover da lista",
                                action = onRemoveFromMyList,
                                buttonColor = 0xffB20510
                            )
                        } else {
                            ToggleButton(
                                icon = Icons.Default.PlaylistAdd,
                                text = "Adicionar à lista",
                                action = onAddToMyListClick,
                                buttonColor = 0xff4F4F4F
                            )
                        }
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Box(
                                Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .background(
                                        Color(toggleButton.buttonColor),
                                        shape = CircleShape
                                    )
                                    .align(Center)
                                    .clip(CircleShape)
                                    .clickable {
                                        movie?.let { toggleButton.action(it) }
                                    }
                                    .padding(8.dp)
                            ) {
                                Row(
                                    Modifier.align(Center),
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        toggleButton.icon,
                                        contentDescription = null,
                                        Modifier
                                            .size(32.dp)
                                    )
                                    Text(text = toggleButton.text)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenWithMovieAddedToMyListPreview() {
    StarWarsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MovieDetailsScreen(
                uiState = MovieDetailsUiState(),
                onAddToMyListClick = {},
                onRemoveFromMyList = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenWithoutMovieAddedToMyListPreview() {
    StarWarsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MovieDetailsScreen(
                uiState = MovieDetailsUiState(movie = sampleMovieAdded),
                onAddToMyListClick = {},
                onRemoveFromMyList = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsScreenWithMovieRemovedFromMyListPreview() {
    StarWarsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MovieDetailsScreen(
                uiState = MovieDetailsUiState(movie = sampleMovieRemoved),
                onAddToMyListClick = {},
                onRemoveFromMyList = {},
            )
        }
    }
}
