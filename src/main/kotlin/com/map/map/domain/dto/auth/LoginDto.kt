package com.map.map.domain.dto.auth

import javax.validation.constraints.NotNull


class LoginDto {
    @NotNull(message = "id null 불가능")
    var id:String? = null
    @NotNull(message = "password null 불가능")
    var password: String? = null

}