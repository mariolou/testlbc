package fr.louprod.testlbc.albumdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import fr.louprod.testlbc.baseclasses.BaseFragment
import fr.louprod.testlbc.databinding.FragmentAlbumDetailsBinding

class AlbumDetailsFragment: BaseFragment() {

    var binding: FragmentAlbumDetailsBinding? = null
    var viewModel: AlbumDetailsViewModel? = null
    val adapter = AlbumDetailsAdapter(null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAlbumDetailsBinding.inflate(layoutInflater, container, false)

        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = this@AlbumDetailsFragment.adapter
            setHasFixedSize(true)
        }

        viewModel = getViewModel(AlbumDetailsViewModel::class.java)?.also {
            it.tracksList.observe(this, Observer {
                adapter.tracks = it
                adapter.notifyDataSetChanged()
            })
        }

        val args: AlbumDetailsFragmentArgs by navArgs()

        viewModel?.getAllAlbumTracks(
            args.albumId
        )

        return binding?.root
    }
}