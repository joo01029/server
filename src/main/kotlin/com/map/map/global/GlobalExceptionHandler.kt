package com.map.map.global

import com.map.map.domain.response.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpClientErrorException
import java.lang.Exception

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(HttpClientErrorException::class)
    fun httpClientErrorExceptionHandler(e: HttpClientErrorException):ResponseEntity<Response>{
        val response = Response(e.statusCode, e.message!!)
        return ResponseEntity<Response>(response, e.statusCode)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException):ResponseEntity<Response>{
        val response = Response(HttpStatus.BAD_REQUEST, e.bindingResult!!.allErrors[0].defaultMessage!!)
        return ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(e: Exception):ResponseEntity<Response>{
        e.printStackTrace()
        val response = Response(HttpStatus.INTERNAL_SERVER_ERROR, "서버에러")
        return ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }


}