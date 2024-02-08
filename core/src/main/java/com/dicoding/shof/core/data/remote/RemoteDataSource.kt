package com.dicoding.shof.core.data.remote

import android.util.Log
import com.dicoding.shof.core.data.remote.network.ApiResponse
import com.dicoding.shof.core.data.remote.network.ApiService
import com.dicoding.shof.core.domain.model.GameDetails
import com.dicoding.shof.core.domain.model.Games
import com.dicoding.shof.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllGames(query: String? = null): Flow<ApiResponse<List<Games>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getList(search =  query)
                val dataArray = response.results.map {
                    DataMapper.mapResponseToDomain(it)
                }
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getDetailsGame(id: Int): Flow<ApiResponse<GameDetails>> {
        return flow {
            try {
                val response = apiService.getDetails(id = id)
                if (response.name.isNotEmpty()) {
                    val gameDetails = DataMapper.mapDetailsResponseToDomain(response)
                    emit(ApiResponse.Success(gameDetails))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

