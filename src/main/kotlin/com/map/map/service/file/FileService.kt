package com.map.map.service.file

import org.springframework.web.multipart.MultipartFile

interface FileService {
    fun storeFile(file: MultipartFile): String
}