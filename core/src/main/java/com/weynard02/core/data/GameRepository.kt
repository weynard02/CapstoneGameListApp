package com.weynard02.core.data

import com.weynard02.core.data.source.local.LocalDataSource
import com.weynard02.core.data.source.remote.RemoteDataSource
import com.weynard02.core.data.source.remote.network.ApiResponse
import com.weynard02.core.data.source.remote.response.GameResponse
import com.weynard02.core.domain.model.Game
import com.weynard02.core.domain.repository.IGameRepository
import com.weynard02.core.utils.AppExecutors
import com.weynard02.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {
    override fun getAllGame(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGame().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getAllGame()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(gameList)
            }
        }.asFlow()

    override fun getGame(query: String): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getGame(query).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getGame(query)

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(gameList)
            }
        }.asFlow()

    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute{
            localDataSource.setFavoriteGame(gameEntity, state)
        }
    }
}