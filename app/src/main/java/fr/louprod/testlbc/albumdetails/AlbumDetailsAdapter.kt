package fr.louprod.testlbc.albumdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.louprod.backendmodule.models.TrackModel
import fr.louprod.testlbc.databinding.ViewholderAlbumDetailBinding

class AlbumDetailsAdapter(var tracks: List<TrackModel>?) : RecyclerView.Adapter<AlbumDetailsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailsViewHolder {
        return AlbumDetailsViewHolder(
            ViewholderAlbumDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return tracks?.size ?: 0
    }

    override fun onBindViewHolder(holder: AlbumDetailsViewHolder, position: Int) {
        tracks?.getOrNull(position)?.let {
            holder.bind(it)
        }
    }
}