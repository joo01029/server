package com.map.map.service.jwt

import com.map.map.domain.entity.User
import com.map.map.enum.JwtType

interface JwtService {
    fun createToken(id: String, authType: JwtType): String
    fun validateToken(token: String?): User?
    fun refreshToken(refreshToken: String?): String?
}