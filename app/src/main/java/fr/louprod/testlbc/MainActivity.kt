package fr.louprod.testlbc

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import com.google.android.material.snackbar.Snackbar
import fr.louprod.backendmodule.models.AlbumModel
import fr.louprod.backendmodule.network.CustomObserver
import fr.louprod.backendmodule.network.DataRequester
import fr.louprod.backendmodule.repositories.AlbumRepository
import fr.louprod.testlbc.albumsList.AlbumsListFragment
import fr.louprod.testlbc.databinding.ActivityMainBinding
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }
}
