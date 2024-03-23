package br.com.gstoduto.starwars.sampleData

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import br.com.gstoduto.starwars.model.Movie
import kotlin.random.Random

val randomImage
    get() = "https://swapi.dev/api/films/${Random.nextInt(1, 6)}/${
        Random.nextInt(
            2,
            4
        )
    }"

val randomTitle
    get() = LoremIpsum(Random.nextInt(1, 5)).values
        .first().toString()
val randomDescription
    get() = LoremIpsum(Random.nextInt(5, 30)).values
        .first().toString()
val randomDirector
    get() = LoremIpsum(Random.nextInt(30, 45)).values
        .first().toString()
val randomProducer
    get() = LoremIpsum(Random.nextInt(45, 60)).values
        .first().toString()
val randomEpisode
    get() = LoremIpsum(Random.nextInt(60, 75)).values
        .first().toString()

val sampleMovies = List(15) {
    Movie(
        title = randomTitle,
        url = randomImage,
        openingCrawl = randomDescription,
        episodeId = randomEpisode,
        director = randomDirector,
        producer = randomProducer,
        inMyList = it % 2 == 0
    )
}