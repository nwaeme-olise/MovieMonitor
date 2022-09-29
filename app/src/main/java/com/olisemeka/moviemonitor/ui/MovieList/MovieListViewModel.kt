package com.olisemeka.moviemonitor.ui.MovieList

import androidx.lifecycle.*
import com.olisemeka.moviemonitor.data.MovieListResult
import com.olisemeka.moviemonitor.data.MovieRepository
import com.olisemeka.moviemonitor.util.Constants
import kotlinx.coroutines.launch

class MovieListViewModel(val movieRepository: MovieRepository) : ViewModel() {

    init {
        getMovieListResults()
    }

    private val _movieListResults = MutableLiveData<MovieListResult>()
    val movieListResults: LiveData<MovieListResult> get() = _movieListResults

    private fun getMovieListResults() = viewModelScope.launch {
        val response = movieRepository.getMovieListResults(1, sortBy= Constants.SORT_ORDER)
        _movieListResults.postValue(response.body())
    }


}

class MovieListViewModelProviderFactory(val movieRepository: MovieRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieListViewModel(movieRepository) as T
    }
}