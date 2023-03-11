package com.olisemeka.moviemonitor.ui.movielist

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.olisemeka.moviemonitor.data.MovieListResult
import com.olisemeka.moviemonitor.data.MovieResult
import com.olisemeka.moviemonitor.data.repository.MovieRepositoryImpl
import com.olisemeka.moviemonitor.util.Constants
import com.olisemeka.moviemonitor.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepositoryImpl) : ViewModel() {

    //    private val _movieListResults = MutableLiveData<Resource<MovieListResult>>()
//    val movieListResults: LiveData<Resource<MovieListResult>> get() = _movieListResults
    val movieFlow = movieRepository.getMovieListResults().cachedIn(viewModelScope)


//    init {
//        getMovieListResults()
//    }
//
//    private fun getMovieListResults() = viewModelScope.launch {
//        _movieListResults.value = Resource.Loading()
//        _movieListResults.value = movieRepository.getMovieListResults()
//
//    }
//}
}

class MovieListViewModelProviderFactory @Inject constructor(private val movieRepository: MovieRepositoryImpl): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieListViewModel(movieRepository) as T
    }
}