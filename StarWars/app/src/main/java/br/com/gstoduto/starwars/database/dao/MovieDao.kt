package br.com.gstoduto.starwars.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.gstoduto.starwars.database.entities.MovieEntity
import br.com.gstoduto.starwars.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(movie: MovieEntity)

    @Query("SELECT * FROM movies")
    fun findAll(): Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE inMyList = 1")
    fun myList(): Flow<List<MovieEntity>>

    @Query("UPDATE movies SET inMyList = 0 WHERE title = :id")
    suspend fun removeFromMyList(id: String)

    @Query("UPDATE movies SET inMyList = 1 WHERE title = :id")
    suspend fun addToMyList(id: String)

    @Query("SELECT * FROM movies WHERE title = :id")
    fun findMovieById(id: String?): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(vararg entities: MovieEntity)
}