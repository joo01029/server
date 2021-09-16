package com.map.map.lib

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.MessageDigest

@Component
class Crypto {
    @Value("key_secret")
    private var key:String? = null

    private val digits = "0123456789ABCDEF"

    fun sha256(string:String): String{
        try{
            val md = MessageDigest.getInstance("SHA-256")
            md.update((string+key).toByteArray())
            val byteArray: ByteArray = md.digest()
            return bytesToHex(byteArray)
        }catch (e: Exception){
            throw e
        }
    }

    fun bytesToHex(byteArray: ByteArray): String{
        val hexChars = CharArray(byteArray.size * 2)
        for (i in byteArray.indices) {
            val v = byteArray[i].toInt() and 0xff
            hexChars[i * 2] = digits[v shr 4]
            hexChars[i * 2 + 1] = digits[v and 0xf]
        }
        return String(hexChars)
    }
}