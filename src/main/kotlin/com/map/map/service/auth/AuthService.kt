package com.map.map.service.auth

import com.map.map.domain.dto.auth.LoginDto
import com.map.map.domain.dto.auth.RegisterDto
import com.map.map.domain.response.auth.LoginRo


interface AuthService {
    fun register(registerDto: RegisterDto)
    fun checkId(id: String)
    fun login(loginDto: LoginDto): LoginRo
}