package br.com.gstoduto.starwars.repositories

import android.util.Log
import br.com.gstoduto.starwars.database.dao.MovieDao
import br.com.gstoduto.starwars.model.Movie
import br.com.gstoduto.starwars.network.model.toMovieEntity
import br.com.gstoduto.starwars.network.services.MovieService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.net.ConnectException
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class MovieRepository @Inject constructor(
    private val dao: MovieDao,
    private val service: MovieService
) {
    suspend fun findMovies(): Flow<List<Movie>> {
        CoroutineScope(coroutineContext).launch {
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
}
