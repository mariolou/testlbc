package fr.louprod.testlbc

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import fr.louprod.backendmodule.models.AlbumModel
import fr.louprod.backendmodule.network.CustomObserver
import fr.louprod.backendmodule.network.DataRequester
import fr.louprod.backendmodule.repositories.AlbumRepository
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // For testing :
        AlbumRepository.getAllAlbums(
            object : CustomObserver<List<AlbumModel>>(
                object : DataRequester {
                    override fun showLoader() {
                        findViewById<TextView>(R.id.textview).setText("Loading....")
                    }

                    override fun hideLoader() {
                        findViewById<TextView>(R.id.textview).setText("Finish")
                    }

                    override fun resolveNetworkError(error: String) {
                        Snackbar.make(
                            findViewById(R.id.layout),
                            error,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }

                    override fun handleDisposable(d: Disposable) {

                    }

                }
            ) {
                override fun onCustomSuccess(data: List<AlbumModel>) {
                    Log.d("response", "SUCCESS : ${data.size}")
                    findViewById<TextView>(R.id.textview).setText("Nb albums : ${data.size}")
                }
            },
            true
        )
    }
}
