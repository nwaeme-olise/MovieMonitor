package com.olisemeka.moviemonitor.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.olisemeka.moviemonitor.data.repository.MovieRepositoryImpl
import com.olisemeka.moviemonitor.databinding.FragmentMovieListBinding
import com.olisemeka.moviemonitor.ui.movies.adapter.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var movieRepository: MovieRepositoryImpl


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: MovieListViewModel by activityViewModels {
            MovieListViewModelProviderFactory(
                movieRepository
            )
        }
        val adapter = MovieListAdapter {
            val action =
                MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(it)
            view.findNavController().navigate(action)
        }
        binding.rvMovie.adapter = adapter
        binding.rvMovie.addItemDecoration(
            DividerItemDecoration(
                requireContext(), LinearLayoutManager.VERTICAL
            )
        )
//        binding.progressBar.isVisible = true
        binding.btRetry.setOnClickListener { adapter.retry() }
        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.refresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.movieFlow.collect {
                adapter.submitData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect {
                val state = it.refresh
                binding.progressBar.isVisible = state is LoadState.Loading
                binding.tvErrorMessage.isVisible = state is LoadState.Error
                binding.ivError.isVisible = state is LoadState.Error
                binding.btRetry.isVisible = state is LoadState.Error
            }
        }

//        viewModel.movieListResults.observe(viewLifecycleOwner) { response ->
//            when (response) {
//                is Resource.Loading -> {
//                    binding.progressBar.isVisible = true
//                }
//
//                is Resource.Success -> {
//                    binding.progressBar.isVisible = false
//                    Log.d("MovieListFragment", "${response.data?.results}")
//                    response.data?.let {
//                        adapter.submitList(it.results)
//                    }
//                }
//
//                is Resource.Error -> {
//                    binding.progressBar.isVisible = false
//                    Log.e("MovieListFragment", "${response.message}")
//                }
//            }
//        }

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