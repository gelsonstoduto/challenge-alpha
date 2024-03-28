package br.com.gstoduto.starwars.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.gstoduto.starwars.database.dao.MovieDao
import br.com.gstoduto.starwars.database.dao.VehicleDao
import br.com.gstoduto.starwars.database.entities.MovieEntity
import br.com.gstoduto.starwars.database.entities.VehicleEntity

@Database(
    version = 3,
    entities = [MovieEntity::class, VehicleEntity::class],
    exportSchema = true,
    autoMigrations = [
        AutoMigration(1, 2),
        AutoMigration(2, 3),]
)

abstract class StarWarsDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun vehicleDao(): VehicleDao

}