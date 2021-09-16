package com.map.map.domain.dto.auth

import com.map.map.enum.Gender
import org.hibernate.validator.constraints.Length
import java.util.*
import javax.validation.constraints.NotNull


class RegisterDto {
    @NotNull(message = "id null 불가능")
    @Length(min=4,max=20, message="id 길이 4~20")
    var id: String? = null

    @NotNull(message = "password null 불가능")
    @Length(min=8,max=50, message="password 길이 8~50")
    var password: String? = null

    @NotNull(message = "name null 불가능")
    @Length(min=2,max=20, message="name 길이 2~20")
    var name: String? = null

    @NotNull(message = "gender null 불가능")
    var gender: Gender = Gender.Male

    @NotNull(message = "birthDate null 불가능")
    var birthDate: Date? = null
}