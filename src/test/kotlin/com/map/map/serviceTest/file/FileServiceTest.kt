package com.map.map.serviceTest.file

import com.map.map.service.file.FileServiceImpl
import jdk.jfr.ContentType
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileInputStream
import java.nio.file.Path
import java.nio.file.Paths

@SpringBootTest(classes = arrayOf(FileServiceImpl::class))
class FileServiceTest @Autowired constructor(
    var fileServiceImpl: FileServiceImpl
){
    private var testFileLocation: Path = Paths.get("").toAbsolutePath()
    @Test
    fun uploadFile(){

        var fileName = "삼겹살"
        var contentType ="jpeg"
        var filePath : String = testFileLocation.toString()+"/"+fileName+"."+contentType
        println(filePath)
        var testFile : MultipartFile = getMultipartFile(fileName, contentType, filePath);
        fileServiceImpl.storeFile(testFile)
    }

    fun getMultipartFile(fileName: String, contentType: String, filePath: String): MockMultipartFile{
        var fileInputStream = FileInputStream(File(filePath))
        return MockMultipartFile(fileName, "$fileName.$contentType", contentType, fileInputStream)
    }


}