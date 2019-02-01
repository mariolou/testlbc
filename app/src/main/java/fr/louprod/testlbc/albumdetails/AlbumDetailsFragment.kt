package fr.louprod.testlbc.albumdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import fr.louprod.testlbc.R
import fr.louprod.testlbc.baseclasses.BaseFragment
import fr.louprod.testlbc.databinding.FragmentAlbumDetailsBinding

class AlbumDetailsFragment : BaseFragment() {

    private var binding: FragmentAlbumDetailsBinding? = null
    private var viewModel: AlbumDetailsViewModel? = null
    private val adapter = AlbumDetailsAdapter(null)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAlbumDetailsBinding.inflate(layoutInflater, container, false)

        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = this@AlbumDetailsFragment.adapter
            setHasFixedSize(true)
        }

        viewModel = getViewModel(AlbumDetailsViewModel::class.java)?.also { viewModel ->
            viewModel.tracksList.observe(this, Observer {
                adapter.tracks = it
                adapter.notifyDataSetChanged()
            })
        }

        val args: AlbumDetailsFragmentArgs by navArgs()

        viewModel?.getAllAlbumTracks(
            args.albumId
        )

        setAppBarTitle(getString(R.string.albumDetailsFragment_title, args.albumId.toString()))

        return binding?.root
    }
}