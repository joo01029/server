package com.map.map.domain.response

import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpStatus

class ResponseData<t>(status:HttpStatus, message: String, val data: t): Response(status, message)