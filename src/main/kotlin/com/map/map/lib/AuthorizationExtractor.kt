package com.map.map.lib

import org.apache.logging.log4j.util.Strings
import java.util.*
import javax.servlet.http.HttpServletRequest

class AuthorizationExtractor {
    companion object {
        private const val AUTHORIZATION: String = "Authorization"

        fun extract(request: HttpServletRequest, type: String): String {
            val headers: Enumeration<String> = request.getHeaders(AUTHORIZATION)
            while (headers.hasMoreElements()) {
                val value: String = headers.nextElement()
                if (value.lowercase(Locale.getDefault()).startsWith(type.lowercase())) {
                    return value.substring(type.length).trim()
                }
            }

            return Strings.EMPTY
        }
    }
}