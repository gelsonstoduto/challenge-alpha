package br.com.gstoduto.starwars.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.gstoduto.starwars.database.dao.MovieDao
import br.com.gstoduto.starwars.database.entities.MovieEntity

@Database(
    version = 1,
    entities = [MovieEntity::class],
)
abstract class StarWarsDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}