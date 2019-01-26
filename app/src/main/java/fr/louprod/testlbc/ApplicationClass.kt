package fr.louprod.testlbc

import android.app.Application

class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()
        ModulesConfiguration.initModules(applicationContext)
    }
}