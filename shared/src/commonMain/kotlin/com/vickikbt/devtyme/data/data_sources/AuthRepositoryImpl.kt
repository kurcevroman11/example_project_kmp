package com.vickikbt.devtyme.data.data_sources

import com.vickikbt.devtyme.data.cache.sqldelight.AccessTokenDao
import com.vickikbt.devtyme.data.mappers.toDomain
import com.vickikbt.devtyme.data.mappers.toEntity
import com.vickikbt.devtyme.data.network.ApiService
import com.vickikbt.devtyme.data.network.models.CurrentUserDto
import com.vickikbt.devtyme.domain.models.AccessToken
import com.vickikbt.devtyme.domain.repositories.AuthRepository
import database.AccessTokenEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl constructor(
    private val apiService: ApiService,
    private val accessTokenDao: AccessTokenDao
) : AuthRepository {

    override suspend fun fetchUserToken(code: String) {
        val responseDto = apiService.fetchUserToken(code = code)
        val responseEntity = responseDto?.toEntity()

        responseEntity?.let {
            saveUserToken(accessToken = it)
        }
    }

    override suspend fun saveUserToken(accessToken: AccessTokenEntity) =
        accessTokenDao.saveToken(accessTokenEntity = accessToken)

    override suspend fun getUserToken(): Flow<AccessToken?> {
        return accessTokenDao.getToken.map { it?.toDomain() }
    }

    override suspend fun deleteUserToken() = accessTokenDao.deleteToken()

    override suspend fun getUserProfile(): Flow<CurrentUserDto?> {
        return flowOf(apiService.getCurrentUser())
    }
}
