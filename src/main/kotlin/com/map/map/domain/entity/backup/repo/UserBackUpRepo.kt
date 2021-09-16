package com.map.map.domain.entity.backup.repo

import com.map.map.domain.entity.backup.UserBackUp
import org.springframework.data.jpa.repository.JpaRepository

interface UserBackUpRepo : JpaRepository<UserBackUp, Long>{
}