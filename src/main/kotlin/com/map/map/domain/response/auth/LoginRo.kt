package com.map.map.domain.response.auth

import lombok.ToString

@ToString
data class LoginRo (
    var accessToken: String? = null,
    var refreshToken: String? = null
    )