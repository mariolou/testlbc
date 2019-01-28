package fr.louprod.testlbc.albumslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.louprod.backendmodule.models.AlbumModel
import fr.louprod.testlbc.databinding.ViewholderAlbumsListBinding

class AlbumsListAdapter(
    var albums: List<AlbumModel>?,
    val onItemClickInterface: AlbumsListViewHolderClickInterface
) : RecyclerView.Adapter<AlbumsListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsListViewHolder {
        return AlbumsListViewHolder(
            ViewholderAlbumsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return albums?.size ?: 0
    }

    override fun onBindViewHolder(holder: AlbumsListViewHolder, position: Int) {
        albums?.getOrNull(position)?.let {
            holder.bind(it, onItemClickInterface)
        }
    }
}