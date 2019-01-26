package fr.louprod.testlbc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.louprod.backendmodule.models.TrackModel
import fr.louprod.backendmodule.repositories.TrackRepository
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // For testing :
        TrackRepository.getAllTracks(
            object : Observer<List<TrackModel>> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: List<TrackModel>) {
                    Log.d("response", "${t.size}")
                }

                override fun onError(e: Throwable) {
                    Log.d("response", "ERROR")
                    e.printStackTrace()
                }

            }
        )
    }
}
