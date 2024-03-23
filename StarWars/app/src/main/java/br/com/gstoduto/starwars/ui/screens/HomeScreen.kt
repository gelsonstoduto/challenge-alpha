package br.com.gstoduto.starwars.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.gstoduto.starwars.model.Movie
import br.com.gstoduto.starwars.sampleData.sampleMovies
import br.com.gstoduto.starwars.ui.theme.StarWarsTheme
import br.com.gstoduto.starwars.ui.uistates.HomeUiState
import br.com.gstoduto.starwars.util.Constants
import coil.compose.AsyncImage

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onMovieClick: (Movie) -> Unit,
    onRetryLoadMovies: () -> Unit,
    columns: Int,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        HomeUiState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    Modifier.align(Alignment.Center)
                )
            }
        }

        is HomeUiState.Success -> {
            val movies = uiState.movies
            Column(
                modifier
                    .fillMaxSize()
            ) {
                Surface {
                    Text(
                        text = "H o m e / F i l m s",
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(columns),
                    Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalItemSpacing = 16.dp,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(movies) { movie ->
                        val urlImage = movie.url
                            .substring(movie.url.length - 3)
                            .split("/")
                            .drop(1)
                            .joinToString("")
                        val image = "${Constants.BASE_URL_MOVIE_IMAGE}$urlImage.jpg"

                        Column() {
                            AsyncImage(
                                model = image,
                                contentDescription = null,
                                Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .clickable {
                                        onMovieClick(movie)
                                    },
                                placeholder = ColorPainter(Color.Gray),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                text = "${movie.episodeId}: ${movie.title}",
                                Modifier
                                    .background(color = Color.White)
                                    .fillMaxWidth()
                                    .padding(start = 4.dp),
                                style = TextStyle.Default.copy(
                                    fontSize = 10.sp,
                                    color = Color.Black
                                )
                            )
                        }
                    }
                }
            }
        }

        HomeUiState.Empty -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Nenhuma seção encontrada",
                    style = TextStyle.Default.copy(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                )
                TextButton(onClick = { onRetryLoadMovies() }) {
                    Text(text = "Tentar buscar novamente")
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    StarWarsTheme {
        Surface {
            Surface(color = MaterialTheme.colorScheme.background) {
                HomeScreen(
                    uiState = HomeUiState.Success(
                        movies = sampleMovies
                    ),
                    onRetryLoadMovies = {},
                    onMovieClick = {},
                    columns = 2
                )
            }
        }
    }
}