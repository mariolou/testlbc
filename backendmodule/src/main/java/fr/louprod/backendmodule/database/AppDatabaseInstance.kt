package fr.louprod.backendmodule.database

import androidx.room.Room
import fr.louprod.backendmodule.moduleconfiguration.BackendModuleConfiguration

object AppDatabaseInstance {

    private const val databaseName = "tlbcDb"

    val database: AppDatabase? by lazy {
        BackendModuleConfiguration.moduleConfiguration?.getApplicationContext()?.let { context ->
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                databaseName
            ).build()
        }
    }
}