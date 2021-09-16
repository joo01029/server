package com.map.map.service.file

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.nio.file.*
import java.util.*

@Service
class FileServiceImpl : FileService{
    private var fileStorageLocation: Path = Paths.get("static/").toAbsolutePath().normalize()

//    @Autowired
//    fun makeDirectory(){
//        try{
//            Files.createDirectory(fileStorageLocation)
//        }catch (e: Exception){
//            throw e;
//        }
//    }

    /**
     * 파일 저장
     */
    override fun storeFile(file: MultipartFile): String {
        val fileName = StringUtils.cleanPath(UUID.randomUUID().toString()+"-"+Objects.nonNull(file.originalFilename)+"."+file.contentType)
        return try{
            var makeFile = fileStorageLocation.resolve(fileName)
            Files.copy(file.inputStream, makeFile, StandardCopyOption.REPLACE_EXISTING)
            fileName
        }catch (e: IOException){
            throw e
        }
    }


}