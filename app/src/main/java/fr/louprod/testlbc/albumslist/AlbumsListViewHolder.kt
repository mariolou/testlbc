package fr.louprod.testlbc.albumslist

import androidx.recyclerview.widget.RecyclerView
import fr.louprod.backendmodule.models.AlbumModel
import fr.louprod.testlbc.databinding.ViewholderAlbumsListBinding

class AlbumsListViewHolder(val binding: ViewholderAlbumsListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        album: AlbumModel,
        onItemClickInterface: AlbumsListViewHolderClickInterface
    ) {
        binding.idTextView.text = album.id.toString()
        binding.titleTextView.text = album.firstTitles.first()
        binding.imageview.setImageURI(album.firstImagesUrlThb.first())
        binding.root.setOnClickListener {
            onItemClickInterface.onItemClick(album.id)
        }
    }
}