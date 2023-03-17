package com.olisemeka.moviemonitor.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.olisemeka.moviemonitor.api.MovieApi
import com.olisemeka.moviemonitor.data.MovieResult
import com.olisemeka.moviemonitor.data.database.MovieDatabase
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator @Inject constructor(val movieApi: MovieApi, val movieDatabase: MovieDatabase): RemoteMediator<Int, MovieResult>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieResult>
    ): MediatorResult {
//        try{
//            val pageKey = when (loadType){
//                LoadType.REFRESH -> 0
//                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
//                LoadType.APPEND -> {
//                    val lastItem = state.lastItemOrNull()
//                        ?: return MediatorResult.Success(endOfPaginationReached = true)
//                    lastItem.id?.plus(1)
//                }
//            }
//        }
        TODO()
    }
}