package com.olisemeka.moviemonitor.ui.movielist

import androidx.lifecycle.*
import com.olisemeka.moviemonitor.data.MovieListResult
import com.olisemeka.moviemonitor.data.MovieRepository
import com.olisemeka.moviemonitor.util.Constants
import com.olisemeka.moviemonitor.util.Resource
import kotlinx.coroutines.launch

class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movieListResults = MutableLiveData<Resource<MovieListResult>>()
    val movieListResults: LiveData<Resource<MovieListResult>> get() = _movieListResults

    init {
        getMovieListResults()
    }

    private fun getMovieListResults() = viewModelScope.launch {
        _movieListResults.value = Resource.Loading()
        _movieListResults.value = movieRepository.getMovieListResults(1, sortBy= Constants.SORT_ORDER)

    }


}

class MovieListViewModelProviderFactory(private val movieRepository: MovieRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieListViewModel(movieRepository) as T
    }
}