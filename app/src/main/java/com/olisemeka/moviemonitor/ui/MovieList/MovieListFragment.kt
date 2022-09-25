package com.olisemeka.moviemonitor.ui.MovieList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.olisemeka.moviemonitor.api.RetrofitInstance
import com.olisemeka.moviemonitor.databinding.FragmentMovieListBinding
import com.olisemeka.moviemonitor.ui.MovieList.MovieListAdapter
import com.olisemeka.moviemonitor.util.Constants
import retrofit2.HttpException
import java.io.IOException

class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!


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
        val context = requireContext()
        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try{
                RetrofitInstance.api.getMovieListResults(1, Constants.API_KEY, Constants.SORT_ORDER)
            } catch(e:IOException){
                binding.progressBar.isVisible = false
                return@launchWhenCreated

            } catch(e:HttpException){
                binding.progressBar.isVisible=false
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() != null){
                val body = response.body()!!
                binding.rvMovie.adapter = MovieListAdapter(context, body.results)
            }
            binding.progressBar.isVisible = false
        }
    }
}