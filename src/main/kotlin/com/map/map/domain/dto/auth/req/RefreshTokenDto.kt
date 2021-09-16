package com.map.map.domain.dto.auth.req

import javax.validation.constraints.NotBlank

class RefreshTokenDto {
    @NotBlank
    val token: String? = null
}