package fr.louprod.testlbc

import android.content.Context
import fr.louprod.backendmodule.moduleconfiguration.BackendModuleConfiguration
import fr.louprod.backendmodule.moduleconfiguration.BackendModuleConfigurationInterface

object ModulesConfiguration {
    fun initModules(applicationContext: Context) {
        BackendModuleConfiguration.moduleConfiguration = object : BackendModuleConfigurationInterface {
            override fun getApplicationContext(): Context {
                return applicationContext
            }
        }
    }
}