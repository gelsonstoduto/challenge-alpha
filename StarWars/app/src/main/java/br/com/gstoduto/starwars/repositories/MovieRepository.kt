package br.com.gstoduto.starwars.repositories

import android.util.Log
import br.com.gstoduto.starwars.database.dao.MovieDao
import br.com.gstoduto.starwars.database.entities.MovieEntity
import br.com.gstoduto.starwars.model.Movie
import br.com.gstoduto.starwars.network.model.movie.toMovieEntity
import br.com.gstoduto.starwars.network.services.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val dao: MovieDao,
    private val service: MovieService
) {
    suspend fun findMovies(): Flow<List<Movie>> {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.findAll()
                val entities = response.results.map { it.toMovieEntity() }
                dao.saveAll(*entities.toTypedArray())
            } catch (e: ConnectException) {
                Log.e("MovieRepository", "findMovies: falha ao conectar na API", e)
            }
        }

        return dao.findAll()
    }

    fun findMovie(id: String): Flow<MovieEntity> {
        return dao.findMovieById(id)
    }

    suspend fun addToMyList(id: String) {
        dao.addToMyList(id)
    }

    suspend fun removeFromMyList(id: String) {
        dao.removeFromMyList(id)
    }

    fun myList(): Flow<List<MovieEntity>> {
        return dao.myList()
    }
}
