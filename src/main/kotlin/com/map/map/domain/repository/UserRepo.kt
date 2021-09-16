package com.map.map.domain.repository

import com.map.map.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo : JpaRepository<User, Long> {
    fun findById(id:String):User?
}