package com.vickikbt.devtyme.data.mappers

import com.vickikbt.devtyme.domain.models.AccessToken
import database.AccessTokenEntity

internal fun AccessTokenEntity.toDomain(): AccessToken {
    return AccessToken(
        accessToken = this.accessToken,
        refreshToken = this.refreshToken,
        scope = this.scope,
        tokenType = this.tokenType,
        uid = this.uid
    )
}
