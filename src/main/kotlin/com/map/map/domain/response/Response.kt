package com.map.map.domain.response

import org.springframework.http.HttpStatus

open class Response(status: HttpStatus, val message: String) {
    val status = status.value()
}