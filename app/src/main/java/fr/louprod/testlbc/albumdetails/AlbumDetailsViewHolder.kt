package fr.louprod.testlbc.albumdetails

import androidx.recyclerview.widget.RecyclerView
import fr.louprod.backendmodule.models.TrackModel
import fr.louprod.testlbc.databinding.ViewholderAlbumDetailBinding

class AlbumDetailsViewHolder(val binding: ViewholderAlbumDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(track: TrackModel) {
        binding.idTextView.text = track.id.toString()
        binding.titleTextView.text = track.title
        binding.imageview.setImageURI(track.imageUrl)
    }
}