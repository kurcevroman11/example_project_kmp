package com.vickikbt.devtyme.data.repository

import com.vickikbt.devtyme.data.cache.realm.AccessTokenDao
import com.vickikbt.devtyme.data.cache.realm.models.AccessTokenEntity
import com.vickikbt.devtyme.data.mappers.toDomain
import com.vickikbt.devtyme.data.mappers.toEntity
import com.vickikbt.devtyme.data.network.ApiService
import com.vickikbt.devtyme.domain.models.AccessToken
import io.realm.RealmResults
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl constructor(
    private val apiService: ApiService,
    private val accessTokenDao: AccessTokenDao
) : AuthRepository {

    override suspend fun fetchUserToken(code: String): AccessToken? {
        val responseDto = apiService.fetchUserToken(code = code)
        val responseEntity = responseDto?.toEntity()

        responseEntity?.let { saveUserToken(accessToken = it) }

        return responseEntity?.toDomain()
    }

    override suspend fun saveUserToken(accessToken: AccessTokenEntity) =
        accessTokenDao.saveToken(tokenEntity = accessToken)

    override suspend fun getUserToken(): Flow<RealmResults<AccessTokenEntity>> =
        accessTokenDao.getToken

    // override suspend fun deleteUserToken() = accessTokenDao.deleteToken()
}
