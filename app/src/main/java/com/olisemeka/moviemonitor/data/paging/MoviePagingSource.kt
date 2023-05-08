package com.olisemeka.moviemonitor.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.olisemeka.moviemonitor.api.MovieApi
import com.olisemeka.moviemonitor.data.model.MovieResult
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviePagingSource @Inject constructor(private val movieApi: MovieApi) : PagingSource<Int, MovieResult>() {
    override fun getRefreshKey(state: PagingState<Int, MovieResult>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResult> {
        return try{
            val nextPage = params.key ?: 1
            val response = movieApi.getMovieResults(page = nextPage)
            val movieList = response.results

            LoadResult.Page(
                data = movieList,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (movieList.isEmpty()) null else nextPage + 1
            )
        }
        catch(ioException: IOException){
            LoadResult.Error(ioException)
        }
        catch(httpException: HttpException){
            LoadResult.Error(httpException)
        }
    }
}