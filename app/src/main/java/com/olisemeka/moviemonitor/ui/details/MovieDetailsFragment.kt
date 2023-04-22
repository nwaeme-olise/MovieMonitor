package com.olisemeka.moviemonitor.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.olisemeka.moviemonitor.data.repository.MovieRepositoryImpl
import com.olisemeka.moviemonitor.data.source.local.model.MovieResult
import com.olisemeka.moviemonitor.databinding.FragmentMovieDetailsBinding
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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movie: MovieResult = args.movie
        movie.run {
            binding.tvSynopsis.text = overview
            binding.tvReleaseDate.text = releaseDate
            binding.tvGenre.text = genreIds.let {
                GenreIdConverter.convertIdToGenre(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}