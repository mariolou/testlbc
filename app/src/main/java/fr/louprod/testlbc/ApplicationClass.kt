package fr.louprod.testlbc

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()
        ModulesConfiguration.initModules(applicationContext)
        Fresco.initialize(this)
    }
}