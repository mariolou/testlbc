package fr.louprod.testlbc

import android.content.Context
import fr.louprod.backendmodule.moduleConfiguration.BackendModuleConfiguration
import fr.louprod.backendmodule.moduleConfiguration.BackendModuleConfigurationInterface

object ModulesConfiguration {
    fun initModules(applicationContext: Context) {
        BackendModuleConfiguration.moduleConfiguration = object : BackendModuleConfigurationInterface {
            override fun getApplicationContext(): Context {
                return applicationContext
            }

        }
    }
}