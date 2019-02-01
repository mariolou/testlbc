package fr.louprod.testlbc.albumslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fr.louprod.testlbc.baseclasses.BaseFragment
import fr.louprod.testlbc.databinding.FragmentAlbumsListBinding

class AlbumsListFragment : BaseFragment(), AlbumsListViewHolderClickInterface {

    var binding: FragmentAlbumsListBinding? = null
    var viewModel: AlbumsListViewModel? = null
    var adapter = AlbumsListAdapter(null, this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAlbumsListBinding.inflate(inflater, container, false)

        viewModel = getViewModel(AlbumsListViewModel::class.java).also {
            it?.albumsList?.observe(this, Observer {
                adapter.albums = it
                adapter.notifyDataSetChanged()
            })
        }

        binding?.recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            this.adapter = this@AlbumsListFragment.adapter
        }

        viewModel?.getAlbums()

        return binding?.root
    }

    override fun onItemClick(albumId: Int) {
        binding?.root?.findNavController()?.navigate(
            AlbumsListFragmentDirections.actionAlbumsListFragmentToAlbumDetailsFragment(albumId)
        )
    }

    override fun onDestroyView() {
        binding?.recyclerView?.adapter = null
        super.onDestroyView()
    }
}