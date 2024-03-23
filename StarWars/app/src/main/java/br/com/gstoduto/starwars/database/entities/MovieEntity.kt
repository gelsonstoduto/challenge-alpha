package br.com.gstoduto.starwars.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.gstoduto.starwars.model.Movie

@Entity(tableName = "movies")
class MovieEntity(
    @PrimaryKey
    val title: String,
    @ColumnInfo(name = "episode_id") var episodeId: String?  = null,
    @ColumnInfo(name = "opening_crawl") var openingCrawl: String?  = null,
    val director: String,
    val producer: String,
    val url: String,
    val inMyList: Boolean = false
)

fun MovieEntity.toMovie() = Movie(
    title = title,
    episodeId = episodeId,
    openingCrawl = openingCrawl,
    director = director,
    producer = producer,
    url = url,
    inMyList = inMyList
)
