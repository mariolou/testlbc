package fr.louprod.testlbc

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import fr.louprod.testlbc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val navController by lazy {
        findNavController(R.id.navHost)
    }

    private val appBarConfig by lazy {
        AppBarConfiguration.Builder(navController.graph).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )


        binding?.toolbar?.let {
            NavigationUI.setupWithNavController(
                it,
                navController,
                appBarConfig
            )
        }

        setSupportActionBar(binding?.toolbar)
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.d("click", "11111")
        return NavigationUI.navigateUp(navController, appBarConfig) || super.onSupportNavigateUp()
    }
}
