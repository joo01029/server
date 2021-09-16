package com.map.map.filter

import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.*
import javax.servlet.http.HttpServletResponse

@Order(1)
@Component
class CorsFilter: Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val res = response as HttpServletResponse
        res.setHeader("Access-Control-Allow-Origin", "*")
        res.setHeader("Access-Control-Allow-Credentials", "true")
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH, HEAD")
        res.setHeader("Access-Control-Max-Age", "-1")
        res.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers, Cache-Control, Pragma, Expires")
        res.setHeader("Access-Control-Expose-Headers", "content-disposition")

        chain!!.doFilter(request, response)
    }

    override fun init(filterConfig: FilterConfig?) {}
    override fun destroy() {}
}