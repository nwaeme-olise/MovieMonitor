package com.olisemeka.moviemonitor.ui.MovieList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.olisemeka.moviemonitor.api.RetrofitInstance
import com.olisemeka.moviemonitor.data.MovieRepository
import com.olisemeka.moviemonitor.databinding.FragmentMovieListBinding
import com.olisemeka.moviemonitor.ui.MovieList.MovieListAdapter
import com.olisemeka.moviemonitor.util.Constants
import retrofit2.HttpException
import java.io.IOException

class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private val movieRepository by lazy{ MovieRepository() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: MovieListViewModel by viewModels { MovieListViewModelProviderFactory(movieRepository)  }
        val adapter = MovieListAdapter(requireContext())
        binding.rvMovie.adapter = adapter
        binding.progressBar.isVisible = true
        viewModel.movieListResults.observe(viewLifecycleOwner, Observer {response ->

                adapter.submitList(response.results)
                binding.progressBar.isVisible = false



        })

//        lifecycleScope.launchWhenCreated {
//            binding.progressBar.isVisible = true
//            val response = try{
//                RetrofitInstance.api.getMovieListResults(1, sortBy=Constants.SORT_ORDER)
//            } catch(e:IOException){
//                binding.progressBar.isVisible = false
//                return@launchWhenCreated
//
//            } catch(e:HttpException){
//                binding.progressBar.isVisible=false
//                return@launchWhenCreated
//            }
//            if (response.isSuccessful && response.body() != null){
//                val body = response.body()!!
//                binding.rvMovie.adapter = MovieListAdapter(context, body.results)
//            }
//            binding.progressBar.isVisible = false
//        }
    }
}