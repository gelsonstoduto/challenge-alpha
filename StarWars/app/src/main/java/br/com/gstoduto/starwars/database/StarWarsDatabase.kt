package br.com.gstoduto.starwars.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.gstoduto.starwars.database.dao.MovieDao
import br.com.gstoduto.starwars.database.dao.VehicleDao
import br.com.gstoduto.starwars.database.entities.MovieEntity
import br.com.gstoduto.starwars.database.entities.VehicleEntity

@Database(
    version = 2,
    entities = [MovieEntity::class, VehicleEntity::class],
)
abstract class StarWarsDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun vehicleDao(): VehicleDao

}