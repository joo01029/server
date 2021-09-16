package com.map.map.service.auth

import com.map.map.domain.dto.auth.RegisterDto
import com.map.map.domain.entity.User
import com.map.map.domain.entity.backup.UserBackUp
import com.map.map.domain.entity.backup.repo.UserBackUpRepo

fun registerDtoToUser(registerDto: RegisterDto, user: User){
    user.id = registerDto.id
    user.password = registerDto.password
    user.name = registerDto.name
    user.gender = registerDto.gender
    user.birthDate = registerDto.birthDate
}

fun userToUserBackUp(user: User, userBackUp: UserBackUp){
    userBackUp.idx = user.idx
    userBackUp.id = user.id
    userBackUp.password = user.password
    userBackUp.name = user.name
    userBackUp.birthDate = user.birthDate
    userBackUp.gender = user.gender
}