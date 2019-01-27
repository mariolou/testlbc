package fr.louprod.testlbc.albumsList

import androidx.recyclerview.widget.RecyclerView
import fr.louprod.backendmodule.models.AlbumModel
import fr.louprod.testlbc.databinding.ViewholderAlbumsListBinding

class AlbumsListViewHolder(val binding: ViewholderAlbumsListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(album: AlbumModel) {
        binding.idTextView.text = album.id.toString()
        binding.titleTextView.text = album.firstTitles.first()
        binding.imageview.setImageURI(album.firstImagesUrlThb.first())
    }
}