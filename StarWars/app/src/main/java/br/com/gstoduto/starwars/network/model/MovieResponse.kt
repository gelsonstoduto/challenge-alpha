package br.com.gstoduto.starwars.network.model

import br.com.gstoduto.starwars.database.entities.MovieEntity
import br.com.gstoduto.starwars.model.Movie

data class MovieResponse(
    val id: String,
    val title: String,
    val url: String,
    val episode_id: String,
    val opening_crawl: String,
    val director: String,
    val producer: String,
    val inMyList: Boolean
)

fun MovieResponse.toMovieEntity(): MovieEntity {
    return MovieEntity(
        title = title,
        url = url,
        episodeId = episode_id,
        openingCrawl = opening_crawl,
        director = director,
        producer = producer,
        inMyList = inMyList
    )
}

fun MovieResponse.toMovie(): Movie {
    return Movie(
        title = title,
        url = url,
        episodeId = episode_id,
        openingCrawl = opening_crawl,
        director = director,
        producer = producer,
        inMyList = inMyList
    )
}