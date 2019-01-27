package fr.louprod.testlbc.albumsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import fr.louprod.testlbc.baseClasses.BaseFragment
import fr.louprod.testlbc.databinding.FragmentAlbumsListBinding

class AlbumsListFragment: BaseFragment() {

    var binding: FragmentAlbumsListBinding? = null
    var viewModel: AlbumsListViewModel? = null
    var adapter: AlbumsListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAlbumsListBinding.inflate(inflater, container, false)

        viewModel = getViewModel(AlbumsListViewModel::class.java).also {
            it?.albumsList?.observe(this, Observer {
                adapter?.albums = it
                adapter?.notifyDataSetChanged()
            })
        }

        adapter = AlbumsListAdapter(null)

        binding?.recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@AlbumsListFragment.context)
            this.adapter = this@AlbumsListFragment.adapter
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // We don't refresh the data from API if we just change the configuration
        viewModel?.getAlbums(savedInstanceState == null)
    }
}