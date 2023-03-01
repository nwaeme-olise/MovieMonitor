package com.olisemeka.moviemonitor.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.olisemeka.moviemonitor.data.repository.MovieRepositoryImpl
import com.olisemeka.moviemonitor.databinding.FragmentMovieDetailsBinding
import com.olisemeka.moviemonitor.ui.movielist.MovieListViewModel
import com.olisemeka.moviemonitor.ui.movielist.MovieListViewModelProviderFactory
import com.olisemeka.moviemonitor.util.GenreIdConverter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var movieRepository: MovieRepositoryImpl

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel: MovieListViewModel by activityViewModels { MovieListViewModelProviderFactory(movieRepository)}
        val moviePosition = args.moviePosition
        viewModel.movieListResults.observe(viewLifecycleOwner) { response ->
            val movieResponse = response.data?.results
            binding.tvSynopsis.text = movieResponse?.get(moviePosition)?.overview
            binding.tvReleaseDate.text = movieResponse?.get(moviePosition)?.releaseDate
            binding.tvGenre.text = movieResponse?.get(moviePosition)?.genreIds?.let {
                GenreIdConverter.convertIdToGenre(it)
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}