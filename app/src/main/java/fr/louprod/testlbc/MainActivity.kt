package fr.louprod.testlbc

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import fr.louprod.backendmodule.models.TrackModel
import fr.louprod.backendmodule.repositories.TrackRepository
import fr.louprod.backendmodule.network.CustomObserver
import fr.louprod.backendmodule.network.NetworkRequester
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // For testing :
        TrackRepository.getAllTracks(
            object : CustomObserver<List<TrackModel>>(
                object : NetworkRequester {
                    override fun showLoader() {

                    }

                    override fun hideLoader() {

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
                override fun onCustomSuccess(data: List<TrackModel>) {
                    Log.d("response", "SUCCESS : ${data.size}")
                }
            }
        )
    }
}
