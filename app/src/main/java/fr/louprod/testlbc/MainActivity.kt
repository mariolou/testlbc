package fr.louprod.testlbc

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.louprod.backendmodule.models.TrackModel
import fr.louprod.backendmodule.repositories.TrackRepository
import fr.louprod.backendmodule.utils.CustomObserver

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // For testing :
        TrackRepository.getAllTracks(
            object : CustomObserver<List<TrackModel>>(null) {
                override fun onCustomSuccess(data: List<TrackModel>) {
                    Log.d("response", "SUCCESS : ${data.size}")
                }
            }
        )
    }
}
